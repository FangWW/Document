package com.javapatterns.command.television;

public class ChannelCommand implements Command
{
	private Tv myTv;
    private int channel;
	
    public ChannelCommand(Tv tv, int channel)
    {
        myTv = tv;
        this.channel = channel;
    }

	public void execute()
    {
		myTv.changeChannel(channel);
	}
}
