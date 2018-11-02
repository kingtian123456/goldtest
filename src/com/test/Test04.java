package com.test;
import java.io.IOException;  
import java.net.DatagramPacket;  
import java.net.DatagramSocket;  
import java.net.InetAddress;  
import java.net.SocketException;  
import java.net.UnknownHostException;  

public class Test04 {  
  
    public static void main(String[] args) {  
          
        try {  
            // 定义服务器地址  
            InetAddress inetAddress = InetAddress.getByName("192.168.11.123");
            byte[] data = "{msi:\"123456789\",device:\"admin\",data:\"1000000\",time:\"1452033541\"}".getBytes();  
            // 创建数据报，准备数据  
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, 9563);  
            // 创建一个DatagramSocket  
            DatagramSocket datagramSocket = new DatagramSocket();  
            // 发送请求  
            datagramSocket.send(datagramPacket);  
              
              
            // *** 接收服务端响应回来的数据 ***  
              
            byte[] resData = new byte[2048];  
            // 创建数据报，用户接受客户端请求的数据  
            DatagramPacket resDatagramPacket = new DatagramPacket(resData, resData.length);  
            // 接收客户端请求的数据（在接收到数据之前一直处于阻塞状态）  
            datagramSocket.receive(resDatagramPacket);  
            // 获取请求的数据，并转成String打印出来  
            String serverData = new String(resData, 0, resDatagramPacket.getLength());  
            System.out.println("我是客户端，服务端响应的数据为：" + serverData);  
              
              
            datagramSocket.close();  
              
        } catch (UnknownHostException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (SocketException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
}  