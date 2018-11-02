package com.entity;

import java.util.Date;
import java.util.List;

public class News {
	
    private Integer newsId;//文章ID

    private Integer sortId;//所属类别ID

    private String newsTitle;//文章标题

    private String newsIntro;//简介

    private String newsAuthor;//作者

    private String newsCopyfrom;//来源

    private String newsKeyword;//关键字

    private String newsDefaultpicurl;//首页图片

    private String newsUser;//编辑人

    private Integer newsOrders;//排序

    private Integer newsHits;//点击数

    private Integer newsDeleted;//0删除1保存

    private Integer newsPassed;//0不显示1显示

    private Integer newsOntop;//固定1，0不固定

    private Date newsCreatetime;//生成时间

    private Date newsUpdatetime;//更改时间

    private String newsContent;//文章内容
    
    private Integer comments;//文章评论数
    
    private List<String> list;//关键字分割成list
   
    public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Integer getComments() {
		return comments;
	}

	public void setComments(Integer comments) {
		this.comments = comments;
	}

	public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle == null ? null : newsTitle.trim();
    }

    public String getNewsIntro() {
        return newsIntro;
    }

    public void setNewsIntro(String newsIntro) {
        this.newsIntro = newsIntro == null ? null : newsIntro.trim();
    }

    public String getNewsAuthor() {
        return newsAuthor;
    }

    public void setNewsAuthor(String newsAuthor) {
        this.newsAuthor = newsAuthor == null ? null : newsAuthor.trim();
    }

    public String getNewsCopyfrom() {
        return newsCopyfrom;
    }

    public void setNewsCopyfrom(String newsCopyfrom) {
        this.newsCopyfrom = newsCopyfrom == null ? null : newsCopyfrom.trim();
    }

    public String getNewsKeyword() {
        return newsKeyword;
    }

    public void setNewsKeyword(String newsKeyword) {
        this.newsKeyword = newsKeyword == null ? null : newsKeyword.trim();
    }

    public String getNewsDefaultpicurl() {
        return newsDefaultpicurl;
    }

    public void setNewsDefaultpicurl(String newsDefaultpicurl) {
        this.newsDefaultpicurl = newsDefaultpicurl == null ? null : newsDefaultpicurl.trim();
    }

    public String getNewsUser() {
        return newsUser;
    }

    public void setNewsUser(String newsUser) {
        this.newsUser = newsUser == null ? null : newsUser.trim();
    }

    public Integer getNewsOrders() {
        return newsOrders;
    }

    public void setNewsOrders(Integer newsOrders) {
        this.newsOrders = newsOrders;
    }

    public Integer getNewsHits() {
        return newsHits;
    }

    public void setNewsHits(Integer newsHits) {
        this.newsHits = newsHits;
    }

    public Integer getNewsDeleted() {
        return newsDeleted;
    }

    public void setNewsDeleted(Integer newsDeleted) {
        this.newsDeleted = newsDeleted;
    }

    public Integer getNewsPassed() {
        return newsPassed;
    }

    public void setNewsPassed(Integer newsPassed) {
        this.newsPassed = newsPassed;
    }

    public Integer getNewsOntop() {
        return newsOntop;
    }

    public void setNewsOntop(Integer newsOntop) {
        this.newsOntop = newsOntop;
    }

    public Date getNewsCreatetime() {
        return newsCreatetime;
    }

    public void setNewsCreatetime(Date newsCreatetime) {
        this.newsCreatetime = newsCreatetime;
    }

    public Date getNewsUpdatetime() {
        return newsUpdatetime;
    }

    public void setNewsUpdatetime(Date newsUpdatetime) {
        this.newsUpdatetime = newsUpdatetime;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent == null ? null : newsContent.trim();
    }
}