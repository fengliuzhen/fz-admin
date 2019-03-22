package com.fz.admin.dao;

import com.fz.admin.entity.Menu;
import com.fz.admin.entity.MenuTableEntity;
import com.fz.admin.entity.MenuTreeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MenuDao {
    List<Menu> getMenuListByUid(@Param("uid")int uid);
    Integer getIsHavePower(Map<String,Object> mapParam);
    List<MenuTreeEntity> getMenuTreeList(@Param("id")int id);
    List<MenuTableEntity> getMenuListByPid(@Param("pid")int pid);
}
