package com.controls;

import java.io.IOException;
import java.io.PrintWriter;
import javax.annotation.Resource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import com.alibaba.fastjson.JSON;
import com.entity.Labs;

import com.entity.User;
import com.services.LabsService;
import com.servlert.ToolUtil;

import util.AuthPassport;

import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月9日 上午11:48:21
* 类说明
*/
@Controller
public class LabsControls {
		
		@Resource
		LabsService ls;
		
		
		//激活帐号接口
		@PostMapping("/putLabsOne")
		@AuthPassport
		public void putLabsOne(HttpServletRequest request,HttpServletResponse response) {
			ToolUtil.crossDomain(response);
			String number = request.getParameter("number");
			
			User user = (User)request.getSession().getAttribute("USER");
			
			boolean reuslt = ls.updateLabsUser(number, user);
			
			PrintWriter out = null;
			
			try {
				out = response.getWriter();
				out.println(reuslt);
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//分页查询序列号
		@PostMapping("/getLabsPageInfo")
		@AuthPassport
		public void getLabsPageInfo(HttpServletRequest request,HttpServletResponse response) {
			ToolUtil.crossDomain(response);
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

	        Page<Labs> userById = ls.selectgetLabsByInfo(startIndex, pagesize);
	        
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
		
		//修改单个序列号的状态
		@PostMapping("/updateLabsOne")
		@AuthPassport
		public void updateLabsOne(HttpServletRequest request,HttpServletResponse response) {
			ToolUtil.crossDomain(response);
			String labsId = request.getParameter("labsId");
			String state = request.getParameter("state");
			
			Labs labs = new Labs();
				 labs.setLabsId(Integer.parseInt(labsId));
				 labs.setLabsSend(Integer.parseInt(state));
			
			int num = ls.updateByPrimaryKeySelective(labs);
			
			PrintWriter writer;
	        try {
	            writer = response.getWriter();
	            if(num > 0) {
	            	writer.println(true);
	            }else {
	            	writer.println(false);
	            }
	            
	            writer.flush();
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		//删除单个序列号
		@PostMapping("/deleteLabsOne")
		@AuthPassport
		public void deleteLabsOne(HttpServletRequest request,HttpServletResponse response) {
			ToolUtil.crossDomain(response);
			String labsId = request.getParameter("labsId");
			
			int num = ls.deleteByPrimaryKey(Integer.parseInt(labsId));
			
			PrintWriter writer;
	        try {
	            writer = response.getWriter();
	            if(num > 0) {
	            	writer.println(true);
	            }else {
	            	writer.println(false);
	            }
	            
	            writer.flush();
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
		
		//批量新增序列号
		@PostMapping("/InsertLabsInfo")
		@AuthPassport
		public void InsertLabsInfo(HttpServletRequest request,HttpServletResponse response) {
			ToolUtil.crossDomain(response);
			String num = request.getParameter("num");
			
			//新开线程，执行批量插入
			boolean math = ToolUtil.putLabsInfo(Integer.parseInt(num));
			
			PrintWriter writer;
	        try {
	            writer = response.getWriter();
	            writer.println(math);
	            writer.flush();
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}
}
