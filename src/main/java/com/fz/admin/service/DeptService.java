package com.fz.admin.service;

import com.fz.admin.entity.DeptDragEntity;
import com.fz.admin.entity.DeptEntity;
import com.fz.admin.entity.DeptPostEntity;
import com.fz.admin.entity.DeptTreeEntity;

import java.util.List;

public interface DeptService {
    List<DeptTreeEntity> getDeptTreeList(int id);
    int addDept(DeptEntity deptEntity);
    int updateDept(DeptEntity deptEntity);
    DeptEntity getDeptEntity(int id);
    List<DeptPostEntity> getDeptPostList(int deptId);
    int[] getDeptPostIds(int deptId);
    int addDeptPost(DeptPostEntity deptPostEntity);
    DeptEntity getDeptEntityByName(int parentId,String deptName);
    int getDeptMaxOrderNo(int parentId);
    int updateState(int id,int dataState);
    int updateDragDept(DeptDragEntity deptDragEntity);
    int updateDragDeptOrderNo(DeptDragEntity deptDragEntity);
    int updateDragOldDeptOrderNo(DeptDragEntity deptDragEntity);
}
