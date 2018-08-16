package com.fz.admin.entity;

import java.util.List;

public class Menu {
    private int id;
    private String name;
    private String icon;
    private String url;

    public List<Menu> getSubitem() {
        return subitem;
    }

    public void setSubitem(List<Menu> subitem) {
        this.subitem = subitem;
    }

    private List<Menu> subitem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
