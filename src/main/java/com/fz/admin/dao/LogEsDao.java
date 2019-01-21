package com.fz.admin.dao;

import com.fz.admin.entity.SysLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface LogEsDao extends ElasticsearchRepository<SysLog,Long> {
}
