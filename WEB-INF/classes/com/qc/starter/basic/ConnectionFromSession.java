package com.qc.starter.basic;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;

public class ConnectionFromSession {
	private static final Logger logger = Logger.getLogger(ConnectionFromSession.class.getName());
	
	public static Connection getJavaSqlConnectionFromHibernateSession(Session session){
		logger.info("ConnectionFromSession | getJavaSqlConnectionFromHibernateSession() | :-Start ");
	    SessionFactoryImplementor sessionFactoryImplementor = null;
	    ConnectionProvider connectionProvider = null;
	    Connection connection = null ;
	    try {
	        sessionFactoryImplementor = (SessionFactoryImplementor)session.getSessionFactory();
	        connectionProvider = sessionFactoryImplementor.getConnectionProvider();
	        connection = connectionProvider.getConnection();
	       // System.out.println("connection conn:::"+connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        logger.error("ConnectionFromSession | getJavaSqlConnectionFromHibernateSession() |"+ e.getMessage()+" :-Error ");
	    }
	    logger.info("ConnectionFromSession | getJavaSqlConnectionFromHibernateSession() | :-END ");
	    return connection;
	}
}

