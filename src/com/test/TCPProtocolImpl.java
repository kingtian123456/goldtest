package com.test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月26日 下午5:07:37
* 类说明
*/
public class TCPProtocolImpl implements TCPProtocol {
	
	private int bufferSize;
	
	private int num = 0;
	 
	public TCPProtocolImpl(int bufferSize) {
		this.bufferSize = bufferSize;
	}
	
	/**
	 * 将可连接 调整为 可读取
	 */
	public void handleAccept(SelectionKey key) throws IOException {
		SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
		clientChannel.configureBlocking(false);
		clientChannel.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
	}
 
	private ByteBuffer buffer = ByteBuffer.allocate(1024);
	
	public void handleRead(SelectionKey key) throws IOException {
		// 获得与客户端通信的信道
		SocketChannel clientChannel = (SocketChannel) key.channel();
 
		buffer.position(0);
		buffer.limit(1024);
 
		// 读取信息获得读取的字节数
		long bytesRead = 0;
		try
		{
			bytesRead = clientChannel.read(buffer);
		}
		catch(Exception ee)
		{
			key.cancel();
			clientChannel.close();
			ee.printStackTrace();
			return;
		}
 
		System.out.println(bytesRead);
		if (bytesRead < 0) {
			// 没有读取到内容的情况
			key.cancel();
			clientChannel.close();
		}else if(bytesRead > 0){
			// 将缓冲区准备为数据传出状态
			buffer.flip();
			// 将字节转化为为UTF-8的字符串
			Charset inCharset = Charset.forName("UTF-8");
			String receivedString = inCharset.decode(buffer).toString();
			// 控制台打印出来
			System.out.println("接收到来自" + clientChannel.socket().getRemoteSocketAddress() + "的信息:" + receivedString);
			// 准备发送的文本
			if(num == 0) {
				byte[] aa = hexToByteArray("23333431373031383344DF21");
				alen = aa.length;
				bedd.position(0);
		    	bedd.limit(1024);
		    	bedd.put(aa,0,alen);
		    	bedd.flip();
				clientChannel.write(bedd);
			}
			// 设置为下一次读取或是写入做准备
			key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
		}
		num++;
	}
	
	private int alen = 0;
	ByteBuffer bedd = ByteBuffer.allocate(1024);
	
	public void handleWrite(SelectionKey key) throws IOException {
		// do nothing
	}

	
	/** 
     * hex字符串转byte数组 
     * @param inHex 待转换的Hex字符串 
     * @return  转换后的byte数组结果 
     */  
    public static byte[] hexToByteArray(String inHex){  
        int hexlen = inHex.length();  
        byte[] result;  
        if (hexlen % 2 == 1){  
            //奇数  
            hexlen++;  
            result = new byte[(hexlen/2)];  
            inHex="0"+inHex;  
        }else {  
            //偶数  
            result = new byte[(hexlen/2)];  
        }  
        int j=0;  
        for (int i = 0; i < hexlen; i+=2){  
            result[j]=hexToByte(inHex.substring(i,i+2));  
            j++;  
        }  
        return result;   
    }  
    
    /** 
     * Hex字符串转byte 
     * @param inHex 待转换的Hex字符串 
     * @return  转换后的byte 
     */  
    public static byte hexToByte(String inHex){  
       return (byte)Integer.parseInt(inHex,16);  
    }
}