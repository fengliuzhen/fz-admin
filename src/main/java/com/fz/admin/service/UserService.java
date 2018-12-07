package com.fz.admin.service;

import com.fz.admin.entity.AddUserEntity;
import com.fz.admin.entity.UserEntity;
import com.github.pagehelper.PageInfo;

public interface UserService {
    UserEntity getLoginInfo(String userName,String passWord);
    UserEntity getUserBaseInfo(int userId);
    PageInfo<UserEntity> getUserList(UserEntity userEntity);
    int addUser(AddUserEntity addUserEntity);
    UserEntity getUserModelByName(String userName);
}
