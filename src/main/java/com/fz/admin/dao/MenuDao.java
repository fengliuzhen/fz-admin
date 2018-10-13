package com.fz.admin.dao;

import com.fz.admin.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MenuDao {
    List<Menu> getMenuListByUid(@Param("uid")int uid);
    Integer getIsHavePower(Map<String,Object> mapParam);
}
