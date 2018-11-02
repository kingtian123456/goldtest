package com.servlert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetAddress;

import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.californium.core.CoapClient;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.entity.Chat;
import com.mysql.jdbc.PreparedStatement;

import sun.misc.BASE64Encoder;
import util.SmsSendRequest;
import util.SmsSendResponse;

public class ToolUtil {
	
	public static final String code = "IMEI";
	
	public static final String value = "value";
	
	public static final String IP = "103.45.106.53";
	
	public static final int port = 8799;
	
	public static final int max_len  = 4096;
	
	public static final String ponse = "2.05";
	
	public static final String coap = "COAP";
	
	public static final String udp = "UDP";
	
	public static final String cherst = "UTF-8";
	
	public static final String date = "yyyy-MM-dd HH:mm:ss";
	
	public static final String bole = "{";
	
	public static final String bolt = "}";
	
	public static final String ponsel = "2.00";
	
	public static String email = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
	
	public static String phone = "^[1][34578]\\d{9}$";
	
	public static String ip = null;
	
	public static int port1 = 0;
	
	public static final String charset = "utf-8";
	// 用户平台API账号(非登录账号,示例:N1234567)
	public static String account = "N3265116";
	// 用户平台API密码(非登录密码)
	public static String pswd = "PRl7mNphg8c47f";
	
	public static DatagramSocket socket = null;
	
	public static  List<String> list = new ArrayList<String>();
	
	public static  List<String> listuser = new ArrayList<String>();
	
	public static List<String> listdate = new ArrayList<>();
	
	public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        //确定计算方法
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
        
