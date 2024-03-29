package com.test;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月26日 下午6:13:05
* 类说明
*/
public class TCPClientReadThread implements Runnable {
	private Selector selector;
 
	public TCPClientReadThread(Selector selector) {
		this.selector = selector;
 
		new Thread(this).start();
	}
 
	@Override
	public void run() {
		try {
			while (selector.select() > 0) {
				// 遍历每个有可用IO操作Channel对应的SelectionKey
				for (SelectionKey sk : selector.selectedKeys()) {
 
					// 如果该SelectionKey对应的Channel中有可读的数据
					if (sk.isReadable()) {
						// 使用NIO读取Channel中的数据
						SocketChannel sc = (SocketChannel) sk.channel();
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						sc.read(buffer);
						buffer.flip();
 
						// 将字节转化为为UTF-8的字符串
						Charset inCharset = Charset.forName("UTF-8");
						String receivedString = inCharset.decode(buffer).toString();
 
						// 控制台打印出来
						System.out.println("接收到来自服务器" + sc.socket().getRemoteSocketAddress() + "的信息:" + receivedString);
 
						// 为下一次读取作准备
						sk.interestOps(SelectionKey.OP_READ);
					}
 
					// 删除正在处理的SelectionKey
					selector.selectedKeys().remove(sk);
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
