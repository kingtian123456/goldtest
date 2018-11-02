package com.test;

import org.eclipse.californium.core.CoapResource;
import org.eclipse.californium.core.server.resources.CoapExchange;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月16日 下午3:03:43
* 类说明
*/
public class HelloWorldResource extends CoapResource {  
    
    /** 
     * The constructor 
     */      
    public HelloWorldResource() {    
            // set resource identifier  
            super("helloWorld");  
        }  
          
    /** 
     * It is called when a GET method is received.  
     * @param exchange The user request 
     */  
    @Override  
    public void handleGET(CoapExchange exchange) {   
       exchange.respond("Hello World!!");  
  
    }  
    }  
