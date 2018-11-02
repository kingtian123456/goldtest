package com.test;

import java.io.IOException;
import java.nio.channels.SelectionKey;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月26日 下午5:06:40
* 类说明
*/
public interface TCPProtocol {
	/**
	 * 接收一个SocketChannel的处理
	 * 
	 * @param key
	 * @throws IOException
	 */
	void handleAccept(SelectionKey key) throws IOException;
 
	/**
	 * 从一个SocketChannel读取信息的处理
	 * 
	 * @param key
	 * @throws IOException
	 */
	void handleRead(SelectionKey key) throws IOException;
 
	/**
	 * 向一个SocketChannel写入信息的处理
	 * 
	 * @param key
	 * @throws IOException
	 */
	void handleWrite(SelectionKey key) throws IOException;
}