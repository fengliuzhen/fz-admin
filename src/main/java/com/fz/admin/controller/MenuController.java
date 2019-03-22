package com.fz.admin.controller;

import com.fz.admin.entity.MenuTableEntity;
import com.fz.admin.entity.MenuTreeEntity;
import com.fz.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/childlist/{id}",method = RequestMethod.GET,produces="application/json")
    public List<MenuTreeEntity> menuTreeList(@PathVariable("id")int parentId)
    {
        List<MenuTreeEntity> deptTreeList=menuService.getMenuTreeList(parentId);
        return deptTreeList;
    }

    @RequestMapping(value = "/childtablelist/{id}",method = RequestMethod.GET,produces="application/json")
    public List<MenuTableEntity> MenuTableTreeList(@PathVariable("id")int parentId)
    {
        return menuService.getMenuListByPid(parentId);
    }
}
