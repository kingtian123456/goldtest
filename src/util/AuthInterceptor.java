package util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年6月5日 上午10:41:04
* 拦截器类
*/
public class AuthInterceptor extends  HandlerInterceptorAdapter {

	//拦截器
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { 
		
		if(handler instanceof HandlerMethod)
		{
			HandlerMethod hm = (HandlerMethod) handler;
			
			if(hm.getMethodAnnotation(AuthPassport.class) != null && 
					hm.getMethodAnnotation(AuthPassport.class).chek() == true) 
			{
				Object user = request.getSession().getAttribute("USER");
            	
            	if(user == null || user.equals("")) {
            		
            		response.sendRedirect("/Login.html");
            		
            		return false;
            	}
			}
		}
		return super.preHandle(request, response, handler);
	}
}
