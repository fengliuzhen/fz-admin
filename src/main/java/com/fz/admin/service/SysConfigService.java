package com.fz.admin.service;

import com.fz.admin.entity.SysConfig;

public interface SysConfigService {
    SysConfig getEntityById(int id);
    SysConfig getEntityByKey(String itemKey);
}
