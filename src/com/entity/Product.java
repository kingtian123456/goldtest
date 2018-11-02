package com.entity;

import java.util.Date;

public class Product {
    private Integer pro_id;//产品id

    private String user_code;//用户编号

    private String pro_name;//产品名称

    private String pro_text;//产品说明

    private Integer pro_model;//产品形式:1想法2原型3工程样机

    private Integer pro_devb;//开发板选择

    private Integer pro_sensor;//传感器选择

    private Integer pro_industry;//行业

    private Integer pro_type;//种类

    private Integer pro_bat;//运营商

    private Integer pro_contype;//连接方式

    private Integer pro_protocol;//通讯协议

    private Integer dvg_devnum;//产品设备数量

    private Date pro_intime;//创建时间

    private Date pro_edtime;//修改时间
    
    private Agreement ament;//协议对象
    
    private Classfiy fiy;//开发板对象
    
	public Classfiy getFiy() {
		return fiy;
	}

	public void setFiy(Classfiy fiy) {
		this.fiy = fiy;
	}

	public Agreement getAment() {
		return ament;
	}

	public void setAment(Agreement ament) {
		this.ament = ament;
	}

	public Integer getPro_id() {
		return pro_id;
	}

	public void setPro_id(Integer pro_id) {
		this.pro_id = pro_id;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getPro_name() {
		return pro_name;
	}

	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}

	public String getPro_text() {
		return pro_text;
	}

	public void setPro_text(String pro_text) {
		this.pro_text = pro_text;
	}

	public Integer getPro_model() {
		return pro_model;
	}

	public void setPro_model(Integer pro_model) {
		this.pro_model = pro_model;
	}

	public Integer getPro_devb() {
		return pro_devb;
	}

	public void setPro_devb(Integer pro_devb) {
		this.pro_devb = pro_devb;
	}

	public Integer getPro_sensor() {
		return pro_sensor;
	}

	public void setPro_sensor(Integer pro_sensor) {
		this.pro_sensor = pro_sensor;
	}

	public Integer getPro_industry() {
		return pro_industry;
	}

	public void setPro_industry(Integer pro_industry) {
		this.pro_industry = pro_industry;
	}

	public Integer getPro_type() {
		return pro_type;
	}

	public void setPro_type(Integer pro_type) {
		this.pro_type = pro_type;
	}

	public Integer getPro_bat() {
		return pro_bat;
	}

	public void setPro_bat(Integer pro_bat) {
		this.pro_bat = pro_bat;
	}

	public Integer getPro_contype() {
		return pro_contype;
	}

	public void setPro_contype(Integer pro_contype) {
		this.pro_contype = pro_contype;
	}

	public Integer getPro_protocol() {
		return pro_protocol;
	}

	public void setPro_protocol(Integer pro_protocol) {
		this.pro_protocol = pro_protocol;
	}

	public Integer getDvg_devnum() {
		return dvg_devnum;
	}

	public void setDvg_devnum(Integer dvg_devnum) {
		this.dvg_devnum = dvg_devnum;
	}

	public Date getPro_intime() {
		return pro_intime;
	}

	public void setPro_intime(Date pro_intime) {
		this.pro_intime = pro_intime;
	}

	public Date getPro_edtime() {
		return pro_edtime;
	}

	public void setPro_edtime(Date pro_edtime) {
		this.pro_edtime = pro_edtime;
	}
    
    
}