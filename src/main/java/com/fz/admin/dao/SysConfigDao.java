package com.fz.admin.dao;

import com.fz.admin.entity.SysConfig;
import org.apache.ibatis.annotations.Param;

public interface SysConfigDao {
    SysConfig getEntityById(@Param("id")int id);
    SysConfig getEntityByKey(@Param("itemKey")String itemKey);
}
