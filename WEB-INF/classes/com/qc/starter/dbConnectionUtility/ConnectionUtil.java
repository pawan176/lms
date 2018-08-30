package com.qc.starter.dbConnectionUtility;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;


public class ConnectionUtil{
	public static Map dbConnectionMapAuth;
	public static Map dbConnectionMapProspect;
	public static Map dbConnectionMapMASTER;
	protected static Logger log = Logger.getLogger(ConnectionUtil.class);
	static Locale locale = new Locale("en","US");
	/**
	 * Populate Db Connection From property files
	 */
	static 
	{
		log.info("static - Start."); 
		dbConnectionMapAuth 		= getDBConnectionMap("com/qc/starter/dbConnectionConfigration/DBConnectionMapAuth", locale);
		dbConnectionMapProspect		= getDBConnectionMap("com/qc/starter/dbConnectionConfigration/DBConnectionMapProspect", locale);
		dbConnectionMapMASTER 		= getDBConnectionMap("com/qc/starter/dbConnectionConfigration/DBConnectionMapMaster", locale);
		log.info("static - End."); 
	}

	public static Map getDBConnectionMap(String baseName, Locale locale)
	{
		log.info("getDBConnectionMap - Start.");
		Map propertyMap = new HashMap();
		try
		{
			ResourceBundle property = ResourceBundle.getBundle(baseName, locale);
			propertyMap.put("DB_SERVERIP", property.getString("DB_SERVERIP"));
			propertyMap.put("DB_SERVERPORT", property.getString("DB_SERVERPORT"));
			propertyMap.put("DB_SID", property.getString("DB_SID"));
			propertyMap.put("DB_DRIVER", property.getString("DB_DRIVER"));
			propertyMap.put("DB_USERNAME", property.getString("DB_USERNAME"));
			propertyMap.put("DB_PASSWORD", property.getString("DB_PASSWORD"));
			propertyMap.put("DB_DATASOURCENAME", property.getString("DB_DATASOURCENAME"));
			propertyMap.put("DB_SERVER", property.getString("DB_SERVER"));
			propertyMap.put("APP_SERVER", property.getString("APP_SERVER"));
			propertyMap.put("DB_CONNECTIONTYPE", property.getString("DB_CONNECTIONTYPE"));
			propertyMap.put("DB_DRIVERCLASS", property.getString("DB_DRIVERCLASS"));
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
		log.info("getDBConnectionMap - End."); 
		return propertyMap;
	}

}
