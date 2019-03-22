package com.fz.admin.serviceImpl;

import com.fz.admin.dao.MenuDao;
import com.fz.admin.entity.Menu;
import com.fz.admin.entity.MenuTableEntity;
import com.fz.admin.entity.MenuTreeEntity;
import com.fz.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service("menuServiceImpl")
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    public List<Menu> getMenuListByUid(int uid)
    {
        List<Menu> menuList=menuDao.getMenuListByUid(uid);
        //对结果排序
        List<Menu> retList=menuList.stream().filter(menu ->menu.getPid()==0).sorted(Comparator.comparing(Menu::getOrderNo)).collect(toList());
        for(Menu item:retList)
        {
            item.setSubitem(menuList.stream().filter(menu -> menu.getPid()==item.getId()).sorted(Comparator.comparing(Menu::getOrderNo)).collect(toList()));
        }
        return retList;
    }
    public Integer getIsHavePower(int userId,String menuPath)
    {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("userId",userId);
        paramMap.put("menuPath",menuPath);
        return menuDao.getIsHavePower(paramMap);
    }

    @Override
    public List<MenuTreeEntity> getMenuTreeList(int id) {
        return getChildrenList(id);
    }

    @Override
    public List<MenuTableEntity> getMenuListByPid(int pid) {
        return getMenuChildrenList(pid,1);
    }
    private List<MenuTableEntity> getMenuChildrenList(int parentId,int level)
    {
        List<MenuTableEntity> tmpList=menuDao.getMenuListByPid(parentId);
        if(!Objects.equals(tmpList,null)&&tmpList.size()>0)
        {
            for(MenuTableEntity tmpItem:tmpList)
            {
                tmpItem.setLevel(level);
                tmpItem.setChildList(getMenuChildrenList(tmpItem.getId(),level+1));
            }
        }
        return tmpList;
    }
    private List<MenuTreeEntity> getChildrenList(int parentId)
    {
        List<MenuTreeEntity> tmpList=menuDao.getMenuTreeList(parentId);
        if(!Objects.equals(tmpList,null)&&tmpList.size()>0)
        {
            for(MenuTreeEntity tmpItem:tmpList)
            {
                tmpItem.setChildren(getChildrenList(tmpItem.getId()));
            }
        }
        return tmpList;
    }
}
