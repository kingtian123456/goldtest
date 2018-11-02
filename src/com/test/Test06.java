package com.test;

import java.util.HashMap;
import java.util.Map;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月13日 上午10:23:26
* 类说明
*/
public class Test06 {
		
	static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
	
	// GPS转高德
	public static Map<String,Object> transform(double wglat, double wglon)
	{
	    double a = 6378245.0;
	    double ee = 0.00669342162296594323;


	    double dlat = transformlat(wglon - 105.0, wglat - 35.0);
	    double dlon = transformlon(wglon - 105.0, wglat - 35.0);
	    double radlat = wglat / 180.0 * Math.PI;
	    double magic = Math.sin(radlat);


	    magic = 1 - ee * magic * magic;
	    double sqrtmagic = Math.sqrt(magic);


	    dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * Math.PI);
	    dlon = (dlon * 180.0) / (a / sqrtmagic * Math.cos(radlat) * Math.PI);
	    double mglat = wglat + dlat;
	    double mglon = wglon + dlon;
	    
	    Map<String,Object> map = new HashMap<>();
	    					map.put("mglat", mglat);
	    					map.put("mglon", mglon);
	    
	    System.err.println("高德地图:纬度是"+mglat+"经度是"+mglon);
	    
	    return map;
	}
	
	static double transformlat(double x, double y)
	{
	    double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y +
	        0.2 * Math.sqrt(Math.abs(x));
	    ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
	    ret += (20.0 * Math.sin(y * Math.PI) + 40.0 * Math.sin(y / 3.0 * Math.PI)) * 2.0 / 3.0;
	    ret += (160.0 * Math.sin(y / 12.0 * Math.PI) + 320 * Math.sin(y * Math.PI / 30.0)) * 2.0 / 3.0;
	    return ret;
	}


	static double transformlon(double x, double y)
	{
	    double ret =
	        300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
	    ret += (20.0 * Math.sin(6.0 * x * Math.PI) + 20.0 * Math.sin(2.0 * x * Math.PI)) * 2.0 / 3.0;
	    ret += (20.0 * Math.sin(x * Math.PI) + 40.0 * Math.sin(x / 3.0 * Math.PI)) * 2.0 / 3.0;
	    ret +=
	        (150.0 * Math.sin(x / 12.0 * Math.PI) + 300.0 * Math.sin(x / 30.0 * Math.PI)) * 2.0 / 3.0;
	    return ret;
	}
	
	//高德转百度
	static void bd_encrypt(double gg_lat, double gg_lon)
	{


	    double x = gg_lon, y = gg_lat;
	    double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
	    double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);


	    double bd_lon = z * Math.cos(theta) + 0.0065;
	    double bd_lat = z * Math.sin(theta) + 0.006;
	    
	    System.err.println("百度地图:纬度是"+bd_lat+"经度是"+bd_lon);
	}
	
	public static void main(String[] args) {
		String mstr = "This what are miss  you  > like pass you < kill slsdyx";
		if(mstr.indexOf(">") != -1) {
			System.err.println(mstr.indexOf(">"));
			System.err.println("OK");
		}else {
			System.err.println("FALSE");
		}
	}
}
