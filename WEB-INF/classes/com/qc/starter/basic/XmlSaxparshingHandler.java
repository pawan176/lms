package com.qc.starter.basic;

import java.util.LinkedHashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class XmlSaxparshingHandler  extends DefaultHandler{
	
	 private Map<String,String> menuList = null;
	 
	 private String displayMenu="";
	    public Map<String,String> getMenuList() {
	        return menuList;
	    }
	 
	 
	    boolean displayName = false;
	    boolean menuType = false;
	    boolean flag = false;
	    boolean actionFlage = false;
	    String menuTypeName="";
	    String actionId="";
	    String temp=null;
	 
	    @Override
	    public void startElement(String uri, String localName, String qName, Attributes attributes)
	            throws SAXException {
	        if (menuList == null){
	        	menuList = new LinkedHashMap<String,String>();
	    
	        }else if (qName.equalsIgnoreCase("DISPLAY_NAME")) {
	        	displayName = true;
	        }else if (qName.equalsIgnoreCase("MENU_TYPE")) {
	        	menuType = true;
	        }else if(qName.equalsIgnoreCase("ACTION_ID")){
	        	actionFlage=true;
	        }
	    }
	 
	 
	    @Override
	    public void endElement(String uri, String localName, String qName) throws SAXException {
	        if (qName.equalsIgnoreCase("Employee")) {
	           // menuList.add(menu);
	        }
	    }
	 
	 
	    @Override
	    public void characters(char ch[], int start, int length) throws SAXException {
	         if(actionFlage){
	        	 actionId=new String(ch, start, length);	
	        	 actionFlage=false;
	         }
	        if (displayName) {
	        	displayMenu=new String(ch, start, length);
	        	displayName = false;
	        	flag=true;
	        } 
            if(flag && menuType){
            	menuTypeName=new String(ch, start, length);
            	if(menuTypeName.equalsIgnoreCase("LEAD_MANAGEMENT")){
            	menuList.put(actionId, displayMenu);
	        	displayName = false;
	        	menuType = false;
	        	flag=false;
            	}
            }
	    }
	}