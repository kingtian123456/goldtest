package com.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.entity.Chat;
import com.entity.Devices;
import com.entity.Product;
import com.entity.User;
import com.services.DatasService;
import com.services.DevicesService;
import com.services.ProductService;
import com.servlert.ToolUtil;

import util.AuthPassport;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月22日 下午3:02:02
* 类说明
*/
@Controller
public class DcviceControls {
	
	@Resource
	DevicesService devser;
	
	@Resource
	ProductService pct;
	
	@Resource
	DatasService datas;
	
	//新增设备的接口
	@RequestMapping(value="/addDeviceModal",method = RequestMethod.POST)
	@AuthPassport
	public void getWebsocket(String dev_name,String dev_vnum,String dev_type,String dev_forward,Integer dev_forport,
			HttpServletResponse response,HttpServletRequest request) {
		
		ToolUtil.crossDomain(response);
		User user = (User)request.getSession().getAttribute("USER");
		Product product = (Product)request.getSession().getAttribute(user.getUser_account());
		Map<String,Object> map = new HashMap<>();
		   				map.put("dev_vnum",dev_vnum);
		//查询该设备码是不是已经被注册
		Devices device = devser.selectByDeviceMSI(map);
		int max = 0;
		if(device == null) {
			Devices dev = new Devices();
			dev.setDev_name(dev_name);
			dev.setDev_vnum(dev_vnum);
			dev.setDev_type(dev_type);
			dev.setDev_forward(dev_forward);
			dev.setDev_forport(dev_forport);
			dev.setUser_code(user.getUser_code());
			dev.setAt_id(product.getPro_contype());
			dev.setPro_id(product.getPro_id());
			dev.setDev_intime(new Date());
			dev.setDev_code(ToolUtil.getweiyiStr());
			dev.setDev_ispassca(0);
			
			int num = devser.insertSelective(dev);
			
			if(num > 0) {
				product.setDvg_devnum(product.getDvg_devnum()+1);
				max = pct.updateByPrimaryKeySelective(product);
			}
		}
		
		PrintWriter out = null;
		try {
			 out = response.getWriter();
			 if(max > 0 && device == null) {
				out.print(true);
			 }else {
				out.print(false);
			 }
			 out.flush();
			 out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//查询用户单个产品下的所有设备
	@RequestMapping(value="/selectProductDevicesinfo",method=RequestMethod.GET)
	@AuthPassport
	public String getProductDevicesinfo(HttpServletRequest request,
			@RequestParam(value="page",required=false)Integer page,
			@RequestParam(value="logtxt",required=false)String logtxt) {
		
		User user = (User)request.getSession().getAttribute("USER");
		Product product = (Product)request.getSession().getAttribute(user.getUser_account());
		Map<String,Object> map = new HashMap<>();
						   map.put("user_code",user.getUser_code());
						   map.put("pro_id", product.getPro_id());
						   map.put("logTxt", logtxt);
						   
		int zsu = devser.selectByProductDevCount(map);
		int zpage = 0;
		if(zsu == 0) {
			zpage = 1;
		}else if(zsu%4 == 0) {
			zpage = zsu/4;
		}else {
			zpage = zsu/4 + 1;
		}
		Integer page1 = page == null?1:page;
		map.put("after",4);
		map.put("before",(page1-1)*4);		
		List<Devices> list = devser.selectByProductDev(map);
		if(list.size() >= 0) {
			request.setAttribute("devicelist",list);
			request.setAttribute("zbmpage", zpage);
			request.setAttribute("bmpage", page1);
			request.setAttribute("product", product);
			
			return "deviceManage";
		}
		return "eorr";
	}
	
	//查询单个设备的接口
	@RequestMapping(value="/selectDeviceOne",method=RequestMethod.POST)
	@AuthPassport
	public void getDeviceOnexinxi(String dev_code,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		Map<String, Object> map = new HashMap<>();
				map.put("dev_code", dev_code);
		Devices des = devser.selectByDeviceOne(map);
		PrintWriter out = null;
		try {
			 out = response.getWriter();
			 if(des != null) {
				out.write(JSON.toJSONString(des));
			 }
			 out.flush();
			 out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//修改单个设备的接口
	@RequestMapping(value="/UpdateDeviceOne",method=RequestMethod.POST)
	@AuthPassport
	public void UpdateDeviceOnexinxi(String dev_name1,String dev_vnum1,String dev_type1,String dev_code,
			String dev_forward1,Integer dev_forport1,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		Devices dev = new Devices();
				dev.setDev_name(dev_name1);
				dev.setDev_vnum(dev_vnum1);
				dev.setDev_type(dev_type1);
				dev.setDev_forport(dev_forport1);
				dev.setDev_forward(dev_forward1);
				dev.setDev_edtime(new Date());
				dev.setDev_code(dev_code);
				
		int num = devser.updateByPrimaryKeySelective(dev);
		
		PrintWriter out = null;
		try {
			 out = response.getWriter();
			 if(num > 0) {
				out.println(true);
			 }else {
				out.println(false);
			 }
			 out.flush();
			 out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//删除单个设备的接口
	@RequestMapping(value="/deleteDeviceOne",method=RequestMethod.POST)
	@AuthPassport
	public void deleteDeviceOne(Integer dev_id,String dev_code,Integer dev_sumnum,HttpServletResponse response,HttpServletRequest request) {
		ToolUtil.crossDomain(response);
		User user = (User)request.getSession().getAttribute("USER");
		Product product = (Product)request.getSession().getAttribute(user.getUser_account());
		int num = devser.deleteByPrimaryKey(dev_id);
		int max = 0;
		if(num > 0) {
			product.setDvg_devnum(product.getDvg_devnum()-1);
			int min = pct.updateByPrimaryKeySelective(product);
			if(min > 0) {
				if(dev_sumnum != 0) {
					Map<String, Object> map = new HashMap<>();
					map.put("dev_codes",dev_code);
					max = datas.deleteByDatasinfo(map);
					if(max <= 0) {
						throw new RuntimeException();
					}
				}
			}else {
				throw new RuntimeException();
			}
		}
		PrintWriter out = null;
		try {
			 out = response.getWriter();
			 if(max > 0) {
				out.println(true);
			 }else {
				out.println(false);
			 }
			 out.flush();
			 out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/selectByDeviceChart",method=RequestMethod.POST)
	@AuthPassport
	public void selectByDeviceChartTrue(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		User user = (User)request.getSession().getAttribute("USER");
		Product product = (Product)request.getSession().getAttribute(user.getUser_account());
		List<Chat> list= devser.selectByDeviceChart(product.getPro_id());
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(JSON.toJSONString(list));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/selectByDeviceZONG",method=RequestMethod.POST)
	@AuthPassport
	public void selectByDeviceChartZONG(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		User user = (User)request.getSession().getAttribute("USER");
		Product product = (Product)request.getSession().getAttribute(user.getUser_account());
		List<Chat> list= devser.selectByDeviceChatzong(product.getPro_id());
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(JSON.toJSONString(list));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//按时间来处理的ajax图表
	@RequestMapping(value="/selectByDeviceDate",method=RequestMethod.POST)
	@AuthPassport
	public void selectByDeviceChartDate(HttpServletRequest request,HttpServletResponse response,String begin,String end) {
		ToolUtil.crossDomain(response);
		User user = (User)request.getSession().getAttribute("USER");
		Product product = (Product)request.getSession().getAttribute(user.getUser_account());
		Map<String,Object> map = new HashMap<>();
						map.put("pro_id",product.getPro_id());
						map.put("begin",begin);
						map.put("end",end);
		//查询新增数据，按日期分组
		List<Chat> list = devser.selectByDeviceDate(map);
		//查询总数据，按日期分组
		List<Chat> lisz = devser.selectByDevicesDateChatZong(map);
		/*
		for (Chat chat : list) {
			System.err.println(chat.getDays()+"********"+chat.getZsum());
		}
		
		for (Chat chat : lisz) {
			System.err.println(chat.getDays()+"//////////"+chat.getZsum());
		}
		
		
		if(list.size() != 0) {
			begin = list.get(0).getDays();
			end = list.get(list.size()-1).getDays();
		}
		*/
		
		List<String> list1 = ToolUtil.getBetweenDates(begin, end);
		List<Chat> list2 = ToolUtil.getAllList(list, list1);
		List<Chat> list3 = ToolUtil.getZAllList(lisz, list1);
		map.clear();
		map.put("Xin",list2);
		map.put("Zong",list3);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(JSON.toJSONString(map));
			System.err.println(JSON.toJSONString(map));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//设备通信聊天功能
	@RequestMapping(value="/Communication",method=RequestMethod.GET)
	public String Communication(String dev_code,HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		Map<String,Object> map = new HashMap<>();
					map.put("dev_code", dev_code);
		Devices devs = devser.selectByDeviceOne(map);
		
		request.setAttribute("devost", devs);
		
		return "debugging";
	}
	/*
	@RequestMapping(value = "/selectProductDevicesinfos",method = RequestMethod.POST)
    public void getUserById(HttpServletRequest request, HttpServletResponse response){
		System.err.println("进入77777777777");
		User user = (User)request.getSession().getAttribute("USER");
		Product product = (Product)request.getSession().getAttribute(user.getUser_account());
		Map<String,Object> map = new HashMap<>();
						   map.put("user_code",user.getUser_code());
						   map.put("pro_id", product.getPro_id());
						   
		Integer zsu = product.getDvg_devnum();
        String curPage = request.getParameter("curPage");
        String pageSize = request.getParameter("pageSize");
        
        if (curPage == null || "".equals(curPage)){
            curPage = "0";
        }
        if (pageSize == null || "".equals(pageSize)){
            pageSize = "12";
        }
        
        Integer startIndex =Integer.valueOf(curPage).intValue();
        Integer pagesize = Integer.valueOf(pageSize).intValue();

        Page<Devices> userById = devser.getDevicesByInfo(zsu, startIndex, pagesize, map);
        
        String result = JSON.toJSONString(userById);
        
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.println(result);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
