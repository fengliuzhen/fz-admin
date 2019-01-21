package com.fz.admin.entity;

/**
 * 日志类型
 */
public enum LogEnum {
    system(1,"系统日志"),
    user(2,"用户日志"),
    menu(3,"菜单日志"),
    role(4,"角色日志"),
    power(5,"权限日志");

    private int code;
    private String name;

    LogEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
