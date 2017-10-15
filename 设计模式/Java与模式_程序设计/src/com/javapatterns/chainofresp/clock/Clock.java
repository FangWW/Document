package com.javapatterns.chainofresp.clock;

import java.awt.Color;
import java.awt.Graphics;
import java.text.DateFormat;

public class Clock extends java.applet.Applet implements Runnable
{
	private Thread clockThread = null;
	
	//Applet method
	public void init()
	{
		setBackground(Color.green);
	}
	
	//Applet method
	public void start()
	{
		if (clockThread == null)
		{
			clockThread = new Thread(this, "Clock");
			clockThread.start(); //thread starts running
		}
	}

	//A Thread method.
	public void run()
	{
		Thread myThread = Thread.currentThread();
		while (clockThread == myThread)
		{
			repaint(); //causes paint() to be called
			try
			{
				Thread.sleep(1000);
			}
			catch (InterruptedException e){ }
		}
	}
	
	//An applet method.
	public void paint(Graphics g)
	{
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		DateFormat dateFormatter = DateFormat.getTimeInstance();
		g.drawString(dateFormatter.format(date), 5, 10);
	}
	// overrides Applet's stop method, not Thread's
	public void stop()
	{
	  	clockThread = null;
	}
}
