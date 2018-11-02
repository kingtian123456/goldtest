package com.servlert;

import java.io.IOException;  
import java.net.DatagramPacket;  
import java.net.DatagramSocket;  
import java.net.SocketException; 
/** 
 * @说明 UDP连接服务端，这里一个包就做一个线程处理 
 * @author 崔素强（http://cuisuqiang.iteye.com/） 
 * @version 1.0 
 * @since 
 */  
public class UdpService {  
	
    private static DatagramSocket datagramSocket = null; // 连接对象   
    /** 
     * 接收数据包，该方法会造成线程阻塞 
     * @return 
     * @throws Exception  
     * @throws IOException 
     */  
    public static DatagramPacket receive(DatagramPacket packet) throws Exception {  
        try {  
            datagramSocket.receive(packet);  
            return packet;  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
    /** 
     * 将响应包发送给请求端 
     * @param bt 
     * @throws IOException 
     */  
    public static void response(DatagramPacket packet) {  
        try {  
            datagramSocket.send(packet);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    /** 
     * 初始化连接 
     * @throws SocketException 
     */  
    public static void init(){  
        try {    
            datagramSocket = new DatagramSocket(8799);  
            datagramSocket.setSoTimeout(0);
            System.out.println("服务端已经启动");  
        } catch (Exception e) {  
            datagramSocket = null;  
            System.err.println("服务端启动失败");  
            e.printStackTrace();  
        } 
    }  
    
    /**
     *关闭套接字 */
    public static void colsed() {
    	datagramSocket.close();
    }
}  