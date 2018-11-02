package util;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月8日 下午1:48:52
* 位置类说明
*/


public class Location {
	
	private String code;//设备编号
	
	private String macode;//设备ID
	
	private String lon;//设备经度
	
	private String lat;//设备纬度
	
	private String state; //设备状态
	
	private String time;//数据上报的时间
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMacode() {
		return macode;
	}

	public void setMacode(String macode) {
		this.macode = macode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
