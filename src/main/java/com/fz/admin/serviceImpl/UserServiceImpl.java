package com.fz.admin.serviceImpl;

import com.fz.admin.core.DateCore;
import com.fz.admin.dao.UserDao;
import com.fz.admin.entity.AddUserEntity;
import com.fz.admin.entity.UserEntity;
import com.fz.admin.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public UserEntity getLoginInfo(String userName, String passWord)
    {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("userName",userName);
        paramMap.put("passWord",passWord);
        return userDao.getLoginInfo(paramMap);
    }
    public UserEntity getUserBaseInfo(int userId)
    {
        return userDao.getUserBaseInfo(userId);
    }

    @Override
    public PageInfo<UserEntity> getUserList(UserEntity userEntity) {
        PageHelper.startPage(userEntity.getPageNum(), userEntity.getPageSize());
        List<UserEntity> list = userDao.getUserList(userEntity);
        PageInfo<UserEntity> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int addUser(AddUserEntity addUserEntity) {
        addUserEntity.setAddTime(DateCore.getDateStamp());
        return userDao.addUser(addUserEntity);
    }

    @Override
    public UserEntity getUserModelByName(String userName) {
        return userDao.getUserModelByName(userName);
    }

}
