package com.qc.starter.basic;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class CommonUtils {
	
	public static String toString(String string) {
        return (string == null ? "" : string);
    }
	
	
	public static String toStringDatabaseNull(String string) {
        return (string == null ? "0" : string);
    }
	
	
	public static String toString(Object object) {
        return (object == null ? "" : object.toString());
    }
	
	public static String decimarlFormaterUpto2Places(Object obj){
		double number = 0;		
		if(obj instanceof String){
			number = Double.parseDouble(obj.toString());
		}
		return new DecimalFormat("#.##").format(number);
	}
	
	public static String getCurrentDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
	
	
	public static java.sql.Date getSqlDateFromString(String startDate){
		java.sql.Date sqlStartDate = null;
		try{
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
			java.util.Date date = sdf1.parse(startDate);
			 sqlStartDate = new java.sql.Date(date.getTime());  
		}catch(Exception e){
			e.printStackTrace();
		}
		return sqlStartDate;
	}
	 
			
	public static String getFormatedStringDateFromEntity(String inputString){
		String strDate=getCurrentDate();
		try{			
			if(!CommonUtils.toString(inputString).equals("")){				
				inputString = inputString.substring(0,10);				
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy						
				Date inputDate = dateFormat.parse(inputString);						
				SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MMM-yyyy HH:mm");//dd/MM/yyyy
				strDate = sdfDate.format(inputDate);	
			}
		}catch(ParseException p){
			p.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}						
		return strDate;
	}
	
	
	public static String getCurrentDateInDDMMMYYY(){
		Date date = new Date(); // from Calendar or wherever
		String str = new SimpleDateFormat("dd-MMM-yyyy").format(date);
		return str;
	}
	
/*<<<<<<< HEAD*/
	public static String removeLastOccuranceOfCharacterInString(String str){
		int ind = str.lastIndexOf("!<ADDRESS>");
		if( ind>=0 )
		    str = new StringBuilder(str).replace(ind, ind+1,"<ADDRESS>").toString();
		System.out.println(str);
		
		return str;
	}
	
	public static int getAgeFromDob(String str){
		try{
			String stryear = str !=null ? str.split("-")[2] : "0";
			int birthYear = Integer.parseInt(stryear); 
			int currentyear = Calendar.getInstance().get(Calendar.YEAR);
			if(currentyear>birthYear)
				return (currentyear - birthYear);
			else
				return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			
		}		
	}
	
	
	
	
	public static int getAgeFromDob(String str,String businessDate){
		try{
			String stryear = str !=null ? str.split("-")[2] : "0";
			String strbusYear = businessDate !=null ? businessDate.split("-")[2] : "0";			
			int birthYear = Integer.parseInt(stryear); 
			int busYear = Integer.parseInt(strbusYear);			
			//int currentyear = Calendar.getInstance().get(Calendar.YEAR);
			if(busYear>birthYear)
				return (busYear - birthYear);
			else
				return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			
		}		
	}
	
	
	
	
	public static int getTotalNumberOfYearsFromDateYear(String strbusYear){
		try{			
			int endyear = Calendar.getInstance().get(Calendar.YEAR);						
			int startingYear = Integer.parseInt(strbusYear); 						
			//int currentyear = Calendar.getInstance().get(Calendar.YEAR);
			if(endyear>startingYear)
				return (endyear - startingYear);
			else
				return 0;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			
		}		
	}
	

	/*public static String toStringDatabaseNull(String string) {
        return (string == null ? "0" : string);
    }*/
/*>>>>>>> WEBCHANGES
	
	public static String removeLastOccuranceOfCharacterInString(String str){
		int ind = str.lastIndexOf("!<ADDRESS>");
		if( ind>=0 )
		    str = new StringBuilder(str).replace(ind, ind+1,"<ADDRESS>").toString();
		System.out.println(str);
		
		return str;
	}	*/

	
	
	public static int getAgeInYearsAndMonthsFromDOB(String flag,String dob,String businessDate){
		int daysInMon[] = {31, 28, 31, 30, 31, 30,31, 31, 30, 31, 30, 31};  //Days in month
        int days = 0, month = 0, year = 0;
        //char[] dob = new char[110];
        
        if(!dob.equals("")){
        	String age [] = dob.split("-");
        	
        	days=Integer.parseInt(age[0]);
            month=getIntMonthfromCharMonth(age[1]);           
            year=Integer.parseInt(age[2]) ;
                        
        }

		try{
	        System.out.println("Date of Birth: "+days+ "/" +month+ "/" +year);
	        
	        
	        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	        Date d = null;
	        try {
	            d = dateFormat.parse(businessDate);	          
	        } 
	        catch (ParseException e) {
	            e.printStackTrace();
	        }
	        
	        
	         //Date d = new Date();
	        System.out.println("Current Date: " +d.getDate()+ "/" +(d.getMonth()+1)+ "/" +(d.getYear()+1900));
	                   
	        days = daysInMon[month - 1] - days + 1;
	            
	        /*Calculating the num of year, month and date*/
	        days = days + d.getDate();
	        month = (12 - month) + d.getMonth();
	        year = (d.getYear() + 1900) - year - 1;
	                
	        if (month >= 12)
	        {
		        year = year + 1;
		        month = month - 12;
	        }
	        
	        if (days >= 31 )
	        {
	        	month = month + 1;
	        	days = days - 31;
	        	if(month == 12){
	        		year = year + 1;
	        		month = month - 12;
	        	}
	        }
	        
	        /*Result portion*/
	        
	        System.out.println("Age: " +year+ " years" +month+ " months" +days+ " days");
	        
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag.equals("year") ? year : month;

	}
	
	
	enum monthList {Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec} ;
	
	
	public static int getIntMonthfromCharMonth(String month){
		int intMonth=1;
		
		switch (monthList.valueOf(month)){
			case Jan :
				intMonth = 1;
				break; 
			case Feb :
				intMonth = 2;
				break;
			case Mar :
				intMonth = 3;
				break; 
			case Apr :
				intMonth = 4;
				break;
			case May :
				intMonth = 5;
				break; 
			case Jun :
				intMonth = 6;
				break;
			case Jul :
				intMonth = 7;
				break; 
			case Aug :
				intMonth = 8;
				break;
			case Sep :
				intMonth = 9;
				break; 
			case Oct :
				intMonth = 10;
				break;
			case Nov :
				intMonth = 11;
				break; 
			case Dec :
				intMonth = 12;
				break;
		
		}
		
		return intMonth;
		
	}
	
	
	
	
	public static String getDateStringinddMMMYYYfromddMMyyyy(String olddate){
		String finalDate = "";
		try{
			
			SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		    SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
		    Date date = format1.parse(olddate);
		    finalDate = format2.format(date);
		    System.out.println(format2.format(date));
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return finalDate;
	}
	
	
	
	
	
}
