package com.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPtest {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		DatagramSocket socket1 = null;
		try {
			socket1 = new DatagramSocket(8888);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		while(true){
		    byte[] buf = new byte[1024];            
		    DatagramPacket dp1 =new DatagramPacket(buf, buf.length);           
		    try {
				socket1.receive(dp1);
			} catch (IOException e) {
				e.printStackTrace();
			}		    
		    String data = new String(dp1.getData(),0,dp1.getLength());
		    String ip = dp1.getAddress().getHostAddress();
		    int port = dp1.getPort();
		    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        System.out.println("Time:" + df.format(new Date()));
		    System.out.println("ModeAddr:"+ip+":"+port+" "+"Payload:"+data);			    
		    byte[] buf1 = data.getBytes();		    
		    try {
				dp1 = new DatagramPacket(buf1,buf1.length,InetAddress.getByName(ip),port);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}               
            try {
				socket1.send(dp1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Echo:"+ip+":"+port+" "+"Payload:"+data);	
            System.out.println("------------------------------------------------------");
		}
	}
}
