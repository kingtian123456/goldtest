package util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dhc on 18-6-1.
 * Description: 所有请求都走此过滤器来判断用户是否登录
 * user: Tian
 **/
public class LoginFilter implements Filter{
	
    private String redirectUrl;
    private String uncheckedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        redirectUrl = servletContext.getInitParameter("redirectPage");
        uncheckedUrls = servletContext.getInitParameter("uncheckedUrls");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        //1.获取请求URL
        String servletPath = httpRequest.getServletPath(); 

        //2.检测1中获取的servletPath是否为不需要检测的URl中的一个.若是,放行
        List<String> urls = Arrays.asList(uncheckedUrls.split(","));
        if (urls.contains(servletPath)) {
            filterChain.doFilter(httpRequest, httpResponse);
            return;
        }

        //3.从session中获取SessionKey对应值,若值不存在,则重定向到redirectUrl
        Object user = httpRequest.getSession().getAttribute("USER");
        if ((user == null)) {
           httpResponse.sendRedirect(httpRequest.getContextPath() + redirectUrl);            
           return;
        }

        //4.若存在,则放行
        filterChain.doFilter(httpRequest, httpResponse);
    }

    @Override
    public void destroy() {
    	
    }
}
