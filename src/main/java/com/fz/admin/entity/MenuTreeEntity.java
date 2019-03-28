package com.fz.admin.entity;

import java.util.List;

public class MenuTreeEntity {
    private int id;
    private String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<MenuTreeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTreeEntity> children) {
        this.children = children;
    }

    private List<MenuTreeEntity> children;
}
