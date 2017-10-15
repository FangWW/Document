package com.javapatterns.facade;

public class Facade
{ 
	
	public Facade()
	{
	}
	
	public void ProcessTitle()
	{ 
	
		StringOutput str = new StringOutput();
		LineOutput line = new LineOutput();
		SignalOutput sig = new SignalOutput();
		
		line.doubleLine();
		sig.sigRectangle();
		str.StringOut("something here"); 
	
	}
	
	public void ProcessSubTitle()
	{ 
	
		StringOutput str = new StringOutput();
		LineOutput line = new LineOutput();
		SignalOutput sig = new SignalOutput();
		
		line.singleLine();
		sig.sigCircle();
		str.StringOut("sub title 1.1");
	
	}
	
	public void ProcessItem()
	{ 
	
		StringOutput str = new StringOutput();
		LineOutput line = new LineOutput();
		SignalOutput sig = new SignalOutput();
		
		sig.sigStar();
		str.StringOut("process item"); 
	
	} 

}


