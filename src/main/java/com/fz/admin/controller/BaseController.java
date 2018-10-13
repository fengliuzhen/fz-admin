package com.fz.admin.controller;

import com.fz.admin.core.ConvertCore;
import com.fz.admin.core.CookieCore;
import com.fz.admin.core.JsonCore;
import com.fz.admin.core.SystemEnum;
import com.fz.admin.entity.UserEntity;
import com.fz.admin.service.RedisService;
import com.fz.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseController {

    Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Autowired
    public HttpServletRequest request;
    @Resource
    public HttpServletResponse response;
    @Autowired
    private RedisService redisService;

    @Autowired
    private UserService userService;

    public int getUserId() {
        //判断Session是否为空
        String tmpSessionId= CookieCore.initLoginCookie(request,response);
        String objSession=redisService.rget(SystemEnum.Admin_Sid.getValue()+tmpSessionId);
        int adminId= ConvertCore.ObjToInt(objSession);
        return adminId;
    }
    public UserEntity GetCureenUser() throws Exception
    {
        try {
            int userId=getUserId();
            //缓存名称
            String cacheName=(SystemEnum.UserInfo.getValue()+userId).toUpperCase();
            //判断缓存
            String redisUserInfo=redisService.rget(cacheName);
            if(Objects.equals(redisUserInfo,null)||redisUserInfo.isEmpty()) {
                if(userId<=0)
                {
                    return null;
                }
                else
                {
                    UserEntity userEntity=userService.getUserBaseInfo(userId);
                    //写用户信息
                    redisService.put((SystemEnum.UserInfo.getValue()+userId).toUpperCase(),userEntity,1, TimeUnit.HOURS);
                   return userEntity;
                }
            }
            else
            {
                return JsonCore.fromJson(redisUserInfo,UserEntity.class);
            }
        }
        catch (Exception e) {
            logger.error(String.format("获取当前用户信息异常%s",e.getMessage()));
            return null;
        }
    }
}
