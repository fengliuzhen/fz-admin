package com.fz.admin.controller;

import com.fz.admin.core.CookieCore;
import com.fz.admin.core.SystemEnum;
import com.fz.admin.entity.Menu;
import com.fz.admin.entity.SysConfig;
import com.fz.admin.entity.UserEntity;
import com.fz.admin.service.MenuService;
import com.fz.admin.service.RedisService;
import com.fz.admin.service.SysConfigService;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class MainController extends BaseController {

    @Autowired
    private SysConfigService sysConfigService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(Model model) throws Exception
    {
        SysConfig sysConfig=sysConfigService.getEntityByKey("sysname");
        model.addAttribute("sysname",Objects.equals(sysConfig,null)?"管理系统":sysConfig.getItemVal());
        //获取用户信息
        UserEntity userEntity=GetCureenUser();
        if(Objects.equals(userEntity,null))
        {
            String tmpSessionId= CookieCore.initLoginCookie(request,response);
            if(tmpSessionId!=null&&!tmpSessionId.isEmpty())
            {
                //清除session值
                redisService.remove(SystemEnum.Admin_Sid.getValue()+tmpSessionId);
            }
            //重新登录
            response.sendRedirect("/login");
        }
        model.addAttribute("adminname",userEntity.getRealName());
        model.addAttribute("deptname",userEntity.getDeptName());
        model.addAttribute("postname",userEntity.getPostName());
        return "main";
    }
    @RequestMapping(value = "/menulist",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public List<Menu> getMenuList() {
        List<Menu> menuList = menuService.getMenuListByUid(getUserId());
        return menuList;
    }
}
