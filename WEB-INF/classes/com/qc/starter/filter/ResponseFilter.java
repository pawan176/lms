package com.qc.starter.filter;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ResponseFilter implements Filter 
{
  private FilterConfig filterConfig = null;

  public void doFilter(ServletRequest request, ServletResponse response,
    FilterChain chain)
    throws IOException, ServletException 
  {
	  HttpServletRequest httpRequest = ((HttpServletRequest) request);
	  HttpServletResponse httpResponse = ((HttpServletResponse) response);
	  httpResponse.setHeader("X-Frame-Options", "deny");
	  httpResponse.setHeader("X-Content-Type-Options", "nosniff");
	  httpResponse.setHeader("cache-control", "no-cache,no-store,max-age=-1,must-revalidate,private,pre-check=0,post-check=0");
	  httpResponse.setHeader("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains");
	  httpResponse.setHeader("X-XSS-Protection", "1; mode=block");  
	  httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	  httpResponse.setDateHeader("Expires", 0); 	  
      chain.doFilter(request, response);
  }

  public void destroy() { }

  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }
  
}
