package com.controls;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.entity.Commodity;
import com.entity.Sort;
import com.entity.User;
import com.services.CommodityService;
import com.services.SortService;
import com.servlert.ToolUtil;

import util.AuthPassport;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月5日 上午10:46:36
* 类说明
*/
@Controller
public class CommodityControls {
	
	@Resource
	CommodityService cos;
	
	@Resource
	SortService sort;
	
	
	//商品新增功能
	@PostMapping("/commodityUpload")
	@AuthPassport
	public void fileUpload(MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
		
		response.setHeader("Access-Control-Allow-Origin", "*"); 
		User user = (User)request.getSession().getAttribute("USER");
		String comTitle = request.getParameter("comTitle");
		String comKeyword = request.getParameter("comKeyword");
		String comLinkurl = request.getParameter("comLinkurl");
		String sortId = request.getParameter("sortId");
		String comIntro = request.getParameter("comIntro");
		String comPrinturl = null;
		try {
			comPrinturl = ToolUtil.uploadimg(file, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.err.println(comPrinturl);
		
		Commodity comm = new Commodity();
				  comm.setComTitle(comTitle);
				  comm.setComKeyword(comKeyword);
				  comm.setComLinkurl(comLinkurl);
				  comm.setComAuthor(user.getUser_account());
				  comm.setSortId(Integer.parseInt(sortId));
				  comm.setComPrinturl(comPrinturl);
				  comm.setComIntro(comIntro);
				  comm.setComCretime(new Date());
				  
		int num = cos.insertSelective(comm);
		
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//商品新增功能
	@PostMapping("/commodityUploads")
	@AuthPassport
	public String fileUploads(MultipartFile file,HttpServletRequest request,HttpServletResponse response) {
			
		ToolUtil.crossDomain(response); 
		User user = (User)request.getSession().getAttribute("USER");
		String comTitle = request.getParameter("comTitle");
		String comKeyword = request.getParameter("comKeyword");
		String comLinkurl = request.getParameter("comLinkurl");
		String sortId = request.getParameter("sortId");
		String comIntro = request.getParameter("comIntro");
		String comPrinturl = null;
		try {
			comPrinturl = ToolUtil.uploadimg(file, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		System.err.println(comPrinturl);
			
		Commodity comm = new Commodity();
				  comm.setComTitle(comTitle);
				  comm.setComKeyword(comKeyword);
				  comm.setComLinkurl(comLinkurl);
				  comm.setComAuthor(user.getUser_account());
				  comm.setSortId(Integer.parseInt(sortId));
				  comm.setComPrinturl(comPrinturl);
				  comm.setComIntro(comIntro);
				  comm.setComCretime(new Date());
					  
		int num = cos.insertSelective(comm);
			
		if(num > 0) {
			return "redirect:getCommodityInfos";
		}else {
			return "devboardnew";
		}
	}
	
	
	//获取全部商品的信息
	@PostMapping("/getCommodityInfo")
	@AuthPassport
	public void selectCommodityInfo(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response); 
		
		List<Commodity> list = cos.selectByCommodityInfo();
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println(JSON.toJSONString(list));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//获取全部商品的信息
	@GetMapping("/getCommodityInfos")
	@AuthPassport
	public String selectCommodityInfos(HttpServletRequest request,HttpServletResponse response) {
			
		ToolUtil.crossDomain(response);  
		List<Commodity> list = cos.selectByCommodityInfo();
		
		List<Sort> list1  = sort.selectBySortSubclass(5);
			
		request.setAttribute("Commodity", list);
		request.setAttribute("SortList",list1);
			
		return "devboard";
			
	}
		
	//删除单个商品信息
	@PostMapping("/deleteCommodityOne")
	@AuthPassport
	public void deleteCommodityOne(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response); 
		String comId = request.getParameter("comId");
		int num = cos.deleteByPrimaryKey(Integer.parseInt(comId));
		
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查询单个商品信息
	@GetMapping("/getCommodityOne")
	@AuthPassport
	public void selectCommodityOne(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response); 
		String comId = request.getParameter("comId");
		Commodity comm = cos.selectByPrimaryKey(Integer.parseInt(comId));
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println(JSON.toJSONString(comm));
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//修改产品功能
	@PostMapping("/updateCommodityOne")
	@AuthPassport
	public String updateCommodityOne(HttpServletRequest request,Commodity com) {
		int num = cos.updateByPrimaryKeySelective(com);
		
		if(num > 0) {
			return "redirect:getCommodityInfos";
		}else {
			return "devboardeditor";
		}
	}
}
