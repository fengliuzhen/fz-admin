package com.fz.admin.controller;

import com.fz.admin.Service.RedisService;
import com.fz.admin.core.APIResultModel;
import com.fz.admin.core.CookieCore;
import com.fz.admin.core.SystemEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginController {

    public Logger log = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private RedisService redisService;

    @RequestMapping("/")
    public String Index()
    {
        return "forward:/login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        String tmpSessionId= CookieCore.initLoginCookie(request,response);
        if(tmpSessionId!=null&&!tmpSessionId.isEmpty())
        {
            //清除session值
            redisService.delete(SystemEnum.Admin_Sid.getValue()+tmpSessionId);
        }
        model.addAttribute("sysname","ERP管理系统");
        model.addAttribute("imgr",(new Random().nextInt()));
        return "login";
    }
    @ResponseBody
    @RequestMapping(value="/postlogin",method= RequestMethod.POST,consumes ="application/json")
    public APIResultModel<String> adminLogin(@RequestBody Map<String,String> mapParams, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        APIResultModel<String> resultModel=new APIResultModel<>();
        try
        {
            String username=mapParams.get("username");
            String password=mapParams.get("password");
            String loginCode=mapParams.get("loginCode");
            //判断参数是否为空 String username,String password,String loginCode
            if(username==null||username.isEmpty()||password==null||password.isEmpty())
            {
                resultModel.setError("1005","登录名或密码不能为空");
                return resultModel;
            }
            //验证码
            String tmpSessionId=CookieCore.initLoginCookie(request,response);
            String vcodeKey=SystemEnum.Valid_Code.getValue()+tmpSessionId;
            String tmpCookie=redisService.get(vcodeKey);
            if(tmpCookie==null||(!loginCode.toUpperCase().equals(tmpCookie.toUpperCase()))){
                resultModel.setError("1001","验证码不正确");
                return resultModel;
            }
            int userId=1;
            boolean isLock=false;
            if(userId>0){
                //判断是否锁定
                if(isLock)
                {
                    resultModel.setError("1001",String.format("【s%】已被系统锁定，暂时无法登录",username));
                    return resultModel;
                }
                //登录成功 移除验证码
                redisService.delete(vcodeKey);
                //改为写radis  8个小时失效
                redisService.put(SystemEnum.Admin_Sid.getValue()+tmpSessionId,userId,8, TimeUnit.HOURS);
                //记录日志
                log.info(String.format("【%s】登录了系统。",username));
                //登录成功
                resultModel.setSuccess("1000","登录成功");
                return resultModel;
            }
            else{
                resultModel.setError("1002","登录名或者密码不正确");
                return resultModel;
            }
        }
        catch(Exception ex)
        {
            System.out.print("登录异常："+ex.getMessage());
            resultModel.setError("1002","登录异常，请重新登录");
            return resultModel;
        }
    }
}
