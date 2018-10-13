package com.fz.admin.service;

import com.fz.admin.entity.UserEntity;

public interface UserService {
    UserEntity getLoginInfo(String userName,String passWord);
    UserEntity getUserBaseInfo(int userId);
}
