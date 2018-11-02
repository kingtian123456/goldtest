package com.services;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月17日 上午10:48:40
* websocket 对话服务端
*/
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;    
import javax.websocket.OnClose;  

import javax.websocket.OnError;  
import javax.websocket.OnMessage;  
import javax.websocket.OnOpen;  

import javax.websocket.Session;  
import javax.websocket.server.PathParam;  
import javax.websocket.server.ServerEndpoint;

import com.servlert.ToolUtil;
import csgxcf.commutil.StringUtil;
import net.sf.json.JSONObject;  
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。  
//chatDemo：表示访问的具体url。{sendUser}：表示在url上的参数  
@ServerEndpoint("/chatDemo/{sendUser}")  
public class ChatScoket{  
	
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 用户在线数  
    private static int onLineCount = 0;  
    // 当前的websocket对象  
    private static ConcurrentHashMap<String, ChatScoket> webSocketMap = new ConcurrentHashMap<String, ChatScoket>();  
    // 当前会话,属于websocket的session  
    private Session session;  
    // 聊天信息  
    private String sendUser;// 当前用户  
    private String toUser;// 接收人    
    /** 
     * 连接建立成功调用的方法 
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据 
     * @PathParam("sendUser") 表示可以在访问的时候带上参数，参数：sendUser为  @ServerEndpoint("/chatDemo/{sendUser}") 
     * @throws IOException 
     */  
    @OnOpen  
    public void onOpen(@PathParam("sendUser") String sendUser,Session session) throws IOException {  
        this.sendUser = sendUser;  
        this.session = session;
        ToolUtil.addUserList(sendUser);
        addOnlineCount();  
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount() + " 当前session是" + session.hashCode());  
        
        webSocketMap.put(sendUser, this);//当前用户的websocket  
        // 刷新在线人数  
        for (ChatScoket chat : webSocketMap.values()) {  
            //使用if判断是要统计人数还是发送消息  
            chat.sendMessage("count",getOnlineCount() + "");  
        }  
    }  
  
    /** 
     * 连接关闭所调用的方法 
     *  
     * @return 
     * @throws IOException  
     */  
    @OnClose  
    public void onClose() throws IOException {  
        // 在线数减1  
        subOnlineCount();  
          
        for (ChatScoket chat : webSocketMap.values()) {  
            //说明当前的session已经被关闭  
            if(chat.session != this.session){  
                chat.sendMessage("count", getOnlineCount() + "");  
            }  
        }  
        webSocketMap.remove(sendUser);
        ToolUtil.deleteUserList(sendUser);
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());  
    }  
  
    /** 
     * 收到客户端的消息后所调用的方法 
     *  
     * @param message  客户端发送过来的消息 
     * @param session  可选的参数，同上 
     * @throws IOException 
     */  
    @OnMessage  
    public void onMessage(String jsonMsg, Session session) throws IOException {
        JSONObject jsonOject = JSONObject.fromObject(jsonMsg);
        jsonOject.put("date", df.format(new Date()));
        toUser = jsonOject.getString("toUser");
        sendUser = jsonOject.getString("nickname");
        String userId = StringUtil.killNull(toUser, "NVLL");
        if("NVLL".equals(userId)) {
        	for (String socket : webSocketMap.keySet()) {
				//广播条件：管理员、本人、上级用户
				//if (StringUtil.isNull(userId) || webSocketMap.get(socket).sendUser.equals(userId)) {
					try {
						synchronized (webSocketMap.get(socket)) {
							jsonOject.put("isSelf", webSocketMap.get(socket).session.equals(session));
							System.err.println(jsonOject+"1111111111111111");
							webSocketMap.get(socket).sendMessage("send",jsonOject.toString());
						}
					} catch (Exception e0) {
						webSocketMap.get(socket).remove(webSocketMap.get(socket));
						try {
							webSocketMap.get(socket).session.close();
						} catch (Exception e1) {
						}
						// HomeSocket.sendBroadCast(-1, "disconnection.");
					}
				
			}
        }else {  
	        // 得到接收人  
	        ChatScoket user = webSocketMap.get(toUser);
	        ChatScoket min = webSocketMap.get(sendUser);
	        if (user == null) {//如果接收人不存在则保持到数据库  
	            
	            return;  
	        } 
	        jsonOject.put("isSelf",user.session.equals(session));
	        user.sendMessage("send",jsonOject.toString());
	        jsonOject.put("isSelf",min.session.equals(session));
        	System.err.println(jsonOject+"2222222222222222222");
	        
	        min.sendMessage("send",jsonOject.toString());
        }
	       
    }  
    
    //关闭连接
    private void remove(ChatScoket chatScoket) {
		for (String key : webSocketMap.keySet()) {
			webSocketMap.get(key).remove(webSocketMap.get(key));
		}
	}

	/** 
     * 发成错误所调用的方法 
     * @param session 
     * @param error 
     */  
    @OnError  
    public void onError(Session session, Throwable error) {  
        System.out.println("发生错误");  
        //error.printStackTrace();  
    }  
  
    /** 
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。 
     * @param type 根据类型判断是要进行在线人数统计还是发送消息  count：人数统计  send：发送消息 
     * @param message 
     * @param receive 
     * @throws IOException 
     */  
    public void sendMessage(String type,String message) throws IOException {  
        if(type.equals("count")){  
            this.session.getBasicRemote().sendText("count:" + message);//在jsp判断是否包含count  
        }else{  
            this.session.getBasicRemote().sendText(message);//提供阻塞式的消息发送方式  
            // this.session.getAsyncRemote().sendText(message);//提供非阻塞式的消息传输方式。  
        }  
          
          
    }  
  
    // 获得当前在线人数  
    public static synchronized int getOnlineCount() {  
        return onLineCount;  
    }  
  
    // 新用户  
    public static synchronized void addOnlineCount() {  
        ChatScoket.onLineCount++;  
    }  
  
    // 移除退出用户  
    public static synchronized void subOnlineCount() {  
        ChatScoket.onLineCount--;  
    }  
}  
