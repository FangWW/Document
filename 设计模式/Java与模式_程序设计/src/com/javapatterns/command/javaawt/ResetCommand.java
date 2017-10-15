package com.javapatterns.command.javaawt;

public class ResetCommand extends Button implements CommandFromGod
{
	Panel p;
	public ResetCommand(String caption, Panel pnl)
	{
		super(caption);
		p = pnl;
	}
	public void Execute()
	{
		p.setBackground(Color.black);
	}
}

