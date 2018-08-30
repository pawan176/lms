package com.qc.starter.mobileAction;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnectionUtil {
	public static Connection getConnection(){
		Connection connection = null;
		try {
			if(connection==null){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@10.1.1.137:1521:MMMQA","QC_PROSPECT","mmm126qa");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
}
