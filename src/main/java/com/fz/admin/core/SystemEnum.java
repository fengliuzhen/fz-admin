package com.fz.admin.core;

public enum SystemEnum {
    Admin_Sid("后台管理员SessionId","FZ-ADMIN-SID-"),
    Valid_Code("验证码SessionId","FZ-VCODE-"),
    CookieName("临时保存Cookie","FZ-ADMIN-COOKIE-SESSIONID"),
    UserInfo("用户信息缓存","FZ-ADMIN-CACHE-");

    private String key ;
    private String value ;

    private SystemEnum( String key , String value ){
        this.key = key ;
        this.value = value ;
    }

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
