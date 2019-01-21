package com.fz.admin.service;

import com.fz.admin.entity.SysLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysLogService {
    Page<SysLog> findByContent(String content, Pageable pageable);
    List<SysLog> searchContent(String strKey);
    boolean add(SysLog sysLog);
    int addLog(SysLog sysLog);
}
