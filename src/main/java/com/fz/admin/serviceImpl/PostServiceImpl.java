package com.fz.admin.serviceImpl;

import com.fz.admin.core.DateCore;
import com.fz.admin.dao.PostDao;
import com.fz.admin.entity.PostDragEntity;
import com.fz.admin.entity.PostEntity;
import com.fz.admin.entity.PostTreeEntity;
import com.fz.admin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Override
    public List<PostTreeEntity> getPostTreeList(int id) {
        return getChildrenList(id);
    }

    @Override
    public int addPost(PostEntity postEntity) {
        int tmpStamp=DateCore.getDateStamp();
        postEntity.setAddTime(tmpStamp);
        postEntity.setLastEditTime(tmpStamp);
        postEntity.setLastEditUid(postEntity.getAddUid());
        postEntity.setDataState(1);
        int rows=postDao.addPost(postEntity);
        if(rows>0) {
            return postEntity.getId();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public int updatePost(PostEntity postEntity) {
        postEntity.setLastEditTime(DateCore.getDateStamp());
        int rows=postDao.updatePost(postEntity);
        return rows;
    }

    @Override
    public PostEntity getPostEntity(int id) {
        return postDao.getPostEntity(id);
    }

    @Override
    public PostEntity getPostEntityByName(int parentId, String postName) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("parentId",parentId);
        paramMap.put("postName",postName);
        return postDao.getPostEntityByName(paramMap);
    }

    @Override
    public int getPostMaxOrderNo(int parentId) {
        return postDao.getPostMaxOrderNo(parentId);
    }

    @Override
    public String getPostIdsByDeptId(int deptId) {
        return postDao.getPostIdsByDeptId(deptId);
    }

    @Override
    public int updateState(int id,int dataState) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("id",id);
        paramMap.put("datastate",dataState);
        return postDao.updateState(paramMap);
    }

    @Override
    @Transactional
    public int updateDragPost(PostDragEntity postDragEntity) {
        int row1=postDao.updateDragPost(postDragEntity);
        if(postDragEntity.getDragNodeOrderNo()>0)
        {
               postDao.updateDragOldPostOrderNo(postDragEntity);
        }
        int row2=postDao.updateDragPostOrderNo(postDragEntity);

        return row1+row2;
    }

    @Override
    public int updateDragPostOrderNo(PostDragEntity postDragEntity) {
        return postDao.updateDragPostOrderNo(postDragEntity);
    }

    @Override
    public int updateDragOldPostOrderNo(PostDragEntity postDragEntity) {
        return postDao.updateDragOldPostOrderNo(postDragEntity);
    }

    private List<PostTreeEntity> getChildrenList(int parentId)
    {
        List<PostTreeEntity> tmpList=postDao.getPostTreeList(parentId);
        if(!Objects.equals(tmpList,null)&&tmpList.size()>0)
        {
            for(PostTreeEntity tmpItem:tmpList)
            {
                tmpItem.setChildren(getChildrenList(tmpItem.getId()));
            }
        }
        return tmpList;
    }
}
