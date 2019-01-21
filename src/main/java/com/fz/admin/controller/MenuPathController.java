package com.fz.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class MenuPathController {
    //所有的菜单跳转必须以 /menupath/开头
    @RequestMapping(value = "/organization/deptlist",method = RequestMethod.GET)
    public String DeptList(Model model)
    {
        return "organization/deptlist";
    }
    @RequestMapping(value = "/organization/postlist",method = RequestMethod.GET)
    public String PostList(Model model)
    {
        return "organization/postlist";
    }
    @RequestMapping(value = "/organization/userlist",method = RequestMethod.GET)
    public String UserList(Model model)
    {
        return "organization/userlist";
    }
    @RequestMapping(value = "/system/loglist",method = RequestMethod.GET)
    public String LogList(Model model)
    {
        return "system/loglist";
    }
}
