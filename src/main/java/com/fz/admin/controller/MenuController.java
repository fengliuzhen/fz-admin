package com.fz.admin.controller;

import com.fz.admin.core.DateCore;
import com.fz.admin.dao.MenuDao;
import com.fz.admin.entity.MenuEntity;
import com.fz.admin.entity.MenuTableEntity;
import com.fz.admin.entity.MenuTreeEntity;
import com.fz.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    @RequestMapping(value = "/get/maxorderno/{parentid}",method = RequestMethod.GET)
    public int getMaxOrderNo(@PathVariable("parentid")int pid)
    {
        MenuEntity menuEntity=menuService.getMenuModelById(pid);
        return menuService.getMaxOrderNo(menuEntity.getPid());
    }
    @RequestMapping(value = "/save",method = RequestMethod.POST,consumes="application/json")
    public int saveMenu(@RequestBody MenuEntity menuEntity)
    {
        menuEntity.setLastedituid(getUserId());
        menuEntity.setLastedittime(DateCore.getDateStamp());
        //判断当前节点下是否有重名
        MenuEntity tmpMenu=menuService.getMenuModelByPidName(menuEntity.getPid(),menuEntity.getName());
        if(!Objects.equals(tmpMenu,null))
        {
            if(tmpMenu.getDatastate()<=0) {
                tmpMenu.setDatastate(1);
                menuEntity.setId(tmpMenu.getId());

                menuService.updateMenu(menuEntity);
                return menuService.updateState(tmpMenu);
            }
            else if(!Objects.equals(menuEntity.getId(),tmpMenu.getId()))
            {
                //存在相同名称的菜单
                return -1;
            }
        }

        if(menuEntity.getId()>0)
        {
            int rows= menuService.updateMenu(menuEntity);
            return  rows;
        }
        else
        {
            if(menuEntity.getPid()>0) {
                //判断是否为根节点
                int orderNo=menuService.getMaxOrderNo(menuEntity.getPid());
                if(menuEntity.getOrderno()<orderNo&&menuEntity.getOrderno()<=0)
                {
                    menuEntity.setOrderno(orderNo+1);
                }
            }
            menuEntity.setAdduid(getUserId());
            menuEntity.setAddtime(DateCore.getDateStamp());
            int newId=menuService.addMenu(menuEntity);
            return newId;
        }
    }
    @RequestMapping(value = "/del/{id}",method = RequestMethod.GET)
    public int delMenu(@PathVariable("id")int id)
    {
        MenuEntity menuEntity=new MenuEntity();
        menuEntity.setId(id);
        menuEntity.setDatastate(0);
        menuEntity.setLastedituid(getUserId());
        menuEntity.setLastedittime(DateCore.getDateStamp());
        return menuService.updateState(menuEntity);
    }
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    public MenuEntity getMenu(@PathVariable("id")int id)
    {
        return menuService.getMenuModelById(id);
    }
}
