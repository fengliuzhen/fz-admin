package com.fz.admin.serviceImpl;

import com.fz.admin.dao.MenuDao;
import com.fz.admin.entity.Menu;
import com.fz.admin.entity.MenuEntity;
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
        //添加一级根目录
        List<MenuTreeEntity> tmpList=new ArrayList<>();
        MenuTreeEntity tmpModel=new MenuTreeEntity();
        tmpModel.setLabel("根目录");
        tmpModel.setValue("0");
        tmpModel.setChildren(getChildrenList(id));
        tmpList.add(tmpModel);
        return tmpList;
    }

    @Override
    public List<MenuTableEntity> getMenuListByPid(int pid) {
        return getMenuChildrenList(pid,1);
    }

    @Override
    public MenuEntity getMenuModelById(int id) {
        return menuDao.getMenuModelById(id);
    }

    @Override
    public MenuEntity getMenuModelByPidName(int parentId, String menuName) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("parentId",parentId);
        paramMap.put("menuName",menuName);
        return menuDao.getMenuModelByPidName(paramMap);
    }

    @Override
    public int getMaxOrderNo(int parentId) {
        return menuDao.getMaxOrderNo(parentId);
    }

    @Override
    public int addMenu(MenuEntity menuEntity) {
        return menuDao.addMenu(menuEntity);
    }

    @Override
    public int updateMenu(MenuEntity menuEntity) {
        return menuDao.updateMenu(menuEntity);
    }

    @Override
    public int updateState(MenuEntity menuEntity) {
        return menuDao.updateState(menuEntity);
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
