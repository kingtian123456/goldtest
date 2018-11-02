package com.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.entity.Devices;
import com.entity.Place;

import com.huawei.service.signalingDelivery.CreateDeviceCmdCancelTaskV4;
import com.services.DevicesService;
import com.services.PlaceService;
import com.servlert.ToolUtil;
import com.test.Test02;

import util.Location;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月8日 上午9:46:15
* 类说明
*/
@Controller
public class PlaceControls {
	
		@Resource
		PlaceService ps;
		
		@Resource
		DevicesService dev;
		
		//最新设备记录
		@RequestMapping(value = "/selectThelatestinfo",method=RequestMethod.POST)
		public void selectThelatestinfo(HttpServletResponse response) {
			ToolUtil.crossDomain(response);
			//BigInteger
			List<Place> list = ps.selectByPrThelatestKey();
			
			List<Location> lisz = null;
			
			try {
				lisz = Test02.transformation(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for (Location location : lisz) {
				Devices devmat = dev.selectByDeviceOne(location.getCode());
				location.setMacode(devmat.getDev_id().toString());
				location.setState(null);
			}
			
			PrintWriter out = null;
			
			try {
				out = response.getWriter();
				out.println(JSON.toJSONString(lisz));
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		//今天一天的设备记录
		@RequestMapping(value = "/selectTodayPlace",method=RequestMethod.POST)
		public void selectTodayPlace(HttpServletResponse response,HttpServletRequest request) {
			ToolUtil.crossDomain(response);
			String plcode = request.getParameter("plcode");
			
			List<Place> list = ps.selectByDateTodayPlace(plcode);
			
			List<Location> lisz = null;
			
			try {
				lisz = Test02.transformation(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			PrintWriter out = null;
			
			try {
				out = response.getWriter();
				out.println(JSON.toJSONString(lisz));
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		//近1小时的设备记录
		@RequestMapping(value = "/selectByNearlyonehour",method=RequestMethod.POST)
		public void selectByNearlyonehour(HttpServletResponse response,HttpServletRequest request) {
			ToolUtil.crossDomain(response);		
			int plcode = Integer.parseInt(request.getParameter("plcode"));
			
			Devices devs = dev.selectByPrimaryKey(plcode);
					
			List<Place> list = ps.selectByNearlyonehour(devs.getDev_code());
					
			List<Location> lisz = null;
					
			try {
				lisz = Test02.transformation(list);
			} catch (Exception e) {
				e.printStackTrace();
			}
					
			PrintWriter out = null;
					
			try {
				out = response.getWriter();
				out.println(JSON.toJSONString(lisz));
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//下发命令
		@RequestMapping(value = "/IssuedanOrder",method=RequestMethod.POST)
		public void IssuedanOrder(HttpServletResponse response,HttpServletRequest request) {
			ToolUtil.crossDomain(response);
			String order = request.getParameter("order");
			
			List<Devices> list = dev.selectByDingWeiSheBei();
			
			if(list.size() != 0) {
				for (Devices device : list) {
					try {
						CreateDeviceCmdCancelTaskV4.downsideData(device.getDev_code(),order);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.println(true);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//设备出围栏告警
		@PostMapping(value="/ReportWarning")
		public void ReportWarning(HttpServletResponse response,HttpServletRequest request) {
			ToolUtil.crossDomain(response);
			String[] str = request.getParameterValues("macode");
			for(String strs : ToolUtil.listdate) {
				System.err.println(strs);
			}
			
			for (String stri : str) {
				if(ToolUtil.listdate.indexOf(stri) < 0) {
					System.err.println("不存在");
					ToolUtil.listdate.add(stri);
				}
			}
		}

		
}
