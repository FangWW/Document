package com.javapatterns.command.audioplayer;

/**
 * 	This is the Client role
 */
public class Julia
{
    /**
     * @link aggregation 
     */
    private static Keypad keypad ;

    /**
     * @link aggregation 
     */
    private static AudioPlayer myAudio = new AudioPlayer();

	public static void main(String[] args)
	{
    	test1();
	}

    private static void test1()
    {
    	Command play = new PlayCommand(myAudio);
        Command stop = new StopCommand(myAudio);
        Command rewind = new RewindCommand(myAudio);

        keypad = new Keypad(play, stop, rewind);

        keypad.play();
        keypad.stop();
        keypad.rewind();

        keypad.stop();
        keypad.play();
        keypad.stop();
    }
}               

