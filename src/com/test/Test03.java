package com.test;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.servlert.ToolUtil;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月24日 上午11:15:12
* 类说明
*/
public class Test03 {
	public static void main(String[] args) throws Exception {
		String miss = "2018/10/22 上午10:23:2";
		String math = "2018/10/19 下午6:41:17";
		System.err.println(miss.length());
	}
	
	public static List<String> conversionList(String parameter){
		List<String> list = new ArrayList<>();
		if(parameter.indexOf("，") > 0) {
			String [] date = parameter.split("，");
			for (String str : date) {
				list.add(str);
			}
		}else {
			list.add(parameter);
		}
		return list;
	}
	
	public static List<String> listconversionList(List<String> list){
		List<String> listout = new ArrayList<>();
		for (String str : list) {
			if(str.indexOf("，") > 0) {
				String [] date = str.split("，");
				for (String stt : date) {
					if(!listout.contains(stt)) {
						listout.add(stt);
					}
				}
			}else {
				if(!listout.contains(str)) {
					listout.add(str);
				}
			}
		}
		return listout;
	}
	
	
}
