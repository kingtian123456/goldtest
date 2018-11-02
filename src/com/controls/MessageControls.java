package com.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSON;
import com.entity.Message;
import com.entity.User;
import com.services.MessageService;
import com.servlert.ToolUtil;

import util.AuthPassport;
import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月3日 上午10:02:29
* 类说明
*/
@Controller
public class MessageControls {
	
		@Resource
		MessageService ms;
		
		//新增消息数据
		@PostMapping("/insertMessagePost")
		@AuthPassport
		public void insertMessagePost(HttpServletRequest request,HttpServletResponse response) {
			ToolUtil.crossDomain(response);
			String newsid = request.getParameter("newsid");
			String mscontent = request.getParameter("mscontent");
			String layerid = request.getParameter("layerid");
			User user= (User)request.getSession().getAttribute("USER");
			
			Message mage = new Message();
					mage.setMsContent(mscontent);
					mage.setUserCode(user.getUser_code());
					mage.setNewsId(Integer.parseInt(newsid));
					mage.setMsCretime(new Date());
					
			if(layerid != null && !layerid.equals("")) {
					mage.setMsLayerid(Integer.parseInt(layerid));
			}
			
			int num = ms.insertSelective(mage);
			
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
		
		//查询单个文章下的信息
		@PostMapping("/getMessagePage")
		@AuthPassport
		public void getMessagePage(HttpServletRequest request,HttpServletResponse response) {
			ToolUtil.crossDomain(response);
			String newsid = request.getParameter("newsid");
	        String curPage = request.getParameter("curPage");
	        String pageSize = request.getParameter("pageSize");
	        
	        if (curPage == null || "".equals(curPage)){
	            curPage = "0";
	        }
	        if (pageSize == null || "".equals(pageSize)){
	            pageSize = "4";
	        }
	        
	        Integer newsId = Integer.valueOf(newsid).intValue();
	        Integer startIndex = Integer.valueOf(curPage).intValue();
	        Integer pagesize = Integer.valueOf(pageSize).intValue();

	        Page<Message> userById = ms.selectgetMessageByInfo(newsId, startIndex, pagesize);
	        
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
}
