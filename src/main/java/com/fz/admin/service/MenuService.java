package com.fz.admin.service;

import com.fz.admin.entity.Menu;
import com.fz.admin.entity.MenuTableEntity;
import com.fz.admin.entity.MenuTreeEntity;

import java.util.List;

public interface MenuService {
    List<Menu> getMenuListByUid(int uid);
    Integer getIsHavePower(int userId,String menuPath);
    List<MenuTreeEntity> getMenuTreeList(int id);
    List<MenuTableEntity> getMenuListByPid(int pid);
}
