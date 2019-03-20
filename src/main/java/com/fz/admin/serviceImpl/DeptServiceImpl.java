package com.fz.admin.serviceImpl;

import com.fz.admin.core.DateCore;
import com.fz.admin.dao.DeptDao;
import com.fz.admin.entity.DeptDragEntity;
import com.fz.admin.entity.DeptEntity;
import com.fz.admin.entity.DeptPostEntity;
import com.fz.admin.entity.DeptTreeEntity;
import com.fz.admin.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<DeptTreeEntity> getDeptTreeList(int id) {
        return getChildrenList(id);
    }

    @Override
    public int addDept(DeptEntity deptEntity) {
        int tmpStamp=DateCore.getDateStamp();
        deptEntity.setAddTime(tmpStamp);
        deptEntity.setLastEditTime(tmpStamp);
        deptEntity.setLastEditUid(deptEntity.getAddUid());
        deptEntity.setDataState(1);
        int rows=deptDao.addDept(deptEntity);
        if(rows>0) {
            return deptEntity.getId();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public int updateDept(DeptEntity deptEntity) {
        deptEntity.setLastEditTime(DateCore.getDateStamp());
        int rows=deptDao.updateDept(deptEntity);
        return rows;
    }

    @Override
    public DeptEntity getDeptEntity(int id) {
        return deptDao.getDeptEntity(id);
    }

    @Override
    public List<DeptPostEntity> getDeptPostList(int deptId) {
        return deptDao.getDeptPostList(deptId);
    }

    @Override
    public int[] getDeptPostIds(int deptId) {
        List<DeptPostEntity> deptPostEntityList=deptDao.getDeptPostList(deptId);
        int[] ids=deptPostEntityList.stream().mapToInt(DeptPostEntity::getPostId).toArray();
        return ids;
    }

    @Override
    public int addDeptPost(DeptPostEntity deptPostEntity) {
        return deptDao.addDeptPost(deptPostEntity);
    }

    @Override
    public DeptEntity getDeptEntityByName(int parentId, String deptName) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("parentId",parentId);
        paramMap.put("deptName",deptName);
        return deptDao.getDeptEntityByName(paramMap);
    }

    @Override
    public int getDeptMaxOrderNo(int parentId) {
        return deptDao.getDeptMaxOrderNo(parentId);
    }

    @Override
    public int updateState(int id,int dataState) {
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("id",id);
        paramMap.put("datastate",dataState);
        return deptDao.updateState(paramMap);
    }

    @Override
    @Transactional
    public int updateDragDept(DeptDragEntity deptDragEntity) {
        int row1=deptDao.updateDragDept(deptDragEntity);
        if(deptDragEntity.getDragNodeOrderNo()>0)
        {
               deptDao.updateDragOldDeptOrderNo(deptDragEntity);
        }
        int row2=deptDao.updateDragDeptOrderNo(deptDragEntity);

        return row1+row2;
    }

    @Override
    public int updateDragDeptOrderNo(DeptDragEntity deptDragEntity) {
        return deptDao.updateDragDeptOrderNo(deptDragEntity);
    }

    @Override
    public int updateDragOldDeptOrderNo(DeptDragEntity deptDragEntity) {
        return deptDao.updateDragOldDeptOrderNo(deptDragEntity);
    }

    private List<DeptTreeEntity> getChildrenList(int parentId)
    {
        List<DeptTreeEntity> tmpList=deptDao.getDeptTreeList(parentId);
        if(!Objects.equals(tmpList,null)&&tmpList.size()>0)
        {
            for(DeptTreeEntity tmpItem:tmpList)
            {
                tmpItem.setChildren(getChildrenList(tmpItem.getId()));
            }
        }
        return tmpList;
    }
}
