package com.fz.admin.entity;

import java.util.List;

/**
 * 部门tree
 */
public class RoleTreeEntity {
    private int id;
    private String label;

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

    public List<RoleTreeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<RoleTreeEntity> children) {
        this.children = children;
    }

    private List<RoleTreeEntity> children;
}