        return newstr;
    }
	
	//获取唯一字符串的方法，可以设置为编码
	public static String getUniqueString() {
		String reuslt = UUID.randomUUID().toString().replace("-","");
		return reuslt;
	}
	//获取时间戳字符串
	public static String getweiyiStr() {
		return String.valueOf(System.currentTimeMillis());
	}
	
	//判断是否需要需要创建连接
	public static void getList(String data) {
		list.add(data);
	}
	
	//加入webscoke的标识，判断连接是否建立
	public static void addUserList(String data) {
		listuser.add(data);
	}
	
	//删除webscoke的标识
	public static void deleteUserList(String data) {
		listuser.remove(data);
	}
	
	public static String getJSON(String ip,String value,String name) {
		Map<String, String> map = new HashMap<String,String>();
							map.put("nickname",ip);
							map.put("toUser",name);
							map.put("content",value);
		String reuslt = new JSONObject(map).toString();
		return reuslt;
	} 
	
	//比较两个时间的大小
	public static boolean TocompareDate(String begin, String end) {
		boolean fails = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date bg = null;
		Date ed = null;
		try{  
        bg = sdf.parse(begin);  
		ed = sdf.parse(end);      
		}catch(Exception e){  
          e.printStackTrace();  
		}
		if(bg.getTime() > ed.getTime()) {
			
			fails = true;
			
		}
		return fails;
	}
	
	//区别时间间隔
	public static List<String> getBetweenDates(String begin, String end) {

        List<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date bg = null;
		Date ed = null;
		try{  
        bg = sdf.parse(begin);
		ed = sdf.parse(end);      
       }catch(Exception e){  
          e.printStackTrace();  
       }  
       Calendar tempStart = Calendar.getInstance();
       tempStart.setTime(bg); 
        
       while(bg.getTime()<=ed.getTime()){
		
         result.add(sdf.format(tempStart.getTime()));
         tempStart.add(Calendar.DAY_OF_YEAR, 1);
         bg = tempStart.getTime();
       }
        return result;
	}
	
	//新增信息格式整理
	public static List<Chat> getAllList(List<Chat> chatlist, List<String> datelist){
		List<Chat> newlist = new ArrayList<>();
		Chat nvll= null;
		for(String dl:datelist){
			if(chatlist.size()!=0){
				for(Chat ol:chatlist){
					if(ol.getDays().equals(dl)){//日期一样 插入数据
						newlist.add(ol);
						chatlist.remove(ol);//删除插入过的  下次从第一个没插入的开始
						break;//跳出内循环
					}else{//不一样  插入个空对象
						nvll = new  Chat();
						nvll.setDays(dl);
						nvll.setZsum(0);
						newlist.add(nvll);
						break;
					}
				}
			}else{//datelist 最后一天  比 objlist最后一天大  objlist一个一个删除了  最后为空了
				nvll = new  Chat();
				nvll.setDays(dl);
				nvll.setZsum(0);
				newlist.add(nvll);
			}
		}
		return newlist;
	}
	
	//总设备信息格式整理
	public static List<Chat> getZAllList(List<Chat> chatlist, List<String> datelist){
		List<Chat> newlist = new ArrayList<>();
		Chat min = null;
		Chat nvll= null;
		for(String dl:datelist){
			if(chatlist.size()!=0){
				for(Chat ol:chatlist){
					if(ol.getDays().equals(dl)){//日期一样 插入数据
						newlist.add(ol);
						min = ol;
						chatlist.remove(ol);//删除插入过的  下次从第一个没插入的开始
						break;//跳出内循环
					}else{//不一样  插入个空对象
						if(min != null && TocompareDate(dl,min.getDays())) {
							nvll = new  Chat();
							nvll.setDays(dl);
							nvll.setZsum(min.getZsum());
							newlist.add(nvll);
							break;
						}else {
							nvll = new  Chat();
							nvll.setDays(dl);
							nvll.setZsum(0);
							newlist.add(nvll);
							break;
						}
					}
				}
			}else if(min != null){//datelist 最后一天  比 objlist最后一天大  objlist一个一个删除了  最后为空了
				nvll = new  Chat();
				nvll.setDays(dl);
				nvll.setZsum(min.getZsum());
				newlist.add(nvll);
			}else {
				nvll = new  Chat();
				nvll.setDays(dl);
				nvll.setZsum(0);
				newlist.add(nvll);
			}
		}
		return newlist;
	}
	
	//将GPS参数转化成坐标
	public static String gpstodecimal(String src)
	{
		//BigInteger bg1 = new BigInteger("100");lat 纬度 lon 经度
		//{"timer":"20180608111700","nbtime":"37","latitude":"28.1957","lon":"11253.0882","gpstime":"03","signal":"13","lat":"2811.7453","longitude":"112.8848"}
		double data = Double.parseDouble(src);
		int Degree = (int) (data/100);
		//System.err.println(Degree+"度");
		double Cent_tmp = (data - Degree*100);
		//System.err.println(Cent_tmp+"分");
		int Cent = (int)Cent_tmp;
		//System.err.println(Cent+"///333");
		double Second_tmp = (Cent_tmp - Cent)*60;
		//System.err.println(Second_tmp+"///444");
		int Second = (int)Second_tmp;
		//System.err.println(Second+"///555");
		//double reult = 	//(double)Second/3600 + */(double)Cent/60 + (double)Degree;
		
		double reult = (double)Cent_tmp/60 + (double)Degree;
		
		String reuslt = String.valueOf(reult);
		
		return reuslt;
	}
	
	//异步下发
	public static void reponseasynchronous(String IP,String connt,Integer port) throws Exception {
		
		InetAddress inetAddress = InetAddress.getByName(IP);
		
        byte[] data = connt.getBytes();  
        // 创建数据报，准备数据  
        DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, port);  
        // 创建一个DatagramSocket  
        DatagramSocket datagramSocket = new DatagramSocket();  
        // 发送请求  
        datagramSocket.send(datagramPacket);
        
        datagramSocket.close();
	}
	
	//下发命令
	public static void responseSend(String IP,String connt,Integer port) throws Exception {
		//1.定義服務器地址，端口號，數據包
	    InetAddress address = InetAddress.getByName(IP);
	    byte[] data = connt.getBytes();
	    //2.創建數據包，包含發送的信息
	    DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
	    //4.向服務器端發送數據包
	    UdpService.response(packet);
	}
	
	//时间类转字符串
	public static String getStringDateShort(Date currentTime) {
		 SimpleDateFormat formatter = new SimpleDateFormat(date);
		 String dateString = formatter.format(currentTime);
		 return dateString;
	}
	
	//字符串转date
	public static Date getDateOne(String date,int sart) {
		SimpleDateFormat formatter = null;
		if(sart == 0) {
			formatter = new SimpleDateFormat("dd-MM-yyyy");
		}else {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		}
		
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//上传图片
	public static String uploadimg(MultipartFile file,HttpServletRequest request) throws Exception{
		
		 String fileName = file.getOriginalFilename();
	     String path = request.getSession().getServletContext().getRealPath("assets/mydoc/img");  
	     System.err.println(fileName+"**"+path);
	     File tempFile = new File(path, new Date().getTime() + String.valueOf(fileName));  
	     if (!tempFile.getParentFile().exists()) {  
	    	 tempFile.getParentFile().mkdir();  
	     }  
	     if (!tempFile.exists()) {  
	         tempFile.createNewFile();  
	     }  
	     file.transferTo(tempFile);  
	     return "assets/mydoc/img/" + tempFile.getName();
	}
	
	
	//短信通知接口
	public static String sendSmsByPost(String path, String postContent) {
		URL url = null;
		try {
			url = new URL(path);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setRequestMethod("POST");// 提交模式
			httpURLConnection.setConnectTimeout(10000);//连接超时 单位毫秒
			httpURLConnection.setReadTimeout(10000);//读取超时 单位毫秒
			// 发送POST请求必须设置如下两行
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			httpURLConnection.setRequestProperty("Charset", "UTF-8");
			httpURLConnection.setRequestProperty("Content-Type", "application/json");

			httpURLConnection.connect();
			OutputStream os=httpURLConnection.getOutputStream();
			os.write(postContent.getBytes("UTF-8"));
			os.flush();
			
			StringBuilder sb = new StringBuilder();
			int httpRspCode = httpURLConnection.getResponseCode();
			if (httpRspCode == HttpURLConnection.HTTP_OK) {
				// 开始获取数据
				BufferedReader br = new BufferedReader(
						new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
				return sb.toString();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void dateForm(String msgt){

		//请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
		String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
		// 短信内容
	    String msg = msgt;
		//手机号码
		String phone = "18569031886";
		//状态报告
		String report= "true";
		
		util.SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone,report);
		
		String requestJson = JSON.toJSONString(smsSingleRequest);
		
		System.out.println("before request string is: " + requestJson);
		
		String response = sendSmsByPost(smsSingleRequestServerUrl, requestJson);
		
		System.out.println("response after request result is :" + response);
		
		SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
		
		System.out.println("response  toString is :" + smsSingleResponse);
		
	
	}
	
	//短信找回密码功能
	public static String MessagedateForm(String msgt,String moble){

		//请求地址请登录253云通讯自助通平台查看或者询问您的商务负责人获取
		String smsSingleRequestServerUrl = "http://smssh1.253.com/msg/send/json";
		// 短信内容
	    String msg = msgt;
		//手机号码
		String phone = moble;
		//状态报告
		String report= "true";
		
		util.SmsSendRequest smsSingleRequest = new SmsSendRequest(account, pswd, msg, phone,report);
		
		String requestJson = JSON.toJSONString(smsSingleRequest);
		
		System.out.println("before request string is: " + requestJson);
		
		String response = sendSmsByPost(smsSingleRequestServerUrl, requestJson);
		
		System.out.println("response after request result is :" + response);
		
		SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
		
		System.out.println("response  toString is :" + smsSingleResponse);
		
		return response;
	}
	
	//定时短信下发
	public static void MessageIssued() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {		
			@Override
			public void run() {
				StringBuffer buff = new StringBuffer();
				System.err.println("进入定时器");
				if(listdate.size() > 0) {
					for (String str : listdate) {
						buff.append(str+"、");
					}
					String miast = buff.toString().substring(0,buff.toString().length()-1);
					String msg = "【253云通讯】你好,编号为"+miast+"的设备已逃离电子围栏，请及时进行处理！！！！！！！";
					System.err.println(msg);
					//dateForm(msg);
				}
				//清空集合内容
				listdate.clear();
			}
		},0,10000*10*3);
	}
	
	//读文本文件的内容
	public static String ReadThefile(String url){
		
		File file = new File(url);
		
		StringBuffer buff = new StringBuffer();
		
		Reader reder = null;
		try {
			reder = new InputStreamReader(new FileInputStream(file));
			
			char[] cbuf = new char[1024];
	 		
			while(reder.read(cbuf) != -1) {
				buff.append(cbuf,0,cbuf.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buff.toString();
	}
	
	public static void downLoad(String urlPatten)
	{
		//File file = new File("")
		
		//创建不同的文件夹目录 
		   File file=new File("D:\\code"); 
		   //判断文件夹是否存在 
		   if (!file.exists()) 
		  { 
		    //如果文件夹不存在，则创建新的的文件夹 
		     file.mkdirs(); 
		  } 

		URL url = null;  
		try 
		{  
			url = new URL(urlPatten);  
		} 
		catch (Exception e) 
		{  
			return;
		}  
		 HttpURLConnection conn = null;
		 InputStream inStream = null;
		try
		{
			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000);
			conn.setConnectTimeout(10000);
			int currentLen = conn.getContentLength();
			System.out.println("FILE LENGTH " + currentLen);
			inStream = conn.getInputStream();
			FileOutputStream fou = new FileOutputStream("D:/code/"+System.currentTimeMillis()+".m4a");
			
			byte[] ab = new byte[1024];
			
			while((currentLen = inStream.read(ab))!=-1)
			{
				fou.write(ab,0, currentLen);
			}
			fou.flush();
			inStream.close();
			conn.getResponseCode();
			conn.disconnect();
			inStream = null;
			conn = null;
		} 
		catch (Exception e) 
		{
			inStream = null;
			conn = null;  
		}
	
	}
	
	//将以，字符串拆分为list
	public static List<String> conversionList(String parameter){
		List<String> list = new ArrayList<>();
		if(parameter.indexOf("，") > 0) {
			String [] date = parameter.split("，");
			for (String str : date) {
				list.add(str);
			}
		}else {
			list.add(parameter);
		}
		return list;
	}
	
	//将以，字符串list拆分为list
	public static List<String> listconversionList(List<String> list){
		List<String> listout = new ArrayList<>();
		for (String str : list) {
			if(str.indexOf("，") > 0) {
				String [] date = str.split("，");
				for (String stt : date) {
					if(!listout.contains(stt)) {
						listout.add(stt);
					}
				}
			}else {
				if(!listout.contains(str)) {
					listout.add(str);
				}
			}
		}
		return listout;
	}
	
	//批量新增序列号
	public static boolean putLabsInfo(int num) {
		boolean labst = false;
		Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection("jdbc:mysql://103.45.106.53:3306/faish?rewriteBatchedStatements=true","root","zchs123456789");
            // 开始时间
            Long begin = new Date().getTime();
            // sql前缀
            String prefix = "INSERT INTO labs (labs_number,labs_user,labs_validation,labs_send) VALUES ";
         	// 保存sql后缀
            StringBuffer suffix = new StringBuffer();
         	// 设置事务为非自动提交
         	conn.setAutoCommit(false);
         	// 比起st，pst会更好些
         	PreparedStatement  pst = (PreparedStatement) conn.prepareStatement("");//准备执行语句
         	   
         	// 外层循环，总提交事务次数
         	for (int i = 1; i <= 10; i++) {
         		suffix = new StringBuffer();
         		// 第j次提交步长
         		for (int j = 1; j <= num/10; j++) {
         			// 构建SQL后缀
     	     		String maxbu =  UUID.randomUUID().toString();//利用UUID生成唯一数的方式
     	     		// 构建SQL后缀
     	     		suffix.append("('" +maxbu+"',null,'0','0'),");
         		}
         		// 构建完整SQL
         		String sql = prefix + suffix.substring(0, suffix.length() - 1);
         		// 添加执行SQL
         		pst.addBatch(sql);
         		// 执行操作
         		pst.executeBatch();
         		// 提交事务
         		conn.commit();
         		// 清空上一次添加的数据
         		suffix = new StringBuffer();
         	}
         	   // 头等连接
         	   pst.close();
         	   conn.close();
            // 结束时间
            Long end = new Date().getTime();
            // 耗时
            System.out.println(num+"条数据插入花费时间 : " + (end - begin) / 1000 + " s"+"  插入完成");
            labst = true;
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return labst;
	}
	
	//获取真实IP
	public static String getIPAddress(HttpServletRequest request) {
		
	    String ip = null;

	    //X-Forwarded-For：Squid 服务代理
	    String ipAddresses = request.getHeader("X-Forwarded-For");

	    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
	        //Proxy-Client-IP：apache 服务代理
	        ipAddresses = request.getHeader("Proxy-Client-IP");
	    }

	    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
	        //WL-Proxy-Client-IP：weblogic 服务代理
	        ipAddresses = request.getHeader("WL-Proxy-Client-IP");
	    }

	    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
	        //HTTP_CLIENT_IP：有些代理服务器
	        ipAddresses = request.getHeader("HTTP_CLIENT_IP");
	    }

	    if (ipAddresses == null || ipAddresses.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
	        //X-Real-IP：nginx服务代理
	        ipAddresses = request.getHeader("X-Real-IP");
	    }

	    //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
	    if (ipAddresses != null && ipAddresses.length() != 0) {
	        ip = ipAddresses.split(",")[0];
	    }

	    //还是不能获取到，最后再通过request.getRemoteAddr();获取
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ipAddresses)) {
	        ip = request.getRemoteAddr();
	    }
	    
	    return ip;
	}
	
	//coap下发命令
	public static void postCoapLowerHair(String url,String value) {
		CoapClient client = new CoapClient(url);
		client.post(value,1);
	}
	
	//处理请求跨域
	public static void crossDomain(HttpServletResponse response) {
		response.setContentType("Content-Type;text/plain;charset=UTF-8");
	}

	
	public static void main(String[] args) {
		//ReadThefile("http://zchs.f3322.net:8001/voice/1530181206327123.m4a");
		//downLoad("http://zchs.f3322.net:8001/voice/1530236902697mysdcardtest");
		//System.out.println(getDateOne("29-06-2018"));
		//System.err.println(getStringDateShort(getDateOne("29-06-2018")));
		//MessagedateForm("【253云通讯】尊敬的用户您好，您在设备云平台的密码为123456789！！！！！！","18569031886");
		System.err.println("975132127qq.com".matches(ToolUtil.email));
	}
}
