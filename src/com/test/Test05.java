package com.test;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.servlert.ToolUtil;

import csgxcf.commutil.StringUtil;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月31日 下午6:41:55
* 类说明
*/
public class Test05 {
	
	public static void main(String[] args) throws Exception {
		String miast = "30、28、22、445、564、994、6554、1179、6317、".toString().substring(0,"30、28、22、445、564、994、6554、1179、6317、".toString().length()-1);
		System.err.println(miast);
		
		Timer time = new Timer();
		
		time.schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.err.println("java定时器测试");
				
			}
		},0,5000);
		
	}
}
