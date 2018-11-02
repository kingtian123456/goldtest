package com.services;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月16日 下午4:26:35
* 类说明
*/
import org.apache.log4j.Logger;   
import org.java_websocket.client.WebSocketClient;  
import org.java_websocket.drafts.Draft_6455;  

import org.java_websocket.handshake.ServerHandshake;   
import java.net.URI;  
import java.net.URISyntaxException;  
  
  
public class WebsocketClient {  
      
    private static Logger logger = Logger.getLogger(WebsocketClient.class);  
    public static WebSocketClient client;  
    public static WebSocketClient OpenConnect(String UserID) throws Exception {  
        try {  
            client = new WebSocketClient(new URI("ws://42.51.38.179:80/chatDemo/"+UserID),new Draft_6455()) {  
                @Override  
                public void onOpen(ServerHandshake serverHandshake) {  
                     logger.info("握手成功");  
                }  
  
                @Override  
                public void onMessage(String msg) {  
                     logger.info("收到消息=========="+msg);  
                     if(msg.equals("over")){  
                         client.close();  
                     }  
                       
                }  
  
                @Override  
                public void onClose(int i, String s, boolean b) {  
                     logger.info("链接已关闭");  
                }  
  
                @Override  
                public void onError(Exception e){  
                    e.printStackTrace();  
                    logger.info("发生错误已关闭");  
                }  
            };  
        } catch (URISyntaxException e) {  
            e.printStackTrace();  
        }  
  
        client.connect();
        Thread.sleep(2000);
        logger.info(client.getDraft());  
      // while(!client.getReadyState().equals(WebSocket.READYSTATE.OPEN)){  
        //   logger.info("正在连接...");  
        //}   
       return client;
    }  
    
    //发送消息
    public static void SendMessage(String message) {
    	client.send(message);
    }
}  
