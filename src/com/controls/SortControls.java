package com.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSON;
import com.entity.Sort;
import com.services.SortService;
import com.servlert.ToolUtil;

import util.AuthPassport;
import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月29日 下午4:55:36
* 类说明
*/
@Controller
public class SortControls {
	
	
	@Resource
	SortService sts;
	
	
	//查询子模版接口
	@PostMapping("/selectBySortSubclass")
	@AuthPassport
	public void selectBySortSubclass(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		Integer parentId = Integer.parseInt(request.getParameter("parentId"));
		
		List<Sort> list = sts.selectBySortSubclass(parentId);
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println(JSON.toJSONString(list));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//查询所有的子类模版
	@PostMapping("/selectBySortZiInfo")
	@AuthPassport
	public void selectBySortZiInfo(HttpServletRequest request,HttpServletResponse response) {
			ToolUtil.crossDomain(response);
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

	        Page<Sort> userById = sts.selectgetSortByInfo(startIndex, pagesize);
	        
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
	
	//查询所有的父类模版
	@PostMapping("/selectBySortParentInfo")
	@AuthPassport
	public void selectBySortFuInfo(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);	
		List<Sort> list = sts.selectBySortFuInfo();
			
		PrintWriter out = null;
			
		try {
			out = response.getWriter();
			out.println(JSON.toJSONString(list));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//修改分类
	@PostMapping("/putBySortOne")
	@AuthPassport
	public void putBySortOne(HttpServletRequest request,HttpServletResponse response,Sort sort) {
		ToolUtil.crossDomain(response);
		int num = sts.updateByPrimaryKeySelective(sort);
		
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
	
	//删除分类
	@PostMapping("/deleteBySortOne")
	@AuthPassport
	public void deleteBySortOne(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		String  sortId = request.getParameter("sortId");
		
		String parntId = request.getParameter("parntId");
		
		boolean math = sts.deleteSortCommInfo(Integer.parseInt(sortId),Integer.parseInt(parntId));
			
		PrintWriter out = null;
			
		try {
			out = response.getWriter();
			out.println(math);
			out.flush();
			out.close();
				
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//新增分类
	@PostMapping("/insertBySortOne")
	@AuthPassport
	public void insertBySortOne(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		Sort sort = new Sort();
		String sortName = request.getParameter("sortName");
		String sortSeachkey = request.getParameter("sortSeachkey");
		String sortRemark = request.getParameter("sortRemark");
		String sortContent = request.getParameter("sortContent");
		String sortParentid = request.getParameter("sortParentid");
		String sortDir = request.getParameter("sortDir");
		
		//添加属性值
		sort.setSortName(sortName);
		sort.setSortSeachkey(sortSeachkey);
		sort.setSortRemark(sortRemark);
		sort.setSortContent(sortContent);
		sort.setSortParentid(Integer.parseInt(sortParentid));
		sort.setSortDepth(1);
		sort.setSortType(1);
		sort.setSortStats(1);
		sort.setSortOrders(2);
		sort.setSortIsshow(1);
		sort.setSortDir(sortDir);
		sort.setSortUptime(new Date());
			
		int num = sts.insertSelective(sort);
				
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
}
