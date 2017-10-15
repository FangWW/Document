package com.javapatterns.command.audioplayer2;

/**
 *  This class plays the role of Concrete Command
 */

public class StopCommand implements Command
{
    /**
     * @directed 
     */
    private AudioPlayer myAudio;
	
	public StopCommand ( AudioPlayer a)
    {
		myAudio = a;
	}
	public void execute( )
    {
		myAudio.stop();
	}
}
