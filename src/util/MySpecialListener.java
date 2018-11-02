package util;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年5月21日 上午10:50:31
* 类说明
*/
public class MySpecialListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
