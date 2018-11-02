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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.entity.Agreement;

import com.entity.Classfiy;
import com.entity.Product;
import com.entity.User;

import com.services.AgreementService;
import com.services.ClassfiyService;
import com.services.DatasService;
import com.services.DevicesService;
import com.services.ProductService;
import com.servlert.ToolUtil;

import util.AuthPassport;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月28日 下午3:33:32
* 类说明
*/

@Controller
public class ProductControls {
	
	@Resource
	ProductService prm;
	
	@Resource
	ClassfiyService cfy;
	
	@Resource
	AgreementService amt;
	
	@Resource
	DevicesService dev;
	
	@Resource
	DatasService datas;
	
	//新增产品前，先查询出开发板类型，协议类型
	@GetMapping(value="/addproduct")
	@AuthPassport
	public void getagrinfo(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		Map<String,Object> map = new HashMap<>();
		List<Classfiy> list = cfy.selectByPrimaryinfo();
		List<Agreement> listamt = amt.selectByPrimaryinfo();
		
		map.put("classfiy",list);
		map.put("agreemenet",listamt);
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.write(JSON.toJSONString(map));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//新增产品
	@RequestMapping(value="/addproductOne",method=RequestMethod.POST)
	@AuthPassport
	public void Insertproduct(Product pruct,HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		User user = (User)request.getSession().getAttribute("USER");
		pruct.setUser_code(user.getUser_code());
		pruct.setPro_intime(new Date());
		int num = prm.insertSelective(pruct);
		PrintWriter out = null ;
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
	
	//修改产品，先查询出当前产品的信息
	@RequestMapping(value="/selectProductOne",method=RequestMethod.GET)
	@AuthPassport
	public String getProductOne(int pro_id,HttpServletRequest request) {
		List<Classfiy> list = cfy.selectByPrimaryinfo();
		List<Agreement> listamt = amt.selectByPrimaryinfo();
		Product product = prm.selectByPrimaryKey(pro_id);
		if(product != null) {
			request.setAttribute("product",product);
			request.setAttribute("Classfiy", list);
			request.setAttribute("Agreement", listamt);
			return "edit";
		}
		return "eorr";
	}
	
	//修改产品信息
	@RequestMapping(value="/UpdateProduct",method=RequestMethod.POST)
	@AuthPassport
	public void updateProductOne(Product pruct,HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		pruct.setPro_edtime(new Date());
		int num = prm.updateByPrimaryKeySelective(pruct);
		PrintWriter out = null ;
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
	
	//删除产品信息，及改产品下的所有设备
	@RequestMapping(value="/delectProductOne",method=RequestMethod.POST)
	@AuthPassport
	public void delectProductOne(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		Integer pro_id = Integer.parseInt(request.getParameter("pro_id"));
		Integer dvg_devnum = Integer.parseInt(request.getParameter("dvg_devnum"));
		
		int num = prm.deleteByPrimaryKey(pro_id);
		if(num > 0) {
			if(dvg_devnum > 0) {
				int max = dev.deleteByPrimaryKeyOneDevice(pro_id);
				if(max > 0) {
					Map<String, Object> map = new HashMap<>();
					map.put("pro_id",pro_id);
					int min = datas.deleteByDatasinfo(map);
					if(min < 0) {
						throw new RuntimeException();
					}
				}else {
					throw new RuntimeException();
				}
			}
		}
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
	
	//查看产品的信息，及在该产品下设备的具体明细
	@RequestMapping(value="/SelectProductOne",method=RequestMethod.GET)
	@AuthPassport
	public String getbyProductOne(@RequestParam(value="pro_id",required=false)Integer pro_id,HttpServletRequest request) {
		User user = (User)request.getSession().getAttribute("USER");
		int miss = 0;
		if(pro_id == null) {
			Product product1 =(Product) request.getSession().getAttribute(user.getUser_account());
			miss = product1.getPro_id();
		}else {
			miss = pro_id;
		}
		Product product = prm.selectByPrimaryKey(miss);
		
		//新建产品的session，保存单个产品的具体信息
		request.getSession().setAttribute(user.getUser_account(),product);
		request.setAttribute("product", product);
		
		return "product";
	}
	
	//产品概要分析功能接口
	@RequestMapping(value="/getProductAnalysis",method=RequestMethod.POST)
	@AuthPassport
	public void getProductAnalysis(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		User user = (User)request.getSession().getAttribute("USER");
		Product product =(Product) request.getSession().getAttribute(user.getUser_account());
		//查询某产品的在线设备
		int zxnum = dev.selectByDeviceOnline(product.getPro_id());
		//查询某产品的今日上传数据数
		int datasnum = datas.selectTodayCount(product.getPro_id());
		//查询某产品的上传数据总数
		int totalnum = datas.selectTotalCount(product.getPro_id());
		Map<String,Object>	map = new HashMap<>();
					map.put("zxnum",zxnum);
					map.put("datasnum",datasnum);
					map.put("totalnum",totalnum);
		PrintWriter out  = null;
		try {
			out = response.getWriter();
			out.write(JSON.toJSONString(map));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
