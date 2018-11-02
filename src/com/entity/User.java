package com.entity;

import java.util.Date;

public class User {
	
    private Integer user_id;

    private String user_code;

    private String user_account;

    private String user_password;

    private String user_name;

    private Integer user_age;

    private Integer user_sex;

    private String user_mobile;

    private String user_mail;

    private Date user_intime;

    private Date user_logintime;

    private Integer user_loginnum;
    
    private Integer user_authority;
    
    private Integer user_auth;

    private String user_IP;

    private Date user_uptime;
    
	public Integer getUser_auth() {
		return user_auth;
	}

	public void setUser_auth(Integer user_auth) {
		this.user_auth = user_auth;
	}

	public Integer getUser_authority() {
		return user_authority;
	}

	public void setUser_authority(Integer user_authority) {
		this.user_authority = user_authority;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getUser_age() {
		return user_age;
	}

	public void setUser_age(Integer user_age) {
		this.user_age = user_age;
	}

	public Integer getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public Date getUser_intime() {
		return user_intime;
	}

	public void setUser_intime(Date user_intime) {
		this.user_intime = user_intime;
	}

	public Date getUser_logintime() {
		return user_logintime;
	}

	public void setUser_logintime(Date user_logintime) {
		this.user_logintime = user_logintime;
	}

	public Integer getUser_loginnum() {
		return user_loginnum;
	}

	public void setUser_loginnum(Integer user_loginnum) {
		this.user_loginnum = user_loginnum;
	}

	public String getUser_IP() {
		return user_IP;
	}

	public void setUser_IP(String user_IP) {
		this.user_IP = user_IP;
	}

	public Date getUser_uptime() {
		return user_uptime;
	}

	public void setUser_uptime(Date user_uptime) {
		this.user_uptime = user_uptime;
	} 
}