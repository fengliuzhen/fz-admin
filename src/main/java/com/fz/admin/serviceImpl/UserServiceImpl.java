package com.fz.admin.serviceImpl;

import com.fz.admin.dao.UserDao;
import com.fz.admin.entity.UserEntity;
import com.fz.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
}
