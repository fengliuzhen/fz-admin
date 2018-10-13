package com.fz.admin.serviceImpl;

import com.fz.admin.dao.MenuDao;
import com.fz.admin.entity.Menu;
import com.fz.admin.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
