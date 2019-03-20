package com.fz.admin.entity;

public class PostEntity {
    private int id;
    private String postName;
    private int parentId;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    private String parentName;
    private int orderNo;
    private int dataState;
    private int addTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getDataState() {
        return dataState;
    }

    public void setDataState(int dataState) {
        this.dataState = dataState;
    }

    public int getAddTime() {
        return addTime;
    }

    public void setAddTime(int addTime) {
        this.addTime = addTime;
    }

    public int getAddUid() {
        return addUid;
    }

    public void setAddUid(int addUid) {
        this.addUid = addUid;
    }

    public int getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(int lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public int getLastEditUid() {
        return lastEditUid;
    }

    public void setLastEditUid(int lastEditUid) {
        this.lastEditUid = lastEditUid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private int addUid;
    private int lastEditTime;
    private int lastEditUid;
    private String remark;
}
