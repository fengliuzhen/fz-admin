package com.fz.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemController {

    @RequestMapping("/nopower")
    public String nopower(Model model)
    {
        return "nopower";
    }
}
