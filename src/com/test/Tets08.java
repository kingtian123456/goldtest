package com.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月27日 下午4:24:02
* 类说明
*/
public class Tets08 {
	
	
	
	 public static String send1(String url, String filePath) throws IOException 
	    {
		 StringBuilder sb = new StringBuilder(512);
	        File file = new File(filePath);
	        if (!file.exists() || !file.isFile()) 
	        {
	            return null;
	        }
	 
	        /**
	         * 缁楊兛绔撮柈銊ュ瀻
	         */
	        URL urlObj = new URL(url);
	        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
	 
	        /**
	         * 鐠佸墽鐤嗛崗鎶芥暛閸婏拷
	         */
	        con.setRequestMethod("POST"); // 娴狀櫀ost閺傜懓绱￠幓鎰唉鐞涖劌宕熼敍宀勭帛鐠侇槏et閺傜懓绱�
	        con.setConnectTimeout(10000);
	        con.setReadTimeout(10000);
	        con.setDoInput(true);
	        con.setDoOutput(true);
	        con.setUseCaches(false); // post閺傜懓绱℃稉宥堝厴娴ｈ法鏁ょ紓鎾崇摠
	 
	        // 鐠佸墽鐤嗙拠閿嬬湴婢剁繝淇婇幁锟�
	        con.setRequestProperty("Connection", "Keep-Alive");
	        con.setRequestProperty("Charset", "UTF-8");
	 
	        // 鐠佸墽鐤嗘潏鍦櫕
	        String BOUNDARY = "----------" + System.currentTimeMillis();
	        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
	                + BOUNDARY);
	 
	        // 缁楊兛绔撮柈銊ュ瀻閿涳拷
	        sb.append("--"); // ////////韫囧懘銆忔径姘⒈闁挾鍤�
	        sb.append(BOUNDARY);
	        sb.append("\r\n");
	        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
	                + file.getName() + "\"\r\n");
	        sb.append("Content-Type:application/octet-stream\r\n\r\n");
	 
	        byte[] head = sb.toString().getBytes("utf-8");
	 
	        // 閼惧嘲绶辨潏鎾冲毉濞达拷
	 
	        OutputStream out = new DataOutputStream(con.getOutputStream());
	        out.write(head);
	 
	        // 閺傚洣娆㈠锝嗘瀮闁劌鍨�
	        DataInputStream in = new DataInputStream(new FileInputStream(file));
	        int bytes = 0;
	        byte[] bufferOut = new byte[1024];
	        while ((bytes = in.read(bufferOut)) != -1) {
	            out.write(bufferOut, 0, bytes);
	        }
	        in.close();
	 
	        // 缂佹挸鐔柈銊ュ瀻
	        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 鐎规矮绠熼張锟介崥搴㈡殶閹诡喖鍨庨梾鏃傚殠
	 
	        out.write(foot);
	 
	        out.flush();
	        out.close();
	        int len = con.getContentLength();
	        System.out.println(len);
	        char[] abc = new char[1024];
	        StringBuffer buff = new StringBuffer();
	        
	        InputStreamReader ins = new InputStreamReader(con.getInputStream());
	        
	        while(ins.read(abc) != -1) {
	        	buff.append(abc, 0, abc.length);
	        }

	        System.out.println(buff.toString());
	        
	        con.getResponseCode();
	        con.disconnect();
	 
	        return buff.toString();
	    }
	
	    /**
	     * 多文件上传的方法
	     * 
	     * @param actionUrl：上传的路径
	     * @param uploadFilePaths：需要上传的文件路径，数组
	     * @return
	     */
		@SuppressWarnings("finally")
	    public static String uploadFile(String actionUrl, String uploadFilePaths) {
	        String end = "\r\n";
	        String twoHyphens = "--";
	        String boundary = "*****";
	        
	        DataInputStream in = null;
	        OutputStream ds = null;
	        InputStream inputStream = null;
	        InputStreamReader inputStreamReader = null;
	        BufferedReader reader = null;
	        StringBuffer resultBuffer = new StringBuffer();
	        String tempLine = null;

	        try {
	            // 统一资源
	            URL url = new URL(actionUrl);
	            // 连接类的父类，抽象类
	            URLConnection urlConnection = url.openConnection();
	            // http的连接类
	            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

	            // 设置是否从httpUrlConnection读入，默认情况下是true;
	            httpURLConnection.setDoInput(true);
	            // 设置是否向httpUrlConnection输出
	            httpURLConnection.setDoOutput(true);
	            // Post 请求不能使用缓存
	            httpURLConnection.setUseCaches(false);
	            // 设定请求的方法，默认是GET
	            httpURLConnection.setRequestMethod("POST");
	            // 设置字符编码连接参数
	            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
	            // 设置字符编码
	            httpURLConnection.setRequestProperty("Charset", "UTF-8");
	            // 设置请求内容类型
	            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

	            // 设置DataOutputStream
	            ds = new DataOutputStream(httpURLConnection.getOutputStream());
	           /* for (int i = 0; i <= uploadFilePaths.length; i++) {
	                String uploadFile = uploadFilePaths;
	                String filename = uploadFile.substring(uploadFile.lastIndexOf("//") + 1);
	                ds.writeBytes(twoHyphens + boundary + end);
	                ds.writeBytes("Content-Disposition: form-data; " + "name=\"file" + 1 + "\";filename=\"" + filename
	                        + "\"" + end);
	                ds.writeBytes(end);
	                FileInputStream fStream = new FileInputStream(uploadFile);
	                int bufferSize = 1024;
	                byte[] buffer = new byte[bufferSize];
	                int length = -1;
	                while ((length = fStream.read(buffer)) != -1) {
	                    ds.write(buffer, 0, length);
	                }
	                ds.writeBytes(end);
	                /* close streams 
	                fStream.close();
	            }
	            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
	            /* close streams 
	            ds.flush();*/
	            ds = httpURLConnection.getOutputStream();
	            File file = new File(uploadFilePaths);
	            in = new DataInputStream(new FileInputStream(file));
	           
	            int bytes = 0;
	            byte[] buffer = new byte[1024];
	            while ((bytes = in.read(buffer)) != -1) {
	            	ds.write(buffer, 0, bytes);
	            }
	            ds.flush();
	            
	            System.err.println(httpURLConnection.getResponseCode());
	            if (httpURLConnection.getResponseCode() >= 300) {
	                throw new Exception(
	                        "HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
	            }

	            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
	                inputStream = httpURLConnection.getInputStream();
	                inputStreamReader = new InputStreamReader(inputStream);
	                reader = new BufferedReader(inputStreamReader);
	                tempLine = null;
	                resultBuffer = new StringBuffer();
	                while ((tempLine = reader.readLine()) != null) {
	                    resultBuffer.append(tempLine);
	                    resultBuffer.append("\n");
	                }
	            }

	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } finally {
	            if (ds != null) {
	                try {
	                    ds.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            if (inputStreamReader != null) {
	                try {
	                    inputStreamReader.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            if (inputStream != null) {
	                try {
	                    inputStream.close();
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }

	            return resultBuffer.toString();
	        }
	    }
		
	
	public static void main(String[] args) throws Exception {

		String str = send1("http://zchs.f3322.net:8001/uploadAppVoice","C:/Users/Administrator/Desktop/123.m4a");
		
		System.out.println(str);
	}
}
