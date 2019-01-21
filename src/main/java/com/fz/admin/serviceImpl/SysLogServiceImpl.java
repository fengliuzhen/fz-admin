package com.fz.admin.serviceImpl;

import com.fz.admin.core.ConvertCore;
import com.fz.admin.dao.LogEsDao;
import com.fz.admin.dao.SysLogDao;
import com.fz.admin.entity.SysLog;
import com.fz.admin.service.SysLogService;
import com.google.common.collect.Lists;
import org.apache.lucene.search.Query;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryShardContext;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    @Autowired
    private LogEsDao logEsDao;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Page<SysLog> findByContent(String content, Pageable pageable)
    {
        QueryBuilder queryBuilder= QueryBuilders.queryStringQuery(content).field("content");
        Page<SysLog> list=logEsDao.search(queryBuilder,pageable);
        return  list;
    }

    @Override
    public List<SysLog> searchContent(String strKey)
    {
        QueryBuilder queryBuilder= QueryBuilders.queryStringQuery(strKey).field("content");
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.preTags("<span style='color:red;'>");
        highlightBuilder.postTags("</span>");
        highlightBuilder.field("content");

        SearchResponse response=elasticsearchTemplate.getClient().prepareSearch("fz").setQuery(queryBuilder).highlighter(highlightBuilder).execute().actionGet();
        SearchHits hit=response.getHits();
        List<SysLog> sysLogList= Lists.newArrayList();
        SysLog sysLog;
        for(SearchHit searchHit:hit)
        {
            sysLog=new SysLog();
            Map<String, Object> entityMap = searchHit.getSourceAsMap();
            sysLog.setContent(searchHit.getHighlightFields().get("content").getFragments()[0].toString());
            sysLog.setId(ConvertCore.ObjToInt(entityMap.get("id")));
            sysLog.setLogType(ConvertCore.ObjToInt(entityMap.get("logType")));
            sysLogList.add(sysLog);
        }
        return sysLogList;
    }
    @Override
    public boolean add(SysLog sysLog) {
        logEsDao.save(sysLog);
        return true;
    }

    @Override
    public int addLog(SysLog sysLog) {
        return sysLogDao.addLog(sysLog);
    }
}
