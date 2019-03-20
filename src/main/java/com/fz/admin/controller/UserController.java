package com.fz.admin.controller;

import com.fz.admin.core.APIResultModel;
import com.fz.admin.core.EncryptCore;
import com.fz.admin.entity.AddUserEntity;
import com.fz.admin.entity.LogEnum;
import com.fz.admin.entity.SysLog;
import com.fz.admin.entity.UserEntity;
import com.fz.admin.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/searchlist",method = RequestMethod.POST,consumes="application/json")
    public Map<String, Object> SearchUserList(@RequestBody UserEntity userEntity)
    {
        PageInfo<UserEntity> page=userService.getUserList(userEntity);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("datalist", page.getList());
        map.put("number", page.getTotal());
        return map;
    }
    @RequestMapping(value="/save",method = RequestMethod.POST,consumes="application/json")
    public APIResultModel<String> saveUser(@RequestBody AddUserEntity addUserEntity) throws Exception
    {
        APIResultModel<String> resultModel=new APIResultModel<>();
        //验证用户名是否重复
        UserEntity userEntity=userService.getUserModelByName(addUserEntity.getUserName());
        if(!Objects.equals(userEntity,null))
        {
            if(addUserEntity.getUserId()<=0||userEntity.getUserId()!=addUserEntity.getUserId()) {
                resultModel.setError("300", "用户名已存在");
                return resultModel;
            }
        }
        if(addUserEntity.getUserId()<=0) {
            addUserEntity.setPassWord(EncryptCore.MD5(addUserEntity.getPassWord()));
            addUserEntity.setAddUid(getUserId());
            addUserEntity.setDataState(1);
            addUserEntity.setIsLock(0);
            if(addUserEntity.getNickName().isEmpty())
            {
                addUserEntity.setNickName(addUserEntity.getUserName());
            }
            int newId = userService.addUser(addUserEntity);
            if (newId > 0) {
                resultModel.setSuccess("200", "新增成功");
            } else {
                resultModel.setError("300", "新增失败");
            }
        }
        else
        {
            addUserEntity.setLastEditUid(getUserId());
            int rows=userService.updateUser(addUserEntity);
            if (rows > 0) {
                resultModel.setSuccess("200", "更新成功");
            } else {
                resultModel.setError("300", "更新失败");
            }
        }
        return resultModel;
    }
    @RequestMapping(value="/get/{userid}",method = RequestMethod.GET)
    public APIResultModel<UserEntity> getUser(@PathVariable("userid")int userid) throws Exception
    {
        APIResultModel<UserEntity> resultModel=new APIResultModel<>();
        //验证用户名是否重复
        if(userid==-1)
        {
            userid=GetCureenUser().getUserId();
        }
        UserEntity userEntity=userService.getUserBaseInfo(userid);
        resultModel.setData(userEntity);
        resultModel.setSuccess("200","success");
        return resultModel;
    }
    @RequestMapping(value="/userlist/roleid",method = RequestMethod.POST,consumes ="application/json")
    public Map<String, Object> getUserByRoleId(@RequestBody UserEntity userEntity) throws Exception
    {
        PageInfo<UserEntity> pageList=userService.getUserByRoleId(userEntity);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("datalist", pageList.getList());
        map.put("number", pageList.getTotal());
        return map;
    }
    @RequestMapping(value="/udpatelock",method = RequestMethod.POST,consumes="application/json")
    public APIResultModel<Integer> UpdateLock(@RequestBody AddUserEntity addUserEntity) throws Exception
    {
        APIResultModel<Integer> resultModel=new APIResultModel<>();
        UserEntity userEntity=GetCureenUser();
        SysLog sysLog=new SysLog();
        sysLog.setLogType(LogEnum.user.getCode());
        UserEntity userEntityNew=userService.getUserModelById(addUserEntity.getUserId());
        //sysLog.setId(addUserEntity.getUserId());
        sysLog.setMid(addUserEntity.getUserId());
        sysLog.setContent(userEntityNew.getIsLock()>0?"解锁":"锁定");
        sysLog.setUid(userEntity.getUserId());
        sysLog.setuName(userEntity.getRealName());
        int rows=userService.updateLock(sysLog);
        resultModel.setData(rows);
        resultModel.setCode(rows>0?"200":"300");
        resultModel.setMessage(rows>0?"成功":"失败");
        return resultModel;
    }
    @RequestMapping(value="/udpatepwd",method = RequestMethod.POST,consumes="application/json")
    public APIResultModel<Integer> UpdatePwd(@RequestBody  AddUserEntity addUserEntity) throws Exception
    {
        APIResultModel<Integer> resultModel=new APIResultModel<>();
        addUserEntity.setUserId(GetCureenUser().getUserId());
        if(Objects.equals(addUserEntity,null)||addUserEntity.getUserId()<=0)
        {
            resultModel.setError("300", "未知的用户");
            return resultModel;
        }
        addUserEntity.setPassWord(EncryptCore.MD5(addUserEntity.getPassWord()));
        int ret=userService.updatePwd(addUserEntity);
        if(ret>0) {
            resultModel.setSuccess("200", "修改成功");
        }
        else
        {
            resultModel.setError("300", "修改失败");
        }
        return resultModel;
    }
}
