package com.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;

import java.net.Socket;
import java.util.Arrays;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月25日 上午10:10:01
* TCP服务端
*/
public class TcpModularLinux {
	
	public static void openserver() throws Exception {
		/**开启TCP服务端**/
		@SuppressWarnings("resource")
		ServerSocket serversocket = new ServerSocket(8668);
		
		System.err.println("开启服务端!!!");
		
		while(true) {
			Socket socket = serversocket.accept();
			/**获取输入输出流**/
			InputStream in = socket.getInputStream();
			
			byte[] bytes = new byte[1024*10];
			
			int len = in.read(bytes);
			
			byte[] arr = Arrays.copyOf(bytes,len);
			
			String Asc = new String(arr,"UTF-8");
			
			System.err.println("客户端发送过来的信息ASC:"+Asc);
			
			//System.err.println("客户端发送过来的信息ASCLL码:"+StringToAsciiString(Asc));23333431373031383344DF21
			//String[] str = {"23","33","37","31","38","30","31","39","38","68","d9","21"};
			/**当数据发送为奇数时下发获取模块时间指令**/

			OutputStream out = socket.getOutputStream();
			out.write(hexToByteArray("23333431373031383344DF21"));
			out.flush();
			out.close();
			//in.close();
		}
	}
	
	public static void main(String[] args) throws Exception {
		//openserver();
		byte[] bytes = hexToByteArray("23333431373031383344DF21");
		
		String Asc = new String(bytes,"UTF-8");
		
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
	
	
	/**
	 * ACS字符串转字符串
	 * **/
	public static String asciiToString(String value)  
	{  
	    StringBuffer sbu = new StringBuffer();  
	    String[] chars = value.split(",");  
	    for (int i = 0; i < chars.length; i++) {  
	        sbu.append((char) Integer.parseInt(chars[i]));  
	    }  
	    return sbu.toString();  
	}
	
	/** 
	 * 字节转十六进制 
	 * @param b 需要进行转换的byte字节 
	 * @return  转换后的Hex字符串 
	 */  
	public static String byteToHex(byte b){  
	    String hex = Integer.toHexString(b & 0xFF);  
	    if(hex.length() < 2){  
	        hex = "0" + hex;  
	    }  
	    return hex;  
	}  
	
	 /**
     * 数字字符串转ASCII码字符串
     *
     * @param String
     *            字符串
     * @return ASCII字符串
     */
    public static String StringToAsciiString(String content) {
        String result = "";
        int max = content.length();
        for (int i = 0; i < max; i++) {
            char c = content.charAt(i);
            String b = Integer.toHexString(c);
            result = result + b;
        }
        return result;
    }

    /**
     * 十六进制转字符串
     *
     * @param hexString
     *            十六进制字符串
     * @param encodeType
     *            编码类型4：Unicode，2：普通编码
     * @return 字符串
     */
    public static String hexStringToString(String hexString, int encodeType) {
        StringBuffer result = new StringBuffer();
        int max = hexString.length() / encodeType;
        System.err.println(max);
        for (int i = 0; i < max; i++) {
        	int hexin =  hexStringToAlgorism(hexString.substring(i * encodeType, (i + 1) * encodeType));
        	if(hexin < 128) {
        		char c = (char) hexin;
                result.append(c);
        	}else {
        		result.append(" "+hexin);
        	}
        }
        return result.toString();
    }

    /**
     * 十六进制字符串装十进制
     *
     * @param hex
     *            十六进制字符串
     * @return 十进制数值
     */
    public static int hexStringToAlgorism(String hex) {
        hex = hex.toUpperCase();
        int max = hex.length();
        int result = 0;
        for (int i = max; i > 0; i--) {
            char c = hex.charAt(i - 1);
            int algorism = 0;
            if (c >= '0' && c <= '9') {
                algorism = c - '0';
            } else {
                algorism = c - 55;
            }
            result += Math.pow(16, max - i) * algorism;
        }
        return result;
    }

    /**
     * 十六转二进制
     *
     * @param hex
     *            十六进制字符串
     * @return 二进制字符串
     */
    public static String hexStringToBinary(String hex) {
        hex = hex.toUpperCase();
        String result = "";
        int max = hex.length();
        for (int i = 0; i < max; i++) {
            char c = hex.charAt(i);
            switch (c) {
            case '0':
                result += "0000";
                break;
            case '1':
                result += "0001";
                break;
            case '2':
                result += "0010";
                break;
            case '3':
                result += "0011";
                break;
            case '4':
                result += "0100";
                break;
            case '5':
                result += "0101";
                break;
            case '6':
                result += "0110";
                break;
            case '7':
                result += "0111";
                break;
            case '8':
                result += "1000";
                break;
            case '9':
                result += "1001";
                break;
            case 'A':
                result += "1010";
                break;
            case 'B':
                result += "1011";
                break;
            case 'C':
                result += "1100";
                break;
            case 'D':
                result += "1101";
                break;
            case 'E':
                result += "1110";
                break;
            case 'F':
                result += "1111";
                break;
            }
        }
        return result;
    }

    /**
     * ASCII码字符串转数字字符串
     *
     * @param String
     *            ASCII字符串
     * @return 字符串
     */
    public static String AsciiStringToString(String content) {
        String result = "";
        int length = content.length() / 2;
        for (int i = 0; i < length; i++) {
            String c = content.substring(i * 2, i * 2 + 2);
            int a = hexStringToAlgorism(c);
            char b = (char) a;
            String d = String.valueOf(b);
            result += d;
        }
        return result;
    }

    /**
     * 将十进制转换为指定长度的十六进制字符串
     *
     * @param algorism
     *            int 十进制数字
     * @param maxLength
     *            int 转换后的十六进制字符串长度
     * @return String 转换后的十六进制字符串
     */
    public static String algorismToHEXString(int algorism, int maxLength) {
        String result = "";
        result = Integer.toHexString(algorism);

        if (result.length() % 2 == 1) {
            result = "0" + result;
        }
        return patchHexString(result.toUpperCase(), maxLength);
    }

    /**
     * 字节数组转为普通字符串（ASCII对应的字符）
     *
     * @param bytearray
     *            byte[]
     * @return String
     */
    public static String bytetoString(byte[] bytearray) {
        String result = "";
        char temp;

        int length = bytearray.length;
        for (int i = 0; i < length; i++) {
            temp = (char) bytearray[i];
            result += temp;
        }
        return result;
    }

    /**
     * 二进制字符串转十进制
     *
     * @param binary
     *            二进制字符串
     * @return 十进制数值
     */
    public static int binaryToAlgorism(String binary) {
        int max = binary.length();
        int result = 0;
        for (int i = max; i > 0; i--) {
            char c = binary.charAt(i - 1);
            int algorism = c - '0';
            result += Math.pow(2, max - i) * algorism;
        }
        return result;
    }

    /**
     * 十进制转换为十六进制字符串
     *
     * @param algorism
     *            int 十进制的数字
     * @return String 对应的十六进制字符串
     */
    public static String algorismToHEXString(int algorism) {
        String result = "";
        result = Integer.toHexString(algorism);

        if (result.length() % 2 == 1) {
            result = "0" + result;

        }
        result = result.toUpperCase();

        return result;
    }

    /**
     * HEX字符串前补0，主要用于长度位数不足。
     *
     * @param str
     *            String 需要补充长度的十六进制字符串
     * @param maxLength
     *            int 补充后十六进制字符串的长度
     * @return 补充结果
     */
    static public String patchHexString(String str, int maxLength) {
        String temp = "";
        for (int i = 0; i < maxLength - str.length(); i++) {
            temp = "0" + temp;
        }
        str = (temp + str).substring(0, maxLength);
        return str;
    }

    /**
     * 将一个字符串转换为int
     *
     * @param s
     *            String 要转换的字符串
     * @param defaultInt
     *            int 如果出现异常,默认返回的数字
     * @param radix
     *            int 要转换的字符串是什么进制的,如16 8 10.
     * @return int 转换后的数字
     */
    public static int parseToInt(String s, int defaultInt, int radix) {
        int i = 0;
        try {
            i = Integer.parseInt(s, radix);
        } catch (NumberFormatException ex) {
            i = defaultInt;
        }
        return i;
    }

    /**
     * 将一个十进制形式的数字字符串转换为int
     *
     * @param s
     *            String 要转换的字符串
     * @param defaultInt
     *            int 如果出现异常,默认返回的数字
     * @return int 转换后的数字
     */
    public static int parseToInt(String s, int defaultInt) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            i = defaultInt;
        }
        return i;
    }

    /**
     * 十六进制字符串转为Byte数组,每两个十六进制字符转为一个Byte
     *
     * @param hex
     *            十六进制字符串
     * @return byte 转换结果
     */
    public static byte[] hexStringToByte(String hex) {
        int max = hex.length() / 2;
        byte[] bytes = new byte[max];
        String binarys = hexStringToBinary(hex);
        for (int i = 0; i < max; i++) {
            bytes[i] = (byte)binaryToAlgorism(binarys.substring(i * 8 + 1, (i + 1) * 8));
            if (binarys.charAt(8 * i) == '1') {
                bytes[i] = (byte) (0 - bytes[i]);
            }
        }
        return bytes;
    }

    /**
     * 十六进制串转化为byte数组
     *
     * @return the array of byte
     */
    public static final byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();
        }
        return b;
    }

    /**
     * 字节数组转换为十六进制字符串
     *
     * @param b
     *            byte[] 需要转换的字节数组
     * @return String 十六进制字符串
     */
    public static final String byte2hex(byte b[]) {
        if (b == null) {
            throw new IllegalArgumentException("Argument b ( byte array ) is null! ");
        }
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0xff);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }
}
