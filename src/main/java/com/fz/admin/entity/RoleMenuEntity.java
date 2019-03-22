package com.fz.admin.entity;

public class RoleMenuEntity extends RoleEntity {
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    private int roleId;
    private int menuId;
}
