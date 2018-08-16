package com.fz.admin.controller;

import com.fz.admin.entity.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class MainController {
    @RequestMapping(value = "/main",method = RequestMethod.GET)
    public String main(Model model)
    {
        model.addAttribute("sysname","ERP管理系统");
        return "main";
    }

    @RequestMapping(value = "/menulist",method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public List<Menu> getMenuList()
    {
        List<Menu> menus=new ArrayList<>();

        Menu menu=new Menu();
        menu.setId(1);
        menu.setName("用户管理");
        menu.setIcon("el-icon-menu");

        List<Menu> subMenuList=new ArrayList<>();

        Menu subMenu=new Menu();
        subMenu.setId(11);
        subMenu.setName("用户列表");
        subMenu.setUrl("/dd/ss");
        Menu subMenu2=new Menu();
        subMenu2.setId(12);
        subMenu2.setName("权限列表");
        subMenu2.setUrl("/dd/ss");

        subMenuList.add(subMenu);
        subMenuList.add(subMenu2);
        menu.setSubitem(subMenuList);
        menus.add(menu);

        Menu menu2=new Menu();
        menu2.setId(2);
        menu2.setName("角色管理");
        menu2.setIcon("el-icon-menu");
        menus.add(menu2);
        return menus;
    }
}
