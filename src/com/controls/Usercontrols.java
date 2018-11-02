package com.controls;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.entity.Product;
import com.entity.User;
import com.services.ProductService;
import com.services.UserService;
import com.servlert.ToolUtil;
import com.servlert.UdpService;
import com.test.Test07;

import util.AuthPassport;
import util.Page;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月18日 上午10:58:18
* User类控制层
*/
@Controller
public class Usercontrols {
	
	@Resource 
	UserService userserver;
	
	@Resource 
	ProductService prdt;
	
	//查询信息部分
	@RequestMapping(value="/getProductinfos",method=RequestMethod.POST)
	@AuthPassport
	public void getbyProductinfos(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		User user = (User)request.getSession().getAttribute("USER");
		String curPage = request.getParameter("curPage");
	    String pageSize = request.getParameter("pageSize");
	    if (curPage == null || "".equals(curPage)){
	           curPage = "0";
	    }
	    if (pageSize == null || "".equals(pageSize)){
	           pageSize = "4";
	    }
	    
	    Integer curpage = Integer.valueOf(curPage).intValue();
	    Integer pagesize = Integer.valueOf(pageSize).intValue();
	    
	    Page<Product> productID =  prdt.selectgetDatasByInfo(user.getUser_code(), curpage, pagesize);
	    
	    PrintWriter out = null;
	    
	    try {
			out = response.getWriter();
			out.println(JSON.toJSONString(productID,SerializerFeature.DisableCircularReferenceDetect));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
	
	//退出登录功能
	@RequestMapping(value="/deleteSession",method=RequestMethod.GET)
	@AuthPassport
	public String deleteSession(HttpServletRequest request) {
		
		request.getSession().invalidate();
		
		return "redirect:/Login.html";
	}
	
	@RequestMapping(value="/Logins",method=RequestMethod.POST)
	public void Logins(String name,String password,String verifyCode,
			HttpServletRequest request,HttpServletResponse response) {
		User user = null;
        Map<String, String> map = new HashMap<>();
        
        if(name.matches(ToolUtil.phone)) {//手机登录
        	map.put("user_mobile",name);
    		map.put("userPassword",password);
    		user = userserver.selectgetUserOne(map);
    		if(user != null) {
    			user.setUser_logintime(new Date());
    			user.setUser_loginnum(user.getUser_loginnum()+1);
    			user.setUser_IP(ToolUtil.getIPAddress(request));
    			userserver.updateByPrimaryKeySelective(user);
    			request.getSession().setAttribute("USER", user);
    		}
        }else if(name.matches(ToolUtil.email)) {//邮箱登录
        	map.put("user_mail",name);
    		map.put("userPassword",password);
    		user = userserver.selectgetUserOne(map);
    		if(user != null) {
    			user.setUser_logintime(new Date());
    			user.setUser_loginnum(user.getUser_loginnum()+1);
    			user.setUser_IP(ToolUtil.getIPAddress(request));
    			userserver.updateByPrimaryKeySelective(user);
    			request.getSession().setAttribute("USER", user);
    		}
        }else {//帐号登录
        	map.put("userAccount",name);
    		map.put("userPassword",password);
    		user = userserver.selectgetUserOne(map);
    		if(user != null) {
    			user.setUser_logintime(new Date());
    			user.setUser_loginnum(user.getUser_loginnum()+1);
    			user.setUser_IP(ToolUtil.getIPAddress(request));
    			userserver.updateByPrimaryKeySelective(user);
    			request.getSession().setAttribute("USER", user);
    		}
        }
        
        
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.print(JSON.toJSONString(user));
			
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/gettest",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public void reuslt(@RequestBody String json,HttpServletResponse response) {
		System.err.println("111111111");
		System.err.println(json+"8888");
		if(json != null) {
			PrintWriter out = null;
			try {
				System.err.println("2222222");
				out = response.getWriter();
				out.write(JSON.toJSONString("测试成功"));
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** 
     * 获取验证码 
     *  
     * @param response 
     * @param session 
     */  
    @RequestMapping("/getVerifyCode")  
    public void generate(HttpServletResponse response, HttpSession session) {  
        ByteArrayOutputStream output = new ByteArrayOutputStream();  
        String verifyCodeValue = drawImg(output);  
  
        session.setAttribute("verifyCodeValue", verifyCodeValue);  
  
        try {  
            ServletOutputStream out = response.getOutputStream();  
            output.writeTo(out);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 绘画验证码 
     *  
     * @param output 
     * @return 
     */  
    private String drawImg(ByteArrayOutputStream output) {  
        String code = "";  
        // 随机产生4个字符  
        for (int i = 0; i < 4; i++) {  
            code += randomChar();  
        }  
        int width = 70;  
        int height = 25;  
        BufferedImage bi = new BufferedImage(width, height,  
                BufferedImage.TYPE_3BYTE_BGR);  
        Font font = new Font("Times New Roman", Font.PLAIN, 20);  
        // 调用Graphics2D绘画验证码  
        Graphics2D g = bi.createGraphics();  
        g.setFont(font);  
        Color color = new Color(66, 2, 82);  
        g.setColor(color);  
        g.setBackground(new Color(226, 226, 240));  
        g.clearRect(0, 0, width, height);  
        FontRenderContext context = g.getFontRenderContext();  
        Rectangle2D bounds = font.getStringBounds(code, context);  
        double x = (width - bounds.getWidth()) / 2;  
        double y = (height - bounds.getHeight()) / 2;  
        double ascent = bounds.getY();  
        double baseY = y - ascent;  
        g.drawString(code, (int) x, (int) baseY);  
        g.dispose();  
        try {  
            ImageIO.write(bi, "jpg", output);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return code;  
    }  
  
    /** 
     * 随机参数一个字符 
     *  
     * @return 
     */  
    private char randomChar() {  
        Random r = new Random();  
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";  
        return s.charAt(r.nextInt(s.length()));  
    }
    
    @RequestMapping(value="/Thedownside",method=RequestMethod.POST)
    public void Xiaxinginfo(String data,HttpServletResponse response) {
    	try {
			send(data);
			PrintWriter out = response.getWriter();
			out.print(true);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
	private void send(String datas) throws Exception {
		//1.定義服務器地址，端口號，數據包
		InetAddress address = InetAddress.getByName(ToolUtil.ip);
        int port = ToolUtil.port1;
        byte[] data = datas.getBytes();
        //2.創建數據包，包含發送的信息
        DatagramPacket packet = new DatagramPacket(data, data.length,address, port);
        //3.創建DatagramSocket對象
        //DatagramSocket socket = new DatagramSocket();
        //4.向服務器端發送數據包
        UdpService.response(packet);
	}
	
	//短信找回密码功能
	@PostMapping("/MessagebackUser")
	public void Messageback(HttpServletRequest request,HttpServletResponse response) {
		ToolUtil.crossDomain(response);
		String model = request.getParameter("model");
		System.out.println(model);
		String reuslt = null;
		if(model != null && !model.equals("")) {
			try {
				reuslt = userserver.selectputMessageBack(model);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.err.println(reuslt);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			if(reuslt != null && reuslt.equals("0")) {
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
	
	//测试视频上传接口
	@PostMapping("/loadPath")
    public void FileUpload(HttpServletRequest request,MultipartFile file) {
    	System.err.println("进入");
    	try {
			ToolUtil.uploadimg(file, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.err.println("完成");
    }
	
	@PostMapping("/mdatess")
	public void mdatess(HttpServletRequest request,String file,HttpServletResponse response) throws Exception {
		System.err.println("5444444");
		String dtr = Test07.getImageSrc(file).get(0);
		request.getSession().setAttribute("mdatess", dtr);
		
		PrintWriter out =response.getWriter();
		
		out.print(true);
		
		out.flush();
		out.close();
	}
	
	@GetMapping("/missdate")
	public void missdate(HttpServletRequest request,HttpServletResponse response) {
		System.err.println("牛逼dd");
	}
}
