package com.fz.admin.service;

import com.fz.admin.entity.RoleDragEntity;
import com.fz.admin.entity.RoleEntity;
import com.fz.admin.entity.RoleMenuEntity;
import com.fz.admin.entity.RoleTreeEntity;

import java.util.List;

public interface RoleService {
    List<RoleTreeEntity> getRoleTreeList(int id);
    int addRole(RoleEntity roleEntity);
    int updateRole(RoleEntity roleEntity);
    RoleEntity getRoleEntity(int id);

    List<RoleMenuEntity> geRoleMenuList(int roleId);
    int[] getRoleMenuIds(int roleId);
    int addRoleMenu(RoleMenuEntity roleMenuEntity);

    RoleEntity getRoleEntityByName(int parentId, String roleName);
    Integer getRoleMaxOrderNo(int parentId);
    int updateState(int id, int dataState);
    int updateDragRole(RoleDragEntity roleDragEntity);
    int updateDragRoleOrderNo(RoleDragEntity roleDragEntity);
    int updateDragOldRoleOrderNo(RoleDragEntity roleDragEntity);
}
