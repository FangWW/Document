package com.javapatterns.command.television;

public class Tv
{
	public void turnOn()
	{
	    System.out.println("The televisino is on.");
	}
	
	public void turnOff()
	{
	    System.out.println("The television is off.");
	}
	
	public void changeChannel(int channel)
	{
        currentChannel = channel;
		System.out.println("Now TV channel is " + channel);
	}

    private int currentChannel = 0;
}

