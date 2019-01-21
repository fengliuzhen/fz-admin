package com.fz.admin.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.Date;

// indexName ：索引名字（对应mysql的数据库名字）
//type:类型（对应mysql的表名）
@Document(indexName = "fz",type = "syslog", shards = 1,replicas = 0, refreshInterval = "-1")
public class SysLog {
    @Id
    private long id;
    @Field
    private int logType;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    @Field
    private int mid;
    @Field
    private String content;
    @Field
    private Date logTime;
    @Field
    private int uid;
    @Field
    private String uName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getLogType() {
        return logType;
    }

    public void setLogType(int logType) {
        this.logType = logType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }
}
