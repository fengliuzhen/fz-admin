package com.fz.admin.controller;

import com.fz.admin.core.APIResultModel;
import com.fz.admin.core.EncryptCore;
import com.fz.admin.entity.AddUserEntity;
import com.fz.admin.entity.UserEntity;
import com.fz.admin.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/searchlist",method = RequestMethod.POST,consumes="application/json")
    public Map<String, Object> SearchGoodsList(@RequestBody UserEntity userEntity)
    {
        PageInfo<UserEntity> page=userService.getUserList(userEntity);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("datalist", page.getList());
        map.put("number", page.getTotal());
        return map;
    }
    @RequestMapping(value="/add",method = RequestMethod.POST,consumes="application/json")
    public APIResultModel<String> SyncGoods(@RequestBody AddUserEntity addUserEntity) throws Exception
    {
        APIResultModel<String> resultModel=new APIResultModel<>();
        //验证用户名是否重复
        UserEntity userEntity=userService.getUserModelByName(addUserEntity.getUserName());
        if(!Objects.equals(userEntity,null))
        {
            resultModel.setError("300","用户名已存在");
            return resultModel;
        }
        addUserEntity.setPassWord(EncryptCore.MD5(addUserEntity.getPassWord()));
        addUserEntity.setAddUid(getUserId());
        addUserEntity.setDataState(1);
        addUserEntity.setIsLock(0);
        int newId=userService.addUser(addUserEntity);
        if(newId>0)
        {
            resultModel.setSuccess("200","新增成功");
        }
        else
        {
            resultModel.setError("300","新增失败");
        }
        return resultModel;
    }
}
