package com.qc.starter.dbConnectionUtility;

import java.util.Map;

import org.apache.log4j.Logger;

public class PrintDBConnectionMap 
{
	protected static Logger log = Logger.getLogger(PrintDBConnectionMap.class);
	public static String printMap(Map map)
	{
		log.info("printMap - Start."); 
		log.debug("printMap - Parameter : \r\n 1. map = ");
		String allPropertyNameAndvalue="\r\n";
		allPropertyNameAndvalue=allPropertyNameAndvalue+"DB_SERVER= "+map.get("DB_SERVER").toString()+"\r\n";
		allPropertyNameAndvalue=allPropertyNameAndvalue+"APP_SERVER= "+map.get("APP_SERVER").toString()+"\r\n";
		allPropertyNameAndvalue=allPropertyNameAndvalue+"DB_CONNECTIONTYPE= "+map.get("DB_CONNECTIONTYPE").toString()+"\r\n";
		allPropertyNameAndvalue=allPropertyNameAndvalue+"DB_DATASOURCENAME= "+map.get("DB_DATASOURCENAME").toString()+"\r\n";
		allPropertyNameAndvalue=allPropertyNameAndvalue+"DB_DRIVERCLASS= "+map.get("DB_DRIVERCLASS").toString()+"\r\n";
		allPropertyNameAndvalue=allPropertyNameAndvalue+"DB_SERVERPORT= "+map.get("DB_SERVERPORT").toString()+"\r\n";
		allPropertyNameAndvalue=allPropertyNameAndvalue+"DB_SID= "+map.get("DB_SID").toString()+"\r\n";
		allPropertyNameAndvalue=allPropertyNameAndvalue+"DB_DRIVER= "+map.get("DB_DRIVER").toString()+"\r\n";
		allPropertyNameAndvalue=allPropertyNameAndvalue+"DB_USERNAME= "+map.get("DB_USERNAME").toString()+"\r\n";
		allPropertyNameAndvalue=allPropertyNameAndvalue+"DB_PASSWORD= "+map.get("DB_PASSWORD").toString()+"";
		log.info("printMap - End."); 
		return allPropertyNameAndvalue;
	}
}
