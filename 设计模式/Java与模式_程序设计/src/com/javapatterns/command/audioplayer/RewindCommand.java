package com.javapatterns.command.audioplayer;

/**
 *  This class plays the role of Concrete Command
 */

public class RewindCommand implements Command
{
	private AudioPlayer myAudio;

	public RewindCommand ( AudioPlayer a)
    {
	    myAudio = a;
	}
	public void execute()
    {
	    myAudio.rewind();
	}
}