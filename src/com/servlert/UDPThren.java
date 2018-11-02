package com.servlert;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月10日 上午11:33:32
* 类说明
*/
public class UDPThren extends Thread{
	
	public DatagramPacket packet = null;
	
	public DatagramSocket socket = null;
	
	public boolean kayy = true;
	
	public UDPThren(DatagramPacket packet,DatagramSocket socket) {
		this.packet = packet;
		this.socket = socket;
	}

	public void colses() {
		try {
			kayy = false;
			send();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() 
	{
		 try 
         {  
			while(kayy)
			{  
	           
	                UdpService.receive(packet);
	                byte[] bt = new byte[ToolUtil.max_len]; 
	                bt = packet.getData();
	    			String reuslt = new String(bt,0,packet.getLength(),ToolUtil.cherst);
	    			System.out.println(reuslt);
	    	        System.err.println(packet.getAddress()+"***"+packet.getPort());
	        }
         } 
     		catch (Exception e) 
		 {  
     		e.printStackTrace();
		 }  
	}
	
	public static void send() throws Exception {
		//1.定義服務器地址，端口號，數據包
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 9999;
        byte[] data = "谢谢您的服务".getBytes();
        //2.創建數據包，包含發送的信息
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        //3.創建DatagramSocket對象
        DatagramSocket socket = new DatagramSocket();
        //4.向服務器端發送數據包
        socket.send(packet);
        //关闭资源
        socket.close();
	}
}
