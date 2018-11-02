package com.services;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月16日 下午2:15:13
* 类说明
*/
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import csgxcf.commutil.StringUtil;

@ServerEndpoint(value = "/homeMessage/{userId}")
public class HomeSocket {
	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static final CopyOnWriteArraySet<HomeSocket> HSM_Connections = new CopyOnWriteArraySet<HomeSocket>();
	private String mhUserId;
	//private String mhUuserRole;
	private Session mhSession;

	public HomeSocket() {
	}
	
	// 打开连接
	@OnOpen
	public void onOpen(Session session, @PathParam(value = "userId") String userId) {
		this.mhSession = session;
		this.mhUserId = userId;
		//this.mhUuserRole = userRole;

		HSM_Connections.add(this);
		
		//sendMesgText(0, "opened");
	}

	//关闭连接
	@OnClose
	public void onClose() {
		HSM_Connections.remove(this);
	}

	//接收信息
	@OnMessage
	public void onMessage(String message, Session mhssion) {
	}

	//错误信息响应
	@OnError
	public void onError(Throwable throwable) {
		//客户端浏览器刷新：
		System.out.println("flash:->"+throwable.getMessage());
	}
	
	private void sendMesgText(int flag, String mesg) {
		try {
			this.mhSession.getBasicRemote().sendText(mesg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//发送或广播信息
	public static void sendBroadCast(int flag, String userId, String mesg) {
		userId = StringUtil.killNull(userId, "NVLL");
		for (HomeSocket chat : HSM_Connections) {
			//广播条件：管理员、本人、上级用户
			if (StringUtil.isNull(userId) || chat.mhUserId.equals(userId)) {
				try {
					synchronized (chat) {
						chat.sendMesgText(flag, mesg);
					}
				} catch (Exception e0) {
					HSM_Connections.remove(chat);
					try {
						chat.mhSession.close();
					} catch (Exception e1) {
					}
					// HomeSocket.sendBroadCast(-1, "disconnection.");
				}
			}
		}
	}
}
