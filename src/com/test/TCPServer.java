package com.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月26日 下午5:05:13
* 类说明
*/
public class TCPServer {
	// 缓冲区大小
	private static final int BufferSize = 1024;
 
	// 超时时间，单位毫秒
	private static final int TimeOut = 3000;
 
	// 本地监听端口
	private static final int ListenPort = 8668;
 
	public static void main(String[] args) throws IOException {
		// 创建选择器
		Selector selector = Selector.open();
 
		// 打开监听信道
		ServerSocketChannel listenerChannel = ServerSocketChannel.open();
 
		// 与本地端口绑定
		listenerChannel.socket().bind(new InetSocketAddress(ListenPort));
 
		// 设置为非阻塞模式
		listenerChannel.configureBlocking(false);
 
		// 将选择器绑定到监听信道,只有非阻塞信道才可以注册选择器.并在注册过程中指出该信道可以进行Accept操作
		//一个server socket channel准备好接收新进入的连接称为“接收就绪”
		listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
 
		// 创建一个处理协议的实现类,由它来具体操作
		TCPProtocol protocol = new TCPProtocolImpl(BufferSize);
 
		// 反复循环,等待IO
		while (true) {
			// 等待某信道就绪(或超时)
			int keys = selector.select(TimeOut);
			//刚启动时连续输出0，client连接后一直输出1
			//System.out.print(keys);
			if (keys == 0) {
				System.out.println("独自等待.");
				continue;
			}
			// 取得迭代器.selectedKeys()中包含了每个准备好某一I/O操作的信道的SelectionKey
			Set<SelectionKey> set = selector.selectedKeys();
			//输出为1
			//System.out.println("selectedKeysSize:" + set.size());
			Iterator<SelectionKey> keyIter  = set.iterator();
			
			while (keyIter.hasNext()) {
				SelectionKey key = keyIter.next();
 
				try {
					if (key.isAcceptable()) {
						System.out.println("acceptable");
						//该方法在内部，会将interest由OP_ACCEPT改为OP_READ
						//如果不执行下面的语句，则会一直是accept状态（初始时设置为了accept），无法进入后面的两个if语句
						//console一直打印上面的语句
						protocol.handleAccept(key);
					}
 
					if (key.isReadable()) {
						// 从客户端读取数据
						System.out.println("readable");
						protocol.handleRead(key);
					}
 
					if (key.isValid() && key.isWritable()) {
						//客户端连接一次后，N次连续进入该方法
						//System.out.println("writable");//连续输出
						protocol.handleWrite(key);
					}
				} catch (IOException ex) {
					// 出现IO异常（如客户端断开连接）时移除处理过的键
					keyIter.remove();
					continue;
				}
 
				// 移除处理过的键
				keyIter.remove();
			}
		}
	}
}
