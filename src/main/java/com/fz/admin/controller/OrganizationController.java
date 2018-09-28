package com.fz.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {
    @RequestMapping(value = "/deptlist",method = RequestMethod.GET)
    public String DeptList(Model model)
    {
        return "organization/deptlist";
    }
    @RequestMapping(value = "/postlist",method = RequestMethod.GET)
    public String PostList(Model model)
    {
        return "organization/postlist";
    }
}
