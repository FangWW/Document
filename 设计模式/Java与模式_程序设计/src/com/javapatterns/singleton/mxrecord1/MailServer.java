package com.javapatterns.singleton.mxrecord1;

public class MailServer
{
	private int priority;
	private String server;

	public void setPriority(int priority) 
	{
		this.priority = priority;
	}
	public void setServer(String server) 
	{
		this.server = server;
	}
	public int getPriority() 
	{
		return priority;
	}
	public String getServer() 
	{
		return server;
	}

}
