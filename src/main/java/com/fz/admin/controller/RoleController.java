package com.fz.admin.controller;

import com.fz.admin.core.DateCore;
import com.fz.admin.entity.RoleDragEntity;
import com.fz.admin.entity.RoleEntity;
import com.fz.admin.entity.RoleTreeEntity;
import com.fz.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/childlist/{id}",method = RequestMethod.GET,produces="application/json")
    public List<RoleTreeEntity> RoleTreeList(@PathVariable("id")int parentId)
    {
        List<RoleTreeEntity> roleTreeList=roleService.getRoleTreeList(parentId);
        return roleTreeList;
    }
    @RequestMapping(value = "/edit",method = RequestMethod.POST,consumes="application/json")
    public int EditRole(@RequestBody RoleEntity roleEntity)
    {
        //判断当前节点下是否有重名
        RoleEntity tmpRole=roleService.getRoleEntityByName(roleEntity.getParentId(),roleEntity.getRoleName());
        if(!Objects.equals(tmpRole,null)&&tmpRole.getDataState()<=0)
        {
            return roleService.updateState(tmpRole.getId(),1);
        }
        if(roleEntity.getId()>0)
        {
            roleEntity.setLastEditUid(getUserId());
            int rows= roleService.updateRole(roleEntity);
            return  rows;
        }
        else
        {
            if(roleEntity.getParentId()>0) {
                //判断是否为根节点
                Integer orderNo=roleService.getRoleMaxOrderNo(roleEntity.getParentId());
                if(Objects.equals(orderNo,null))
                {
                    orderNo=0;
                }
                if(roleEntity.getOrderNo()<orderNo&&roleEntity.getOrderNo()<=0)
                {
                    roleEntity.setOrderNo(orderNo+1);
                }
            }

            roleEntity.setAddUid(getUserId());
            roleEntity.setRemark("");
            int newId=roleService.addRole(roleEntity);
            return newId;
        }
    }
    @RequestMapping(value = "/drag",method = RequestMethod.POST,consumes="application/json")
    public int DragRole(@RequestBody RoleDragEntity roleDragEntity)
    {
        roleDragEntity.setLastEditTime(DateCore.getDateStamp());
        roleDragEntity.setLastEditUid(getUserId());
        RoleEntity newRole=roleService.getRoleEntity(roleDragEntity.getDragNodeId());
        //放到节点的位置
        int tmpOrderNo=newRole.getOrderNo()+1;
        if(Objects.equals(roleDragEntity.getDragType().toLowerCase(),"before"))
        {
            tmpOrderNo=newRole.getOrderNo();
            roleDragEntity.setDragNodeOrderNo(newRole.getOrderNo()+1);
        }
        else
        {
            roleDragEntity.setDragNodeOrderNo(-1);
        }
        roleDragEntity.setOrderNo(tmpOrderNo);
        if(roleDragEntity.getParentId()>0)
        {
            roleDragEntity.setDragNodeParentId(roleDragEntity.getParentId());
        }
        else {
            roleDragEntity.setDragNodeParentId(newRole.getParentId());
        }
        return roleService.updateDragRole(roleDragEntity);
    }
    @RequestMapping(value = "/del/{id}",method = RequestMethod.GET)
    public int delRole(@PathVariable("id")int id)
    {
        //判断部门是否在使用中

        return roleService.updateState(id,0);
    }
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public RoleEntity getRole(@PathVariable("id")int id)
    {
        return roleService.getRoleEntity(id);
    }
}
