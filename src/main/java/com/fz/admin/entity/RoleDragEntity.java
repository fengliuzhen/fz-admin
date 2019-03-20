package com.fz.admin.entity;

public class RoleDragEntity extends RoleEntity{
    public int getDragNodeId() {
        return dragNodeId;
    }

    public void setDragNodeId(int dragNodeId) {
        this.dragNodeId = dragNodeId;
    }

    private int dragNodeId;

    public int getDragNodeOrderNo() {
        return dragNodeOrderNo;
    }

    public void setDragNodeOrderNo(int dragNodeOrderNo) {
        this.dragNodeOrderNo = dragNodeOrderNo;
    }

    private int dragNodeOrderNo;

    public String getDragType() {
        return dragType;
    }

    public void setDragType(String dragType) {
        this.dragType = dragType;
    }

    private String dragType;

    public int getDragNodeParentId() {
        return dragNodeParentId;
    }

    public void setDragNodeParentId(int dragNodeParentId) {
        this.dragNodeParentId = dragNodeParentId;
    }

    private int dragNodeParentId;
}
