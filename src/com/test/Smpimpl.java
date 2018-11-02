package com.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月16日 上午11:23:27
* 类说明
*/
public class Smpimpl implements Runnable{

	static {
		BasicConfigurator.configure();
	}
	
	private static Log LOGGER = LogFactory.getLog(Smpimpl.class);
	
	public void run() {
		Thread tczee = Thread.currentThread();
		Smpimpl.LOGGER.info("线程"+tczee.getId()+"正在执行任务");
	}

}
