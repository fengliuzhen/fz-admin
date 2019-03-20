package com.fz.admin.service;

import com.fz.admin.entity.PostDragEntity;
import com.fz.admin.entity.PostEntity;
import com.fz.admin.entity.PostTreeEntity;

import java.util.List;

public interface PostService {
    List<PostTreeEntity> getPostTreeList(int id);
    int addPost(PostEntity postEntity);
    int updatePost(PostEntity postEntity);
    PostEntity getPostEntity(int id);

    PostEntity getPostEntityByName(int parentId, String postName);
    int getPostMaxOrderNo(int parentId);
    String getPostIdsByDeptId(int deptId);
    int updateState(int id, int dataState);
    int updateDragPost(PostDragEntity postDragEntity);
    int updateDragPostOrderNo(PostDragEntity postDragEntity);
    int updateDragOldPostOrderNo(PostDragEntity postDragEntity);
}
