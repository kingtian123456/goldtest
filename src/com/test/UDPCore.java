package com.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class UDPCore {
	
	static DatagramSocket socket = null;
	static DatagramSocket socket1 = null;
	static DatagramPacket dp = null;
	static DatagramPacket dp1 = null;	
	static String data;
	static String ip;
	static int port;	
	static String DATA;
	static String IP;
	static int PORT;
	
	static void udpForward(){
		
		try {
			socket = new DatagramSocket(7777);
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
        while(true){
            byte[] buf = new byte[1024];            
            DatagramPacket dp =new DatagramPacket(buf, buf.length);           
            try {
				socket.receive(dp);
			} catch (IOException e) {
				e.printStackTrace();
			}          
            data = new String(dp.getData(),0,dp.getLength());
            ip = dp.getAddress().getHostAddress();
            port = dp.getPort();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("Time:" + df.format(new Date()));
            System.out.println("ModeAddr:"+ip+":"+port+" "+"Recv Payload:"+data);	       
            String ipCheck[] = data.split(",");
            String regex = 
            		  "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                    + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\:"
                    + "(1(02[4-9]|0[3-9][0-9]|[1-9][0-9]{2})|[2-9][0-9]{3}|[1-5][0-9]{4}|6[0-4][0-9]{3}|65[0-4][0-9]{2}|655[0-2][0-9]|6553[0-5])$";
            if (ipCheck[0].matches(regex)) {                       
            	String a[] = data.split(":");
            	String b[] = a[1].split(",");
            	IP = a[0];
            	PORT = Integer.valueOf(b[0]);
            	DATA = b[1];
            	System.out.println("DestAddr:"+IP+":"+PORT+" "+"Payload:"+DATA);
            	byte[] buf1 = DATA.getBytes();                
            	try {
            		dp = new DatagramPacket(buf1,buf1.length,InetAddress.getByName(IP),PORT);
            	} catch (UnknownHostException e) {
            		e.printStackTrace();
            	}                
            	try {
            		socket.send(dp);
            	} catch (IOException e) {
            		e.printStackTrace();
            	}         
            	System.out.println("Succesd send:"+IP+":"+PORT+" "+"Payload:"+DATA);
            	System.out.println("------------------------------------------------------");
            	} else {
            		System.out.println("Format Wrong!");
            		System.out.println("------------------------------------------------------");
            	}
        	}
        }
	
	static void udpEcho() {
		
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
		    data = new String(dp1.getData(),0,dp1.getLength());
		    ip = dp1.getAddress().getHostAddress();
		    port = dp1.getPort();
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
				e.printStackTrace();
			}
            System.out.println("Echo:"+ip+":"+port+" "+"Payload:"+data);	
            System.out.println("------------------------------------------------------");
		}
	}
		
	public static String dataConvert(String src) {  
        String temp = "";  
        for (int i = 0; i < src.length() / 2; i++) {  
            temp = temp  
                    + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),  
                            16).byteValue();  
        }        
        return temp;         
    }
		
	public static void main(String[] args) throws Exception {
		Thread thread1 = new Thread(new Runnable() {
			public void run() {
				udpForward();
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			public void run() {
				udpEcho();
			}
		});
		
		thread1.start();
		thread2.start();
	}
}
