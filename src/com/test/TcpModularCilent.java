package com.test;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月25日 上午10:31:22
* TCP客户端
*/
public class TcpModularCilent {
	
	public static void main(String[] args) throws Exception{
		
		Socket client =new Socket("127.0.0.1",9000);
		 
	    OutputStream out = client.getOutputStream();
	 
	    out.write(hexToByteArray("23333431373031383344DF21"));
	 
	    // 读取服务端返回的数据，使用Socket读取流
	    InputStream in = client.getInputStream();
	    
	    byte[] bytes = new byte[1024];
	    
	    int len = in.read(bytes);
	    
	    byte[] arr = Arrays.copyOf(bytes, len);
	    
	    String string = new String(arr, "UTF-8");
	    
	    out.flush();
	    out.close();
	    in.close();
	 
	    System.out.println("服务端返回的数据:"+string);
	 
	    client.close();
	}
	
	/** 
     * Hex字符串转byte 
     * @param inHex 待转换的Hex字符串 
     * @return  转换后的byte 
     */  
    public static byte hexToByte(String inHex){  
       return (byte)Integer.parseInt(inHex,16);  
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
}