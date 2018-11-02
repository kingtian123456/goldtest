package com.entity;

import java.util.Date;

public class Download {
	private Integer doId;//文件ID

    private String doName;//文件名称

    private String doTitle;//文件标题
    
    private String doCode;//文件提取码

    private String doIntron;//文件简介

    private String doLoadurl;//文件外部下载地址

    private Integer doLoadnum;//文件的下载数
    
    private Date doCreatime;//文件上传时间

    private Date doUptime;//文件的修改时间

    public Integer getDoId() {
        return doId;
    }

    public void setDoId(Integer doId) {
        this.doId = doId;
    }

    public String getDoName() {
        return doName;
    }

    public void setDoName(String doName) {
        this.doName = doName == null ? null : doName.trim();
    }

    public String getDoTitle() {
        return doTitle;
    }

    public void setDoTitle(String doTitle) {
        this.doTitle = doTitle == null ? null : doTitle.trim();
    }

    public String getDoCode() {
        return doCode;
    }

    public void setDoCode(String doCode) {
        this.doCode = doCode == null ? null : doCode.trim();
    }

    public String getDoIntron() {
        return doIntron;
    }

    public void setDoIntron(String doIntron) {
        this.doIntron = doIntron == null ? null : doIntron.trim();
    }

    public String getDoLoadurl() {
        return doLoadurl;
    }

    public void setDoLoadurl(String doLoadurl) {
        this.doLoadurl = doLoadurl == null ? null : doLoadurl.trim();
    }

    public Integer getDoLoadnum() {
        return doLoadnum;
    }

    public void setDoLoadnum(Integer doLoadnum) {
        this.doLoadnum = doLoadnum;
    }

    public Date getDoCreatime() {
        return doCreatime;
    }

    public void setDoCreatime(Date doCreatime) {
        this.doCreatime = doCreatime;
    }

    public Date getDoUptime() {
        return doUptime;
    }

    public void setDoUptime(Date doUptime) {
        this.doUptime = doUptime;
    }
}