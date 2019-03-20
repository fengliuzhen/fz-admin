package com.fz.admin.service;

import com.fz.admin.entity.AddUserEntity;
import com.fz.admin.entity.SysLog;
import com.fz.admin.entity.UserEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    UserEntity getLoginInfo(String userName,String passWord);
    UserEntity getUserBaseInfo(int userId);
    PageInfo<UserEntity> getUserList(UserEntity userEntity);
    int addUser(AddUserEntity addUserEntity);
    int updateUser(AddUserEntity addUserEntity);
    UserEntity getUserModelByName(String userName);
    UserEntity getUserModelById(int userId);
    PageInfo<UserEntity> getUserByRoleId(UserEntity userEntity);
    int updateLock(SysLog sysLog);
    int updatePwd(AddUserEntity addUserEntity);
}
