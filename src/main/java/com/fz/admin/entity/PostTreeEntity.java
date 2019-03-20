package com.fz.admin.entity;

import java.util.List;

/**
 * 部门tree
 */
public class PostTreeEntity {
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

    public List<PostTreeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<PostTreeEntity> children) {
        this.children = children;
    }

    private List<PostTreeEntity> children;
}
