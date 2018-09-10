package com.fz.admin.controller;

import com.fz.admin.entity.Menu;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class MainController extends BaseController {
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
        subMenu.setUrl("/list");
        Menu subMenu2=new Menu();
        subMenu2.setId(12);
        subMenu2.setName("权限列表");
        subMenu2.setUrl("http://www.baidu.com");

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

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model)
    {
        String sid=request.getSession().getId();
        model.addAttribute("sid",sid);
        return "list";
    }
    @RequestMapping(value = "/search/list",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public Map<String, Object> searchList(@RequestBody Map<String,Object> paramMap)
    {
        PageInfo<Menu> page=new PageInfo();
        List<Menu> menus=new ArrayList<>();
        Menu menu=new Menu();
        menu.setId(1);
        menu.setName("用户管理");
        menu.setIcon("el-icon-menu");
        menus.add(menu);

        page.setList(menus);
        page.setTotal(30);
        Map<String, Object> map = new HashMap();
        map.put("datalist", page.getList());
        map.put("totalcount", page.getTotal());
        return map;
    }
}
