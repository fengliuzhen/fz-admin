package com.fz.admin.serviceImpl;

import com.fz.admin.core.DateCore;
import com.fz.admin.dao.RoleDao;
import com.fz.admin.entity.RoleDragEntity;
import com.fz.admin.entity.RoleEntity;
import com.fz.admin.entity.RoleMenuEntity;
import com.fz.admin.entity.RoleTreeEntity;
import com.fz.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<RoleTreeEntity> getRoleTreeList(int id) {
        return getChildrenList(id);
    }

    @Override
    public int addRole(RoleEntity roleEntity) {
        int tmpStamp=DateCore.getDateStamp();
        roleEntity.setAddTime(tmpStamp);
        roleEntity.setLastEditTime(tmpStamp);
        roleEntity.setLastEditUid(roleEntity.getAddUid());
        roleEntity.setDataState(1);
        int rows=roleDao.addRole(roleEntity);
        if(rows>0) {
            return roleEntity.getId();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public int updateRole(RoleEntity roleEntity) {
        roleEntity.setLastEditTime(DateCore.getDateStamp());
        int rows=roleDao.updateRole(roleEntity);
        return rows;
    }

    @Override
    public RoleEntity getRoleEntity(int id) {
        return roleDao.getRoleEntity(id);
    }

    @Override
    public List<RoleMenuEntity> geRoleMenuList(int roleId) {
        return roleDao.getRoleMenuList(roleId);
    }

    @Override
    public int[] getRoleMenuIds(int roleId) {
        List<RoleMenuEntity> rolemenuEntityList=roleDao.getRoleMenuList(roleId);
        int[] ids=rolemenuEntityList.stream().mapToInt(RoleMenuEntity::getMenuId).toArray();
        return ids;
    }

    @Override
    public int addRoleMenu(RoleMenuEntity roleMenuEntity) {
        return roleDao.addRoleMenu(roleMenuEntity);
    }

    @Override
    public RoleEntity getRoleEntityByName(int parentId, String roleName) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("parentId",parentId);
        paramMap.put("roleName",roleName);
        return roleDao.getRoleEntityByName(paramMap);
    }

    @Override
    public Integer getRoleMaxOrderNo(int parentId) {
        return roleDao.getRoleMaxOrderNo(parentId);
    }

    @Override
    public int updateState(int id,int dataState) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("id",id);
        paramMap.put("datastate",dataState);
        return roleDao.updateState(paramMap);
    }

    @Override
    @Transactional
    public int updateDragRole(RoleDragEntity roleDragEntity) {
        int row1=roleDao.updateDragRole(roleDragEntity);
        if(roleDragEntity.getDragNodeOrderNo()>0)
        {
               roleDao.updateDragOldRoleOrderNo(roleDragEntity);
        }
        int row2=roleDao.updateDragRoleOrderNo(roleDragEntity);

        return row1+row2;
    }

    @Override
    public int updateDragRoleOrderNo(RoleDragEntity roleDragEntity) {
        return roleDao.updateDragRoleOrderNo(roleDragEntity);
    }

    @Override
    public int updateDragOldRoleOrderNo(RoleDragEntity roleDragEntity) {
        return roleDao.updateDragOldRoleOrderNo(roleDragEntity);
    }

    private List<RoleTreeEntity> getChildrenList(int parentId)
    {
        List<RoleTreeEntity> tmpList=roleDao.getRoleTreeList(parentId);
        if(!Objects.equals(tmpList,null)&&tmpList.size()>0)
        {
            for(RoleTreeEntity tmpItem:tmpList)
            {
                tmpItem.setChildren(getChildrenList(tmpItem.getId()));
            }
        }
        return tmpList;
    }
}
