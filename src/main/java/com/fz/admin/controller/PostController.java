package com.fz.admin.controller;

import com.fz.admin.core.DateCore;
import com.fz.admin.entity.PostDragEntity;
import com.fz.admin.entity.PostEntity;
import com.fz.admin.entity.PostTreeEntity;
import com.fz.admin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/post")
public class PostController extends BaseController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/childlist/{id}",method = RequestMethod.GET,produces="application/json")
    public List<PostTreeEntity> PostTreeList(@PathVariable("id")int parentId)
    {
        List<PostTreeEntity> postTreeList=postService.getPostTreeList(parentId);
        return postTreeList;
    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST,consumes="application/json")
    public int EditPost(@RequestBody PostEntity postEntity)
    {
        //判断当前节点下是否有重名
        PostEntity tmpPost=postService.getPostEntityByName(postEntity.getParentId(),postEntity.getPostName());
        if(!Objects.equals(tmpPost,null)&&tmpPost.getDataState()<=0)
        {
            return postService.updateState(tmpPost.getId(),1);
        }
        if(postEntity.getId()>0)
        {
            postEntity.setLastEditUid(getUserId());
            int rows= postService.updatePost(postEntity);
            return  rows;
        }
        else
        {
            if(postEntity.getParentId()>0) {
                //判断是否为根节点
                int orderNo=postService.getPostMaxOrderNo(postEntity.getParentId());
                if(postEntity.getOrderNo()<orderNo&&postEntity.getOrderNo()<=0)
                {
                    postEntity.setOrderNo(orderNo+1);
                }
            }

            postEntity.setAddUid(getUserId());
            postEntity.setRemark("");
            int newId=postService.addPost(postEntity);
            return newId;
        }
    }
    @RequestMapping(value = "/drag",method = RequestMethod.POST,consumes="application/json")
    public int DragPost(@RequestBody PostDragEntity postDragEntity)
    {
        postDragEntity.setLastEditTime(DateCore.getDateStamp());
        postDragEntity.setLastEditUid(getUserId());
        PostEntity newPost=postService.getPostEntity(postDragEntity.getDragNodeId());
        //放到节点的位置
        int tmpOrderNo=newPost.getOrderNo()+1;
        if(Objects.equals(postDragEntity.getDragType().toLowerCase(),"before"))
        {
            tmpOrderNo=newPost.getOrderNo();
            postDragEntity.setDragNodeOrderNo(newPost.getOrderNo()+1);
        }
        else
        {
            postDragEntity.setDragNodeOrderNo(-1);
        }
        postDragEntity.setOrderNo(tmpOrderNo);
        if(postDragEntity.getParentId()>0)
        {
            postDragEntity.setDragNodeParentId(postDragEntity.getParentId());
        }
        else {
            postDragEntity.setDragNodeParentId(newPost.getParentId());
        }
        return postService.updateDragPost(postDragEntity);
    }
    @RequestMapping(value = "/del/{id}",method = RequestMethod.GET)
    public int delPost(@PathVariable("id")int id)
    {
        //判断部门是否在使用中

        return postService.updateState(id,0);
    }
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public PostEntity getPost(@PathVariable("id")int id)
    {
        return postService.getPostEntity(id);
    }
}
