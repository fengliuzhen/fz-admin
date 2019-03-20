package com.fz.admin.dao;

import com.fz.admin.entity.AddUserEntity;
import com.fz.admin.entity.SysLog;
import com.fz.admin.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {
    UserEntity getLoginInfo(Map<String,Object> paramMap);
    UserEntity getUserBaseInfo(@Param("userId")int userId);
    List<UserEntity> getUserList(UserEntity userEntity);
    int addUser(AddUserEntity addUserEntity);
    int updateUser(AddUserEntity addUserEntity);
    UserEntity getUserModelByName(@Param("userName")String userName);
    UserEntity getUserModelById(@Param("userId")int userId);
    List<UserEntity> getUserByRoleId(UserEntity userEntity);
    int updateLock(SysLog sysLog);
    int updatePwd(AddUserEntity addUserEntity);
}
