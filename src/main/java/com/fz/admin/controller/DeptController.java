package com.fz.admin.controller;

import com.fz.admin.core.DateCore;
import com.fz.admin.entity.DeptDragEntity;
import com.fz.admin.entity.DeptEntity;
import com.fz.admin.entity.DeptPostEntity;
import com.fz.admin.entity.DeptTreeEntity;
import com.fz.admin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/childlist/{menuid}",method = RequestMethod.GET,produces="application/json")
    public List<DeptTreeEntity> DeptTreeList(@PathVariable("menuid")int parentId)
    {
        List<DeptTreeEntity> deptTreeList=deptService.getDeptTreeList(parentId);
        return deptTreeList;
    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST,consumes="application/json")
    public int EditDept(@RequestBody DeptEntity deptEntity)
    {
        //判断当前节点下是否有重名
        DeptEntity tmpDept=deptService.getDeptEntityByName(deptEntity.getParentId(),deptEntity.getDeptName());
        if(!Objects.equals(tmpDept,null)&&tmpDept.getDataState()<=0)
        {
            return deptService.updateState(tmpDept.getId(),1);
        }
        if(deptEntity.getId()>0)
        {
            deptEntity.setLastEditUid(getUserId());
            int rows= deptService.updateDept(deptEntity);
            return  rows;
        }
        else
        {
            if(deptEntity.getParentId()>0) {
                //判断是否为根节点
                int orderNo=deptService.getDeptMaxOrderNo(deptEntity.getParentId());
                if(deptEntity.getOrderNo()<orderNo&&deptEntity.getOrderNo()<=0)
                {
                    deptEntity.setOrderNo(orderNo+1);
                }
            }

            deptEntity.setAddUid(getUserId());
            deptEntity.setRemark("");
            int newId=deptService.addDept(deptEntity);
            return newId;
        }
    }

    @RequestMapping(value = "/get/maxorderno/{parentid}",method = RequestMethod.GET)
    public int getMaxOrderNo(@PathVariable("parentid")int pid)
    {
        return deptService.getDeptMaxOrderNo(pid);
    }
    @RequestMapping(value = "/drag",method = RequestMethod.POST,consumes="application/json")
    public int DragDept(@RequestBody DeptDragEntity deptDragEntity)
    {
        deptDragEntity.setLastEditTime(DateCore.getDateStamp());
        deptDragEntity.setLastEditUid(getUserId());
        DeptEntity newDept=deptService.getDeptEntity(deptDragEntity.getDragNodeId());
        //放到节点的位置
        int tmpOrderNo=newDept.getOrderNo()+1;
        if(Objects.equals(deptDragEntity.getDragType().toLowerCase(),"before"))
        {
            tmpOrderNo=newDept.getOrderNo();
            deptDragEntity.setDragNodeOrderNo(newDept.getOrderNo()+1);
        }
        else
        {
            deptDragEntity.setDragNodeOrderNo(-1);
        }
        deptDragEntity.setOrderNo(tmpOrderNo);
        if(deptDragEntity.getParentId()>0)
        {
            deptDragEntity.setDragNodeParentId(deptDragEntity.getParentId());
        }
        else {
            deptDragEntity.setDragNodeParentId(newDept.getParentId());
        }
        return deptService.updateDragDept(deptDragEntity);
    }
    @RequestMapping(value = "/del/{menuid}",method = RequestMethod.GET)
    public int delDept(@PathVariable("menuid")int id)
    {
        //判断部门是否在使用中
        return deptService.updateState(id,0);
    }
    @RequestMapping(value = "/get/{menuid}",method = RequestMethod.GET)
    public DeptEntity getDept(@PathVariable("menuid")int id)
    {
        return deptService.getDeptEntity(id);
    }

    @RequestMapping(value = "/deptpost/{deptid}",method = RequestMethod.GET)
    public int[] getDeptPostIds(@PathVariable("deptid")int deptid)
    {
        return deptService.getDeptPostIds(deptid);
    }


    @RequestMapping(value = "/savedeptpost",method = RequestMethod.POST,consumes="application/json")
    public int editDeptPost(@RequestBody DeptPostEntity deptPostEntity)
    {
        deptPostEntity.setAddUid(getUserId());
        deptPostEntity.setAddTime(DateCore.getDateStamp());
        return deptService.addDeptPost(deptPostEntity);
    }
}
