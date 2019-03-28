package com.fz.admin.entity;

public class MenuEntity extends MenuTableEntity {
    private int adduid;
    private int lastedittime;

    public String getPids() {
        return pids;
    }

    public void setPids(String pids) {
        this.pids = pids;
    }

    private String pids;
    public int getDatastate() {
        return datastate;
    }

    public void setDatastate(int datastate) {
        this.datastate = datastate;
    }

    private int datastate;
    public int getAdduid() {
        return adduid;
    }

    public void setAdduid(int adduid) {
        this.adduid = adduid;
    }

    public int getLastedittime() {
        return lastedittime;
    }

    public void setLastedittime(int lastedittime) {
        this.lastedittime = lastedittime;
    }

    public int getLastedituid() {
        return lastedituid;
    }

    public void setLastedituid(int lastedituid) {
        this.lastedituid = lastedituid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private int lastedituid;
    private String remark;
}
