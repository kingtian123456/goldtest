package com.controls;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.alibaba.fastjson.JSON;
import com.entity.Download;
import com.services.DownloadService;

import util.AuthPassport;
import util.Page;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月23日 上午10:01:35
* 类说明
*/
@Controller
public class DownloadControls {
	
	@Resource
	DownloadService dls;
	
	//分页查询文件信息
	@GetMapping("/getDownloadInfo")
	@AuthPassport
	public void getDownloadInfo(HttpServletRequest request,HttpServletResponse response) {
		String curPage = request.getParameter("curPage");
        String pageSize = request.getParameter("pageSize");
        if (curPage == null || "".equals(curPage)){
            curPage = "0";
        }
        if (pageSize == null || "".equals(pageSize)){
            pageSize = "4";
        }
        
        Integer startIndex =Integer.valueOf(curPage).intValue();
        Integer pagesize = Integer.valueOf(pageSize).intValue();
        
        Page<Download> down = dls.selectgetDownloadByInfo(startIndex, pagesize);
        
        String result = JSON.toJSONString(down);
        
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
	
	//新增文件信息
	@PostMapping("/InsertDownloadOne")
	@AuthPassport
	public void InsertDownloadOne(HttpServletRequest request,HttpServletResponse response) {
		String doName = request.getParameter("doName");
		String doTitle = request.getParameter("doTitle");
		String doCode = request.getParameter("doCode");
		String doIntron = request.getParameter("doIntron");
		String doLoadurl = request.getParameter("doLoadurl");
		
		//填充属性
		Download down = new Download();
				 down.setDoName(doName);
				 down.setDoTitle(doTitle);
				 down.setDoCode(doCode);
				 down.setDoIntron(doIntron);
				 down.setDoLoadurl(doLoadurl);
				 down.setDoCreatime(new Date());
		//数据进行提交
		int num = dls.insertSelective(down);

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
	
	//删除文件信息
	@PostMapping("/deleteDownloadOne")
	@AuthPassport
	public void deleteDownloadOne(HttpServletRequest request,HttpServletResponse response) {
		
		String doId = request.getParameter("doId");
		
		int num = dls.deleteByPrimaryKey(Integer.parseInt(doId));
		
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
	
	//修改文件信息
	@PostMapping("/putDownloadOne")
	@AuthPassport
	public void putDownloadOne(HttpServletRequest request,HttpServletResponse response,Download download) {
		//修改文件信息，服务器添加修改时间
		download.setDoUptime(new Date());
		int num = dls.updateByPrimaryKeySelective(download);
		
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
	
	//改变文件点击数
	@PostMapping("/putDownloadnum")
	@AuthPassport
	public void putDownloadnum(HttpServletRequest request,HttpServletResponse response) {
		
		String doLoadnum = request.getParameter("doLoadnum");
		String doId = request.getParameter("doId");
		
		Download down = new Download();
				 down.setDoId(Integer.parseInt(doId));
				 down.setDoLoadnum(Integer.parseInt(doLoadnum)+1);
				 
		int num = dls.updateByPrimaryKeySelective(down);
		
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
}
