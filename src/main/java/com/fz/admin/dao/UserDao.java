package com.fz.admin.dao;

import com.fz.admin.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserDao {
    UserEntity getLoginInfo(Map<String,Object> paramMap);
    UserEntity getUserBaseInfo(@Param("userId")int userId);
}
