package com.entity;

import java.util.Date;

public class Agreement {
	
    private Integer at_id;

    private String at_name;

    private String at_text;

    private String at_inip;

    private String at_port;

    private Date at_intime;

	public Integer getAt_id() {
		return at_id;
	}

	public void setAt_id(Integer at_id) {
		this.at_id = at_id;
	}

	public String getAt_name() {
		return at_name;
	}

	public void setAt_name(String at_name) {
		this.at_name = at_name;
	}

	public String getAt_text() {
		return at_text;
	}

	public void setAt_text(String at_text) {
		this.at_text = at_text;
	}

	public String getAt_inip() {
		return at_inip;
	}

	public void setAt_inip(String at_inip) {
		this.at_inip = at_inip;
	}

	public String getAt_port() {
		return at_port;
	}

	public void setAt_port(String at_port) {
		this.at_port = at_port;
	}

	public Date getAt_intime() {
		return at_intime;
	}

	public void setAt_intime(Date at_intime) {
		this.at_intime = at_intime;
	}

    
}