package com.entity;

import java.util.Date;

public class Sort {
    private Integer sortId;

    private Integer sortDepth;

    private Integer sortType;

    private Integer sortParentid;

    private String sortName;

    private Integer sortStats;

    private String sortSeachkey;

    private Integer sortOrders;

    private String sortRemark;

    private String sortDir;

    private String sortLinkurl;

    private String sortContent;

    private Integer sortIsshow;

    private Date sortUptime;

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Integer getSortDepth() {
        return sortDepth;
    }

    public void setSortDepth(Integer sortDepth) {
        this.sortDepth = sortDepth;
    }

    public Integer getSortType() {
        return sortType;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public Integer getSortParentid() {
        return sortParentid;
    }

    public void setSortParentid(Integer sortParentid) {
        this.sortParentid = sortParentid;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName == null ? null : sortName.trim();
    }

    public Integer getSortStats() {
        return sortStats;
    }

    public void setSortStats(Integer sortStats) {
        this.sortStats = sortStats;
    }

    public String getSortSeachkey() {
        return sortSeachkey;
    }

    public void setSortSeachkey(String sortSeachkey) {
        this.sortSeachkey = sortSeachkey == null ? null : sortSeachkey.trim();
    }

    public Integer getSortOrders() {
        return sortOrders;
    }

    public void setSortOrders(Integer sortOrders) {
        this.sortOrders = sortOrders;
    }

    public String getSortRemark() {
        return sortRemark;
    }

    public void setSortRemark(String sortRemark) {
        this.sortRemark = sortRemark == null ? null : sortRemark.trim();
    }

    public String getSortDir() {
        return sortDir;
    }

    public void setSortDir(String sortDir) {
        this.sortDir = sortDir == null ? null : sortDir.trim();
    }

    public String getSortLinkurl() {
        return sortLinkurl;
    }

    public void setSortLinkurl(String sortLinkurl) {
        this.sortLinkurl = sortLinkurl == null ? null : sortLinkurl.trim();
    }

    public String getSortContent() {
        return sortContent;
    }

    public void setSortContent(String sortContent) {
        this.sortContent = sortContent == null ? null : sortContent.trim();
    }

    public Integer getSortIsshow() {
        return sortIsshow;
    }

    public void setSortIsshow(Integer sortIsshow) {
        this.sortIsshow = sortIsshow;
    }

    public Date getSortUptime() {
        return sortUptime;
    }

    public void setSortUptime(Date sortUptime) {
        this.sortUptime = sortUptime;
    }
}