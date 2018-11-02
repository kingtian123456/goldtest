package com.test;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.entity.Place;
import com.servlert.ToolUtil;

import util.Location;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月21日 上午11:21:32
* 类说明
*/
public class Test02 {
	
	
	public static String httpgetcommt(String url) {
		StringBuffer buff = null;
		try {
			URL uri = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			conn.setConnectTimeout(25000);
			conn.setReadTimeout(25000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.connect();
			
			int lenum = 0 ;
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			buff = new StringBuffer();
			char[] chr = new char[2048];
			while((lenum = in.read(chr)) != -1) {
				buff.append(chr,0,lenum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return buff.toString();
	}
	
	public static String httppostcommt(String url,Map<String, String> connt) {
		StringBuffer buff = null;
		
		try {
			URL uri = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
			conn.setConnectTimeout(25000);
			conn.setReadTimeout(25000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.connect();
			
			if(connt != null) {
				String mass = JSON.toJSONString(connt);
				OutputStream out = conn.getOutputStream();
				out.write(mass.getBytes("UTF-8"));
				out.close();
			}
			
			int num = 0;
			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			buff = new StringBuffer();
			char [] ch = new char[1024];
			while((num = in.read(ch)) != -1) {
				buff.append(ch,0,num);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buff.toString();
	}
	
	public static void main(String[] args) throws Exception {
		/*String url = "http://api.map.baidu.com/geoconv/v1/?coords=112.885983,28.197325&from=1&to=5&ak=DXD5ehVIQ8zeCYdmexk8jQg5uGOjfpzx";
		System.err.println("***********"+httpgetcommt(url));
		JSONObject jsonobject = new JSONObject(httpgetcommt(url));
		if(jsonobject.has("result")) {
			JSONArray jsonresult = jsonobject.getJSONArray("result");
			for (int i = 0; i < jsonresult.length(); i++) {
				
			}
		}*/
		
		String url = "http://zchs.f3322.net:8000/getLabel/1234567890";
		Map<String, String> map = new HashMap<>();
							map.put("curPage","0");
							map.put("pageSize","12");
							map.put("usercode","10001");
							map.put("divisionid","8602");
							
		System.err.println(httpgetcommt(url));
	}
	
	//转换一个坐标点
	public static List<Location> transformation(List<Place> list) throws Exception {
		
		Location location = null;
		List<Location> listloc = new ArrayList<>();
		for (Place place : list) {
			JSONObject json = new JSONObject(place.getPlvalue());
			//判断查询出来的数据是不是定位的数据
			if(json.has("OriginalLatitude") && json.has("OriginalLongtitude")) {
				String lats = json.getString("OriginalLatitude");
				String lons = json.getString("OriginalLongtitude");
				if(!"null".equals(lats) && !"null".equals(lons) && lats.length() > 0 && lons.length() > 0) {
					String lat = ToolUtil.gpstodecimal(json.getString("OriginalLatitude"));
					String lon = ToolUtil.gpstodecimal(json.getString("OriginalLongtitude"));
					String url = "http://api.map.baidu.com/geoconv/v1/?coords="+lon+","+lat+"&from=1&to=5&ak=DXD5ehVIQ8zeCYdmexk8jQg5uGOjfpzx";
					System.err.println(httpgetcommt(url));
					JSONObject jsonobject = new JSONObject(httpgetcommt(url));
					if(jsonobject.has("result")) {
						JSONArray jsonresult = jsonobject.getJSONArray("result");
						location = new Location();
						for (int i = 0; i < jsonresult.length(); i++) {
							location.setTime(ToolUtil.getStringDateShort(place.getPlintime()));
							location.setCode(place.getPlcode());
							location.setLat(jsonresult.getJSONObject(i).get("y").toString());
							location.setLon(jsonresult.getJSONObject(i).get("x").toString());
						}
						listloc.add(location);
					}
				}
				
			}
		}
		
		return listloc;
	}
}
