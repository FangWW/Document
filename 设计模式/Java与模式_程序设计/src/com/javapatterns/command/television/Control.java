package com.javapatterns.command.television;

public class Control
{    
    /**
     * @directed 
     */
    private Command onCommand, offCommand, changeChannel;

	public Control(Command on, Command off, Command channel)
    {
		onCommand = on;
		offCommand = off;
        changeChannel = channel;
	}

	public void turnOn()
    {
		onCommand.execute() ;
	}

	public void turnOff()
    {
		offCommand.execute();
	}

    public void changeChannel()
    {
    	changeChannel.execute();
    }
}

