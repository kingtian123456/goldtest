package com.servlert;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.net.SocketAddress;
import java.util.Date;
import java.util.HashMap;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.json.JSONObject;
import com.entity.Datas;
import com.entity.Devices;

import com.services.DatasService;
import com.services.DevicesService;
import com.services.WebsocketClient;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月15日 下午4:56:33
* UDP服务端接收类
*/
public class UDPPool implements Runnable{
	
	private DatagramPacket data = null;
	
	private static ExecutorService service =  Executors.newFixedThreadPool(2000);
	
	private static boolean kass = true;
	
	private DevicesService device = null;
	
	private DatasService dataser = null;
	
	public UDPPool(DatagramPacket data,DevicesService device,DatasService datas) {
		this.data = data;
		this.device = device;
		this.dataser = datas;
	}
	
	//接收客户端发过来的信息
	public void run() {
		try {
	       String reuslt = new String(data.getData(),0,data.getLength(),ToolUtil.cherst);
	       SocketAddress address = data.getSocketAddress();
	       //立即回复给设备端
	       sendOne(reuslt,address);
	       if(reuslt != null && !reuslt.equals("")) {
				JSONObject jsonobject = new JSONObject(reuslt);
		        String dev_vnum = jsonobject.get("MSI").toString();
		        String value = jsonobject.get("data").toString();
		        Map<String,Object> map = new HashMap<>();
		        				   map.put("dev_vnum",dev_vnum);
		        				   map.put("at_id",1);
		        Devices dev = device.selectByDeviceMSI(map);
		        
		        Websocketkill(dev,value);
		        //如果有配置转发地址和端口，进行转发
		        if(dev.getDev_forport() != null && dev.getDev_forward() != null && dev != null) {
		        	ToolUtil.reponseasynchronous(dev.getDev_forward(), reuslt, dev.getDev_forport());
		        }
		        
		        if(dev != null) {//存入数据库
		        	Datas datas = new Datas();
		        	  datas.setUser_code(dev.getUser_code());
		        	  datas.setPro_id(dev.getPro_id());
		        	  datas.setDev_codes(dev.getDev_code());
		        	  datas.setDs_Swtch(1);
		        	  datas.setDs_ip(data.getAddress().getHostAddress());
		        	  datas.setDs_port(data.getPort());
		        	  datas.setDs_value(value);
		        	  datas.setDs_time(new Date());
		        	  datas.setDs_ispush(0);
		        	  
		        	int num = dataser.insertSelective(datas);
		        	if(num > 0) {//修改设备信息
		        		dev.setDev_sumnum(dev.getDev_sumnum()+1);
		        		dev.setDev_uptime(new Date());
		        		dev.setDev_ispassca(1);
		        		device.updateByPrimaryKeySelective(dev);
		        	}
		        }
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//返回给数据库
	private void sendOne(String data,SocketAddress address) {
		//返回给设备
	    byte[] datas = data.getBytes();
	    //2.創建數據包，包含發送的信息
	    DatagramPacket packet = new DatagramPacket(datas, datas.length, address);
	    UdpService.response(packet);
	}
	
	private void Websocketkill(Devices dev,String data) throws Exception {
		for (String str : ToolUtil.listuser) {
			System.err.println("用户的标识有:"+str);
		}
        if(ToolUtil.listuser.contains(dev.getDev_code())) {//判断对方是否在线
        	if(ToolUtil.list.contains(dev.getDev_vnum())) {
	        	System.err.println("不需要创建");
	        }else {
	        	System.err.println("需要创建");
	        	ToolUtil.getList(dev.getDev_vnum());
	        	WebsocketClient.OpenConnect(dev.getDev_vnum());
	        }
	        
	        WebsocketClient.SendMessage(ToolUtil.getJSON(dev.getDev_vnum(),data,dev.getDev_code()));
        }
	}
	
	//开启UDP服务端
	public static void openServer(DevicesService device,DatasService datas) throws Exception {
		UdpService.init();
		while(kass) {
			byte[] bytes = new byte[1024*64];
			DatagramPacket Packet = new DatagramPacket(bytes, bytes.length);
			UdpService.receive(Packet);
			service.execute(new UDPPool(Packet,device,datas));
		}
	}
	
	//关闭UDP 服务端
	public static void colse() {
		kass = false;
		try {
			send();
			UdpService.colsed();
			service.shutdownNow();
		} catch (Exception e) {
			System.err.println("错误信息");
		}
	}
	
	//关闭UDP 给服务端发一个空消息  防止报错
	public static void send() throws Exception {
		//1.定義服務器地址，端口號，數據包
        InetAddress address = InetAddress.getByName("42.51.38.179");
        int port = 8799;
        byte[] data = "".getBytes();
        //2.創建數據包，包含發送的信息
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        //3.創建DatagramSocket對象
        DatagramSocket socket = new DatagramSocket();
        //4.向服務器端發送數據包
        socket.send(packet);
        //关闭资源
        //socket.close();
	}
}
