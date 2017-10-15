package com.javapatterns.servlet.sessionlistener;

import javax.servlet.*;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public final class myListener implements HttpSessionAttributeListener
{

    /** This method is called when an attribute 
     *¡¡is added to a session.
     */
    public void attributeAdded(HttpSessionBindingEvent sbe) 
    {
    }

    /** This method is called when an attribute
     * is removed from a session.
     */
    public void attributeRemoved(HttpSessionBindingEvent sbe) 
    {
    }

    /** This method is invoked when an attibute
     * is replaced in a session.
     */
    public void attributeReplaced(HttpSessionBindingEvent sbe) 
    {
    }
}

