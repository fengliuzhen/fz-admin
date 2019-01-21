package com.fz.admin.controller;

import com.fz.admin.core.DateCore;
import com.fz.admin.entity.SysLog;
import com.fz.admin.service.SysLogService;
import org.elasticsearch.action.*;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeAction;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeRequestBuilder;
import org.elasticsearch.action.admin.indices.analyze.AnalyzeResponse;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.threadpool.ThreadPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Page;

import java.util.List;

@Controller
public class SysLogController extends BaseController {

    @Autowired
    private SysLogService sysLogService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping(value = "/searchlog",method = RequestMethod.GET)
    @ResponseBody
    public Page<SysLog> searchLog(String content, @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                                  @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize)
    {
        getAnalyzerWord(content);
        Pageable pageable =PageRequest.of(pageIndex,pageSize);
        Page<SysLog> list=sysLogService.findByContent(content,pageable);
        return list;
    }
    @RequestMapping(value = "/searchkeylog",method = RequestMethod.GET)
    @ResponseBody
    public List<SysLog> searchKeyLog(String content, @RequestParam(value="pageIndex",required=false,defaultValue="0") int pageIndex,
                                  @RequestParam(value="pageSize",required=false,defaultValue="10") int pageSize)
    {
        List<SysLog> list=sysLogService.searchContent(content);
        return list;
    }
    /*
    分词
     */
    public String getAnalyzerWord(String strKey)
    {
        AnalyzeRequestBuilder ikRequest = new AnalyzeRequestBuilder(elasticsearchTemplate.getClient(),AnalyzeAction.INSTANCE,"fz",strKey);
        ikRequest.setAnalyzer("ik_max_word");
        List<AnalyzeResponse.AnalyzeToken> ikTokenList = ikRequest.execute().actionGet().getTokens();
        return "";
    }

    //@RequestMapping("/addlog")
    public String addIndex()
    {
        SysLog sysLog=new SysLog();
        sysLog.setId(4);
        sysLog.setContent("习近平主席邀请外国领导人登上复兴号高铁的模拟驾驶台");
        sysLog.setLogTime(DateCore.getNowDate());
        sysLog.setLogType(1);
        sysLog.setUid(2);
        sysLog.setuName("system2");
        sysLogService.add(sysLog);
        return "";
    }
}
