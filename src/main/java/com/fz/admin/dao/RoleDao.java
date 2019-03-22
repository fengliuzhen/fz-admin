package com.fz.admin.dao;

import com.fz.admin.entity.RoleDragEntity;
import com.fz.admin.entity.RoleEntity;
import com.fz.admin.entity.RoleMenuEntity;
import com.fz.admin.entity.RoleTreeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleDao {
    List<RoleTreeEntity> getRoleTreeList(@Param("id") int id);

    int addRole(RoleEntity roleEntity);

    int updateRole(RoleEntity roleEntity);

    RoleEntity getRoleEntity(@Param("id") int id);

    RoleEntity getRoleEntityByName(Map<String, Object> paramMap);

    Integer getRoleMaxOrderNo(@Param("parentId") int parentId);

    int updateState(Map<String, Object> paramMap);

    int updateDragRole(RoleDragEntity roleDragEntity);

    int updateDragRoleOrderNo(RoleDragEntity roleDragEntity);
    int updateDragOldRoleOrderNo(RoleDragEntity roleDragEntity);

    List<RoleMenuEntity> getRoleMenuList(@Param("roleId")int roleId);

    int addRoleMenu(RoleMenuEntity roleMenuEntity);
}
