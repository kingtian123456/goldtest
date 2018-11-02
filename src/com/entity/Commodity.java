package com.entity;

import java.util.Date;

public class Commodity {
	
    private Integer comId;//商品id

    private Integer sortId;//分类id
    
    private String comTitle;//商品主题

    private String comIntro;//商品简介

    private String comAuthor;//商品添加人

    private String comKeyword;//商品关键词

    private String comLinkurl;//商品的链接

    private Integer comStock;//商品库存

    private String comPrinturl;//商品图片路径

    private Date comCretime;//商品添加时间
    
    private Sort sort;//商品的上一级对象
    
    public Sort getSort() {
		return sort;
	}

	public void setSort(Sort sort) {
		this.sort = sort;
	}

	public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getComTitle() {
        return comTitle;
    }

    public void setComTitle(String comTitle) {
        this.comTitle = comTitle == null ? null : comTitle.trim();
    }

    public String getComIntro() {
        return comIntro;
    }

    public void setComIntro(String comIntro) {
        this.comIntro = comIntro == null ? null : comIntro.trim();
    }

    public String getComAuthor() {
        return comAuthor;
    }

    public void setComAuthor(String comAuthor) {
        this.comAuthor = comAuthor == null ? null : comAuthor.trim();
    }

    public String getComKeyword() {
        return comKeyword;
    }

    public void setComKeyword(String comKeyword) {
        this.comKeyword = comKeyword == null ? null : comKeyword.trim();
    }

    public String getComLinkurl() {
        return comLinkurl;
    }

    public void setComLinkurl(String comLinkurl) {
        this.comLinkurl = comLinkurl == null ? null : comLinkurl.trim();
    }

    public Integer getComStock() {
        return comStock;
    }

    public void setComStock(Integer comStock) {
        this.comStock = comStock;
    }

    public String getComPrinturl() {
        return comPrinturl;
    }

    public void setComPrinturl(String comPrinturl) {
        this.comPrinturl = comPrinturl == null ? null : comPrinturl.trim();
    }

    public Date getComCretime() {
        return comCretime;
    }

    public void setComCretime(Date comCretime) {
        this.comCretime = comCretime;
    }
}