package com.entity;

import java.util.Date;
import java.util.List;

public class Message {
    private Integer msId;//消息ID

    private Integer newsId;//主题ID

    private String userCode;//用户编码

    private String msContent;//消息内容

    private Integer msLayerid;//上一级ID

    private Date msCretime;//创建消息的时间
    
    private List<Message> list;//下一级消息体
    
    private User user;//绑定用户
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Message> getList() {
		return list;
	}

	public void setList(List<Message> list) {
		this.list = list;
	}

	public Integer getMsId() {
        return msId;
    }

    public void setMsId(Integer msId) {
        this.msId = msId;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getMsContent() {
        return msContent;
    }

    public void setMsContent(String msContent) {
        this.msContent = msContent == null ? null : msContent.trim();
    }

    public Integer getMsLayerid() {
        return msLayerid;
    }

    public void setMsLayerid(Integer msLayerid) {
        this.msLayerid = msLayerid;
    }

    public Date getMsCretime() {
        return msCretime;
    }

    public void setMsCretime(Date msCretime) {
        this.msCretime = msCretime;
    }
}