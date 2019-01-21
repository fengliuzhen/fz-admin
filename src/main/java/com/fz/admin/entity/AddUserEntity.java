package com.fz.admin.entity;

import java.util.Date;

public class AddUserEntity {
    private int userId;
    private String userName;
    private String realName;
    private String passWord;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 昵称
     */
    private String nickName;

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    private int age;
    private int sex;
    private int isLock;
    private String email;
    private String mobile;
    private int deptId;
    private int dataState;
    private int addTime;
    private int addUid;
    private String remark;

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

    private int lastEditTime;
    private int lastEditUid;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public int getDeptId() {
        return deptId;
    }
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public int getIsLock() {
        return isLock;
    }
    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getRealName() {
        return realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
}
