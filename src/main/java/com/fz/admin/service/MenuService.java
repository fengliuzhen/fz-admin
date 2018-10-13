package com.fz.admin.service;

import com.fz.admin.entity.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> getMenuListByUid(int uid);
    Integer getIsHavePower(int userId,String menuPath);
}
