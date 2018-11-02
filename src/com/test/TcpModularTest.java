package com.test;

import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月24日 下午6:19:30
* 测试TCP通道服务端
*/

public class TcpModularTest {

	@SuppressWarnings("resource")
	/**
	 * 启动TCP服务端开启8668端口的方法
	 * 新建线程池进行数据接收！！！
	 * **/
	public static void openServer() throws Exception {
		ServerSocket serversocket = new ServerSocket(8668);
		ExecutorService service = Executors.newFixedThreadPool(200);
		while(true) {
			TcpModularRun tcprun = new TcpModularRun(serversocket.accept());
			service.execute(tcprun);
		}
	}
	
	/**
	 * main中启动TCP服务端
	 * **/
	public static void main(String[] args) throws Exception{
		openServer();
 	}
	
}
