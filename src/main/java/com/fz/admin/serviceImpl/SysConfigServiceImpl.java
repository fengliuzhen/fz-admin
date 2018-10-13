package com.fz.admin.serviceImpl;

import com.fz.admin.dao.SysConfigDao;
import com.fz.admin.entity.SysConfig;
import com.fz.admin.service.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sysConfigServiceImpl")
public class SysConfigServiceImpl implements SysConfigService {

    @Autowired
    private SysConfigDao sysConfigDao;

    public SysConfig getEntityById(int id)
    {
        return sysConfigDao.getEntityById(id);
    }
    public SysConfig getEntityByKey(String itemKey)
    {
        return sysConfigDao.getEntityByKey(itemKey);
    }
}
