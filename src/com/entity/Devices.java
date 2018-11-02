package com.entity;

import java.util.Date;

public class Devices {
	
    private Integer dev_id;//设备id

    private Integer at_id;//协议id

    private String user_code;//用户编号

    private Integer pro_id;//产品id

    private Integer dvg_id;//群组id

    private String dev_code;//设备编号

    private String dev_vnum;//设备msi码

    private String dev_name;//设备名称

    private String dev_type;//设备类型

    private String dev_profile;//设备Profile

    private Integer dev_islife;//心跳周期

    private Integer dev_ispassca;//是否认证:0/1

    private Integer dev_sumnum;//上报次数
    
    private String dev_forward;//转发配置的IP
    
    private Integer dev_forport;//转发配置的端口

    private Date dev_uptime;//最近上报时间

    private Date dev_intime;//创建时间

    private Date dev_edtime;//修改时间

	public Integer getDev_id() {
		return dev_id;
	}

	public void setDev_id(Integer dev_id) {
		this.dev_id = dev_id;
	}

	public Integer getAt_id() {
		return at_id;
	}

	public void setAt_id(Integer at_id) {
		this.at_id = at_id;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public Integer getPro_id() {
		return pro_id;
	}

	public void setPro_id(Integer pro_id) {
		this.pro_id = pro_id;
	}

	public Integer getDvg_id() {
		return dvg_id;
	}

	public void setDvg_id(Integer dvg_id) {
		this.dvg_id = dvg_id;
	}

	public String getDev_code() {
		return dev_code;
	}

	public void setDev_code(String dev_code) {
		this.dev_code = dev_code;
	}

	public String getDev_vnum() {
		return dev_vnum;
	}

	public void setDev_vnum(String dev_vnum) {
		this.dev_vnum = dev_vnum;
	}

	public String getDev_name() {
		return dev_name;
	}

	public void setDev_name(String dev_name) {
		this.dev_name = dev_name;
	}

	public String getDev_type() {
		return dev_type;
	}

	public void setDev_type(String dev_type) {
		this.dev_type = dev_type;
	}

	public String getDev_profile() {
		return dev_profile;
	}

	public void setDev_profile(String dev_profile) {
		this.dev_profile = dev_profile;
	}

	public Integer getDev_islife() {
		return dev_islife;
	}

	public void setDev_islife(Integer dev_islife) {
		this.dev_islife = dev_islife;
	}

	public Integer getDev_ispassca() {
		return dev_ispassca;
	}

	public void setDev_ispassca(Integer dev_ispassca) {
		this.dev_ispassca = dev_ispassca;
	}

	public Integer getDev_sumnum() {
		return dev_sumnum;
	}

	public void setDev_sumnum(Integer dev_sumnum) {
		this.dev_sumnum = dev_sumnum;
	}
	
	public String getDev_forward() {
		return dev_forward;
	}

	public void setDev_forward(String dev_forward) {
		this.dev_forward = dev_forward;
	}

	public Integer getDev_forport() {
		return dev_forport;
	}

	public void setDev_forport(Integer dev_forport) {
		this.dev_forport = dev_forport;
	}

	public Date getDev_uptime() {
		return dev_uptime;
	}

	public void setDev_uptime(Date dev_uptime) {
		this.dev_uptime = dev_uptime;
	}

	public Date getDev_intime() {
		return dev_intime;
	}

	public void setDev_intime(Date dev_intime) {
		this.dev_intime = dev_intime;
	}

	public Date getDev_edtime() {
		return dev_edtime;
	}

	public void setDev_edtime(Date dev_edtime) {
		this.dev_edtime = dev_edtime;
	}
    
}