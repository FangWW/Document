package com.javapatterns.command.audioplayer;

/**
 *  This class plays the role of Receiver
 */

public class AudioPlayer
{
	public void play( )
	{
		System.out.println("Playing...");
	}

	public void rewind( )
	{
		System.out.println("Rewinding...");
	}
	
	public void stop()
	{
		System.out.println("Stopped.");
	}                                                                    
}
