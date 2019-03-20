package com.fz.admin.serviceImpl;

import com.fz.admin.core.DateCore;
import com.fz.admin.dao.SysLogDao;
import com.fz.admin.dao.UserDao;
import com.fz.admin.entity.AddUserEntity;
import com.fz.admin.entity.SysLog;
import com.fz.admin.entity.UserEntity;
import com.fz.admin.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SysLogDao sysLogDao;

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
    public int updateUser(AddUserEntity addUserEntity) {
        addUserEntity.setLastEditTime(DateCore.getDateStamp());
        return userDao.updateUser(addUserEntity);
    }

    @Override
    public UserEntity getUserModelByName(String userName) {
        return userDao.getUserModelByName(userName);
    }

    @Override
    public UserEntity getUserModelById(int userId) {
        return  userDao.getUserModelById(userId);
    }

    @Override
    public PageInfo<UserEntity> getUserByRoleId(UserEntity userEntity) {
        PageHelper.startPage(userEntity.getPageNum(), userEntity.getPageSize());
        List<UserEntity> list = userDao.getUserByRoleId(userEntity);
        PageInfo<UserEntity> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    @Transactional
    public int updateLock(SysLog sysLog) {
        try {
            int userRows=userDao.updateLock(sysLog);
            int logRows=sysLogDao.addLog(sysLog);
            return Math.abs(userRows+logRows);
        }
        catch (Exception ex)
        {
            return 0;
        }
    }
    @Override
    public int updatePwd(AddUserEntity addUserEntity)
    {
        return userDao.updatePwd(addUserEntity);
    }
}
