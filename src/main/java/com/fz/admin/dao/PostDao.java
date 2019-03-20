package com.fz.admin.dao;

import com.fz.admin.entity.PostDragEntity;
import com.fz.admin.entity.PostEntity;
import com.fz.admin.entity.PostTreeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PostDao {
    List<PostTreeEntity> getPostTreeList(@Param("id") int id);

    int addPost(PostEntity postEntity);

    int updatePost(PostEntity postEntity);

    PostEntity getPostEntity(@Param("id") int id);

    PostEntity getPostEntityByName(Map<String, Object> paramMap);

    int getPostMaxOrderNo(@Param("parentId") int parentId);

    String getPostIdsByDeptId(@Param("deptId") int deptId);

    int updateState(Map<String, Object> paramMap);

    int updateDragPost(PostDragEntity postDragEntity);

    int updateDragPostOrderNo(PostDragEntity postDragEntity);
    int updateDragOldPostOrderNo(PostDragEntity postDragEntity);
}
