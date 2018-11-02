package com.servlert;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapHandler;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.CoapResponse;
import org.eclipse.californium.core.CoapServer;

import org.eclipse.californium.core.coap.Request;
import org.eclipse.californium.core.coap.CoAP.ResponseCode;
import org.eclipse.californium.core.server.resources.CoapExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.entity.Datas;
import com.entity.Devices;
import com.services.DatasService;
import com.services.DevicesService;
import com.services.WebsocketClient;

/**
 * Servlet implementation class CoapServlet
 */
@Controller
public class CoapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    private CoapServer server = null;
    
    private CoapClient client = null;
    
    @Autowired
	DevicesService device;
	
	@Autowired
	DatasService dataser;
    
	public void destroy() {
		server.stop();
		server.destroy();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
		System.err.println("无参数的init方法");
		server = new CoapServer();//服务端t服务
        server.add(new CoapResource("t").add(new CoapResource("r"){//服务端子服务r
			@Override  
        		public void handlePOST(CoapExchange exchange) {
						Request request = exchange.advanced().getRequest();//获取注册信息
						String IMEI = request.getURI().substring(request.getURI().indexOf("=")+1);
						System.err.println("IMEI:"+IMEI+"-Connected!");//打印设备编码
						
						//修改设备的状态
						Map<String,Object> map = new HashMap<>();
     				   			map.put("dev_vnum",IMEI);
     				   			map.put("at_id",2);
						Devices dev = device.selectByDeviceMSI(map);
						if(dev != null) {
							exchange.respond(ResponseCode.CHANGED);//返回状态
							//连接客户端
							String uri = "coap:/"+request.getSource()+":"+request.getSourcePort()+"/t/d";
							client = createClient(uri);//调整客户端地址并创建连接信息
							//将在线设备的URI地址存储到数据库中，为命令下发做准备
							//命令下发:client.post("牛皮哄哄的样子", 1);
							//equser.setDevUri(client.getURI());
							//dm.updateByPrimaryKeySelective(equser);
							
							client.observe( //开启订阅服务,开启观察者模式
									new CoapHandler() {
										@Override 
										public void onLoad(CoapResponse response) { //加载订阅，有消息推送时打开
											String content = response.getResponseText();//获取消息
											if(!content.equals("")) {
												System.err.println("Recv: " + content);//打印消息
												
												//websocket实时推送和UDP异步下发
												try {
													Websocketkill(dev,content);
													//如果有配置转发地址和端口，进行转发
											        if(dev.getDev_forport() != null && dev.getDev_forward() != null && dev != null) {
											        	ToolUtil.reponseasynchronous(dev.getDev_forward(), content, dev.getDev_forport());
											        }
												} catch (Exception e) {
													e.printStackTrace();
												}
												
												
												
												//给对象赋值
												Datas datas = new Datas();
									        	  datas.setUser_code(dev.getUser_code());
									        	  datas.setPro_id(dev.getPro_id());
									        	  datas.setDev_codes(dev.getDev_code());
									        	  datas.setDs_Swtch(1);
									        	  datas.setDs_ip(uri);
									        	  datas.setDs_value(content);
									        	  datas.setDs_time(new Date());
									        	  datas.setDs_ispush(0);
											
													
												//插入对象
									        	int num = dataser.insertSelective(datas);
										        if(num > 0) {//修改设备信息
										        	dev.setDev_sumnum(dev.getDev_sumnum()+1);
										        	dev.setDev_uptime(new Date());
										        	dev.setDev_ispassca(1);
										        	device.updateByPrimaryKeySelective(dev);
										        }
											}
										}
									
										@Override 
										public void onError() {
										
											System.err.println("RecvError!");//出错
										}
							});                
						}
						/*else {
							exchange.respond(ResponseCode.CHANGED);//返回状态
							//连接客户端
							String uri = "coap:/"+request.getSource()+":"+request.getSourcePort()+"/t/d";
							client = createClient(uri);//调整客户端地址并创建连接信息
							
							reion = client.observe( //开启订阅服务,开启观察者模式
									new CoapHandler() {
										@Override 
										public void onLoad(CoapResponse response) { //加载订阅，有消息推送时打开
											String content = response.getResponseText();//获取消息
											if(!content.equals("")) {
												System.err.println("Recv: " + content);//打印消息
												content = "value:"+content+",IMEI:"+IMEI;
												//获取本地时间
												LocalDateTime shij = LocalDateTime.now();
												DateTimeFormatter dateToStrFormatter = DateTimeFormatter.ofPattern(ToolUtil.date);
												String dateStr = dateToStrFormatter.format(shij);
													
												//给对象赋值
												Error err = new Error();
													  err.setErDate(dateStr);
													  err.setErType(ToolUtil.coap);
													  err.setErIp(client.getURI());
													  err.setErValue(content);
													
												//插入对象
												em.insertSelective(err);
												
												response.advanced();
											}
												
												
										}
										
										@Override 
										public void onError() {
											System.err.println("RecvError!");//出错
											reion.proactiveCancel();
										}
							});
						}*/
				}  
			}));
        server.start();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void Websocketkill(Devices dev,String data) throws Exception {
		for (String str : ToolUtil.listuser) {
			System.err.println("用户的标识有:"+str);
		}
        if(ToolUtil.listuser.contains(dev.getDev_code())) {//判断对方是否在线
        	for (String str : ToolUtil.listuser) {
				System.err.println("用户的标识有:"+str);
			}
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

}
