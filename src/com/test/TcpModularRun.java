package com.test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月25日 上午9:34:28
* 类说明
*/
public class TcpModularRun implements Runnable{
	
	private Socket socket;
	
	private StringBuilder buff = new StringBuilder();
	
	/** 构造方法 给socket赋值**/
	public TcpModularRun(Socket socket) {
		this.socket = socket;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		try {
			InputStreamReader in = new InputStreamReader(socket.getInputStream());
			OutputStream out = socket.getOutputStream();
			char[] bytes = new char[1024*100];
			while(in.read(bytes)!= -1) {
				buff.append(bytes,0,bytes.length);
			}
			System.err.println("客户端发送过来的信息:"+buff.toString());
			
			/**将时间返回给客户端**/
			out.write(new Date().toLocaleString().getBytes());
			out.flush();
			
			out.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
