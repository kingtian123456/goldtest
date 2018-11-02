package com.test;
import org.eclipse.californium.core.CoapHandler;  
import org.eclipse.californium.core.CoapResponse;  
  
/** 
 * This is a simple Handler. It prints the server's response or if an error occurs 
 * it prints the error. 
 * @author fotiou 
 */  
public class HelloWorldHandler implements CoapHandler{  
  
    /** 
     * It is called when the CoAP server responds with some content. It prints the content of the  
     * response. 
     * @param response The response of the server 
     */  
    @Override  
    public void onLoad(CoapResponse response) {  
        String content = response.getResponseText();  
    System.out.println("Received response from Server : " + content);  
    }  
  
    /** 
     * It is called when an error occurs. 
     */  
    @Override  
    public void onError() {  
        System.out.println("Error");  
    }  
      
}  