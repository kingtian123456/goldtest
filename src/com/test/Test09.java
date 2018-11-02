package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.UUID;

import com.mysql.jdbc.PreparedStatement;

/**
* @author 作者 E-mail:Tian
* @version 创建时间：2018年7月9日 上午11:14:36
* 类说明
*/
public class Test09 {
	
	public static void putLabsInfo(int num) {
		Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection("jdbc:mysql://103.45.106.53:3306/faish?rewriteBatchedStatements=true","root","zchs123456789");
            // 开始时间
            Long begin = new Date().getTime();
            // sql前缀
            String prefix = "INSERT INTO labs (labs_number,labs_user,labs_validation,labs_send) VALUES ";
         	// 保存sql后缀
            StringBuffer suffix = new StringBuffer();
         	// 设置事务为非自动提交
         	conn.setAutoCommit(false);
         	// 比起st，pst会更好些
         	PreparedStatement  pst = (PreparedStatement) conn.prepareStatement("");//准备执行语句
         	   
         	// 外层循环，总提交事务次数
         	for (int i = 1; i <= 10; i++) {
         		suffix = new StringBuffer();
         		// 第j次提交步长
         		for (int j = 1; j <= num/10; j++) {
         			// 构建SQL后缀
     	     		String maxbu =  UUID.randomUUID().toString();//利用UUID生成唯一数的方式
     	     		// 构建SQL后缀
     	     		suffix.append("('" +maxbu+"',null,'0','0'),");
         		}
         		// 构建完整SQL
         		String sql = prefix + suffix.substring(0, suffix.length() - 1);
         		// 添加执行SQL
         		pst.addBatch(sql);
         		// 执行操作
         		pst.executeBatch();
         		// 提交事务
         		conn.commit();
         		// 清空上一次添加的数据
         		suffix = new StringBuffer();
         	}
         	   // 头等连接
         	   pst.close();
         	   conn.close();
            // 结束时间
            Long end = new Date().getTime();
            // 耗时
            System.out.println(num+"条数据插入花费时间 : " + (end - begin) / 1000 + " s"+"  插入完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		putLabsInfo(100000);
	}
}
