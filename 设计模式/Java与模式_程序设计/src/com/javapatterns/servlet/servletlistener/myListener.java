package com.javapatterns.servlet.servletlistener;

import javax.servlet.*;

public final class myListener implements ServletContextListener 
{
    /** This method is called when the servlet context is
     * initialized(when the Web Application is deployed). 
     * You can initialize servlet context related data here.
     */ 
    public void contextInitialized(ServletContextEvent event) 
    {
        //write your code here
    }
    
    /** This method is invoked when the Servlet Context 
     * (the Web Application) is undeployed or 
     * WebLogic Server shuts down.
     */ 
    public void contextDestroyed(ServletContextEvent event) 
    {
        //write your code here
    }
}
