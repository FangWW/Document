package com.javapatterns.command.television;

public class OffCommand implements Command
{
	private Tv myTv;
	
	public OffCommand (Tv tv)
    {
		myTv  =  tv;
	}
	public void execute()
    {
		myTv.turnOff();
	}
}
