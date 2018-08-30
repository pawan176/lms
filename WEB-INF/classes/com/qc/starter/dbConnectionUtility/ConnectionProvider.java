package com.qc.starter.dbConnectionUtility;

import java.sql.Connection;
import java.util.Map;

import qc.dal.util.DBConnection;



public class ConnectionProvider {


	public static Connection getConnection(Map dbConnectionMap)
	{
		Connection connection=null;
		try 
		{
			connection= DBConnection.getConnection(dbConnectionMap);
			connection.setAutoCommit(false);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return connection;
	}

}
