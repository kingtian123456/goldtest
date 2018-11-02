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
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSON;
import com.entity.News;

import com.entity.User;
import com.services.MessageService;
import com.services.NewsService;
import com.servlert.ToolUtil;

import util.AuthPassport;
import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月27日 下午4:46:55
* 类说明
*/
@Controller
public class NewsControls {
	
	@Resource
	NewsService news;
	
	@Resource
	MessageService ms;
	
	
	//插入文章的接口
	@PostMapping("/Newsinsert")
	@AuthPassport
	public void testinsert(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		String sortid = request.getParameter("sortid");
		String title = request.getParameter("title");
		String intro = request.getParameter("intro");
		String content = request.getParameter("content");
		String keyword = request.getParameter("keyword");
		String passed = request.getParameter("passed");
		String time = request.getParameter("time");
		User user = (User) request.getSession().getAttribute("USER");
		News nem = new News();
			 nem.setSortId(Integer.parseInt(sortid));
			 nem.setNewsTitle(title);
			 nem.setNewsIntro(intro);
			 nem.setNewsAuthor(user.getUser_account());
			 nem.setNewsContent(content);
			 nem.setNewsCopyfrom("管理员编辑");
			 nem.setNewsKeyword(keyword);
			 nem.setNewsPassed(Integer.parseInt(passed));
			 nem.setNewsUser(user.getUser_name());
			 nem.setNewsCreatetime(ToolUtil.getDateOne(time,0));
			 nem.setNewsDeleted(1);
			 nem.setNewsOrders(1);
			 
		int num = news.insertSelective(nem);	 
		
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
	
	//修改文章的接口
	@PostMapping("/putNewsById")
	@AuthPassport
	public void putNewsById(HttpServletRequest request, HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		String newsid = request.getParameter("newsId");
		String sortid = request.getParameter("sortid");
		String title = request.getParameter("title");
		String intro = request.getParameter("intro");
		String content = request.getParameter("content");
		String keyword = request.getParameter("keyword");
		String passed = request.getParameter("passed");
		String time = request.getParameter("time");
		User user = (User) request.getSession().getAttribute("USER");
		News nem = new News();
			 nem.setNewsId(Integer.parseInt(newsid));	
			 nem.setSortId(Integer.parseInt(sortid));
			 nem.setNewsTitle(title);
			 nem.setNewsIntro(intro);
			 nem.setNewsAuthor(user.getUser_account());
			 nem.setNewsContent(content);
			 nem.setNewsCopyfrom("管理员编辑");
			 nem.setNewsKeyword(keyword);
			 nem.setNewsPassed(Integer.parseInt(passed));
			 nem.setNewsUser(user.getUser_name());
			 nem.setNewsCreatetime(ToolUtil.getDateOne(time,1));
			 nem.setNewsDeleted(1);
			 nem.setNewsOrders(1);
			 nem.setNewsUpdatetime(new Date());
			 
		int num = news.updateByPrimaryKeySelective(nem);	 
		
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
	
	//博客文章分页 ，传递数据
	@PostMapping("/getNewsByInfo")
	@AuthPassport
	public void getNewsById(HttpServletRequest request, HttpServletResponse response){
		ToolUtil.crossDomain(response);
	    String curPage = request.getParameter("curPage");
	    String pageSize = request.getParameter("pageSize");
	    
	    if (curPage == null || "".equals(curPage)){
	    	curPage = "0";
	    }
	    if (pageSize == null || "".equals(pageSize)){
	        pageSize = "3";
	    }
	        
	    Integer startIndex =Integer.valueOf(curPage).intValue();
	    Integer pagesize = Integer.valueOf(pageSize).intValue();

	    Page<News> userById = news.selectgetNewsByInfo(1, startIndex, pagesize);
	        
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
	
	//获取单个文章的信息,更新点击数
	@PostMapping("/getSingleNews")
	@AuthPassport
	public void getSingleNews(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		String newsId = request.getParameter("newsId");
		
		Map<String,Object> map = new HashMap<>();
   					map.put("newsId",Integer.parseInt(newsId));

		News nws = news.selectByPrimaryKey(Integer.parseInt(newsId));
			 nws.setNewsHits(nws.getNewsHits()+1);
			 nws.setComments(ms.selectByMessageNewsCount(map));
			 
		news.updateByPrimaryKeySelective(nws);
		
		nws.setList(ToolUtil.conversionList(nws.getNewsKeyword()));

		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println(JSON.toJSONString(nws));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//根据文章标题查询文章的ID
	@PostMapping("/getNewsOneClick")
	@AuthPassport
	public void putNewsOneClick(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		String title = request.getParameter("title");
		
		News nws = news.selectByNewsID(title);
		if(nws != null) {
			nws.setList(ToolUtil.conversionList(nws.getNewsKeyword()));
		}
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println(JSON.toJSONString(nws));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//文章排行榜加载前8位
	@PostMapping("/getNewsArticle")
	@AuthPassport
	public void getNewsArticle(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		List<News> list = news.selectByNewsArticle();
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println(JSON.toJSONString(list));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	};
	
	//获取用户信息在前端做权限控制
	@GetMapping("/getUserDate")
	@AuthPassport
	public void getUserDate(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		User user = (User) request.getSession().getAttribute("USER");
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println(JSON.toJSONString(user));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取全部的关键字的集合
	@GetMapping("/getNewsKeyWord")
	@AuthPassport
	public void getNewsKeyWord(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		List<String> list = news.selectByNewsKey();
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println(JSON.toJSONString(ToolUtil.listconversionList(list)));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//删除文章同时删除留言的记录
	@PostMapping("/deleteNewsMessageID")
	@AuthPassport
	public void deleteNewsMessageID(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		String newsid = request.getParameter("newsId");
		
		boolean result = news.deleteNewsMessage(Integer.parseInt(newsid));
		
		PrintWriter out = null;
		
		try {
			out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
