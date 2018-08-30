package com.qc.starter.basic;

import java.util.HashSet;
import java.util.Set;

public class test {
	public static void main(String[] args) {
		
		
		
		System.out.println("Age "+CommonUtils.getAgeInYearsAndMonthsFromDOB("months","02-Jan-1980","09-Jun-2023"));
		//System.out.println("getDateStringinddMMMYYYfromddMMyyyy "+CommonUtils.getDateStringinddMMMYYYfromddMMyyyy("03-12-2018"));
		
		//CommonUtils.removeLastOccuranceOfCharacterInString("<ADDRESS>1002001062~1000000001~~~~-1~~~~~~-1~N~~~~~~~~~~~~~9898989801~~!<ADDRESS>");
	//Set s=new HashSet();
		
		//Set s=new HashSet<E>();
		//test eCalc = new test();
        //eCalc.calcEmi(50000, 16, 12);
}

public Double calcEmi(double p, double r, double n) {
        double R = (r / 12) / 100;
        double e = (p * R * (Math.pow((1 + R), n)) / ((Math.pow((1 + R), n)) - 1));
        double totalInt = Math.round((e * n) - p);        
        double intPerMonth = Math.round(totalInt / n);
        System.out.println(" Principal per month -> " + ((Math.round((e) - intPerMonth)+Math.round(intPerMonth))));
        return e;
}

public void calcEmiAllMonths(double p, double r, double n) {

        double R = (r / 12) / 100;
        double P = p;
        double e = calcEmi(P, r, n);
        double totalInt = Math.round((e * n) - p);
        double totalAmt = Math.round((e * n));
        System.out.println("***************************");
        System.out.println(" Principal-> " + P);
        System.out.println(" Rate of Interest-> " + r);
        System.out.println(" Number of Months-> " + n);
        System.out.println(" EMI -> " + Math.round(e));
        System.out.println(" Total Interest -> " + totalInt);
        System.out.println(" Total Amount -> " + totalAmt);
        System.out.println("***************************");
        double intPerMonth = Math.round(totalInt / n);

        for (double i = 1; i <= n; i++) {
                intPerMonth = (P * R);
                P = ((P) - ((e) - (intPerMonth)));
                System.out.println(" Month -> " + (int) i);
                System.out.println(" Interest per month -> "
                                + Math.round(intPerMonth));
                System.out.println(" Principal per month -> "
                                + ((Math.round((e) - intPerMonth)+Math.round(intPerMonth))));
                System.out.println(" Balance Principal -> " + Math.round(P));
                System.out.println("***************************");
        }
}
}
