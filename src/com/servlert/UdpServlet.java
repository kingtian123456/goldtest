package com.servlert;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.services.DatasService;
import com.services.DevicesService;


@Controller
public class UdpServlet extends HttpServlet {
	
	@Autowired
	DevicesService device;
	
	@Autowired
	DatasService datas;
	
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		UDPPool.colse();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
		//ToolUtil.MessageIssued();
		System.err.println("进入了udp服务端");
		try {
			new Thread() {
				public void run() {
					try {
						UDPPool.openServer(device,datas);
					} catch (Exception e) {
						System.err.println("扑捉异常");
						//e.printStackTrace();
					}
				}
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
