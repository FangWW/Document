package com.javapatterns.command.audioplayer;

/**
 * 	This is the Invoker role
 */
public class Keypad
{
    /**
     * @link aggregation 
     */
	private Command playCmd;

    /**
     * @link aggregation 
     */
    private Command rewindCmd;

    /**
     * @link aggregation 
     */
    private Command stopCmd;
	
	public Keypad(Command play, Command stop, Command rewind)
	{
        // concrete Command registers itself with the invoker
		playCmd = play;
		stopCmd = stop;
        rewindCmd = rewind;
	}
	public void play()
	{
		playCmd.execute();
	}
	public void stop()
	{
		stopCmd.execute();
	}
    public void rewind()
    {
        rewindCmd.execute();
    }
}

