package com.fz.admin.dao;

import com.fz.admin.entity.DeptDragEntity;
import com.fz.admin.entity.DeptEntity;
import com.fz.admin.entity.DeptPostEntity;
import com.fz.admin.entity.DeptTreeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeptDao {
    List<DeptTreeEntity> getDeptTreeList(@Param("id")int id);

    int addDept(DeptEntity deptEntity);

    int addDeptPost(DeptPostEntity deptPostEntity);

    int updateDept(DeptEntity deptEntity);

    DeptEntity getDeptEntity(@Param("id")int id);

    DeptEntity getDeptEntityByName(Map<String,Object> paramMap);

    int getDeptMaxOrderNo(@Param("parentId")int parentId);

    List<DeptPostEntity> getDeptPostList(@Param("deptId")int deptId);

    int updateState(Map<String,Object> paramMap);

    int updateDragDept(DeptDragEntity deptDragEntity);

    int updateDragDeptOrderNo(DeptDragEntity deptDragEntity);

    int updateDragOldDeptOrderNo(DeptDragEntity deptDragEntity);
}
