package com.qc.starter.servlet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.qc.starter.controllar.FlushLeadLockController;

public class ValidateSession implements HttpSessionListener{
	
	public void sessionCreated(HttpSessionEvent createEvent) {
		//HttpSession session=createEvent.getSession();
	}
	public void sessionDestroyed(HttpSessionEvent deleteEvent) {
		HttpSession session=deleteEvent.getSession();
		FlushLeadLockController flushLeadLockController=new FlushLeadLockController();
		flushLeadLockController.flushLeadLock(session);
	}

}
