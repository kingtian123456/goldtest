package com.controls;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月30日 下午5:47:07
* 类说明
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import com.alibaba.fastjson.JSON;
import com.entity.Chat;

import com.entity.Datas;
import com.entity.Product;
import com.entity.User;

import com.services.DatasService;
import com.servlert.ToolUtil;
import util.AuthPassport;
import util.Page;

@Controller
public class DatasControls{
	
	@Resource
	DatasService ds;
	
	//设备分页 ，传递数据
	@RequestMapping(value = "/getDatasByInfo",method = RequestMethod.POST)
	@AuthPassport
    public void getUserById(HttpServletRequest request, HttpServletResponse response){
		ToolUtil.crossDomain(response); 
		String dev_code = request.getParameter("dev_codes");
        String curPage = request.getParameter("curPage");
        String pageSize = request.getParameter("pageSize");
        if (curPage == null || "".equals(curPage)){
            curPage = "0";
        }
        if (pageSize == null || "".equals(pageSize)){
            pageSize = "20";
        }
        
        Integer startIndex =Integer.valueOf(curPage).intValue();
        Integer pagesize = Integer.valueOf(pageSize).intValue();

        Page<Datas> userById = ds.selectgetDatasByInfo(dev_code, startIndex, pagesize);
        
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
    }
	
	//数据图表部分
	@RequestMapping(value="/selectByDatasChart",method=RequestMethod.POST)
	@AuthPassport
	public void selectByDeviceChartTrue(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response); 
		User user = (User)request.getSession().getAttribute("USER");
		Product product = (Product)request.getSession().getAttribute(user.getUser_account());
		List<Chat> list= ds.selectByDatasEchar(product.getPro_id());
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
	@RequestMapping(value="/selectByDatasDateEchar",method=RequestMethod.POST)
	@AuthPassport
	public void selectByDatasChartDate(HttpServletRequest request,HttpServletResponse response,String begin,String end) {
		ToolUtil.crossDomain(response); 
		User user = (User)request.getSession().getAttribute("USER");
		Product product = (Product)request.getSession().getAttribute(user.getUser_account());
		Map<String,Object> map = new HashMap<>();
						map.put("pro_id",product.getPro_id());
						map.put("begin",begin);
						map.put("end",end);
		System.err.println(begin+"------"+end);
		List<Chat> list= ds.selectByDatasDateEchar(map);
		for (Chat chat : list) {
			System.err.println(chat.getDays()+"********"+chat.getZsum());
		}
		/*	
		if(list.size() != 0) {
			begin = list.get(0).getDays();
			end = list.get(list.size()-1).getDays();
		}
		*/	
			
		List<String> list1 = ToolUtil.getBetweenDates(begin, end);
		for (String str : list1) {
			System.err.println(str);
		}
		List<Chat> list2 = ToolUtil.getAllList(list, list1);
		for (Chat chat : list2) {
			System.err.println(chat.getDays()+"********"+chat.getZsum());
		}
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(JSON.toJSONString(list2));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//下发命令
	@RequestMapping(value="/Lowerrxiafa",method=RequestMethod.POST)
	@AuthPassport
	public void Lowerrlinux(HttpServletResponse response,HttpServletRequest request) {
		String code = request.getParameter("code");
		String value = request.getParameter("value");
		ToolUtil.crossDomain(response);
		Datas dats = ds.selectDatasByNewest(code);
		String mark = null ;
		if(value.indexOf(">") != -1 && value.indexOf("<") != -1) {
			mark = StringUtils.substringBetween(value, ">", "<");
		}else {
			mark = value;
		}
		
		if(dats != null && dats.getDs_port() != null) {
			try {
				ToolUtil.responseSend(dats.getDs_ip(), mark, dats.getDs_port());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(dats != null && dats.getDs_port() == null) {
			ToolUtil.postCoapLowerHair(dats.getDs_ip(), mark);
		}
			Datas data = new Datas();
				  data.setDev_codes(dats.getDev_codes());
				  data.setUser_code(dats.getUser_code());
				  data.setPro_id(dats.getPro_id());
				  data.setDs_Swtch(2);
				  data.setDs_value(mark);
				  data.setDs_time(new Date());
			int num = ds.insertSelective(data);
				
			PrintWriter out;
			try {
				out = response.getWriter();
				if(num > 0) {
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
	
}
