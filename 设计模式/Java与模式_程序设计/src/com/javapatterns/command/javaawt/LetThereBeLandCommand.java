package com.javapatterns.command.javaawt;

public class LetThereBeLandCommand extends Button implements CommandFromGod
{
	Panel p;
	public LetThereBeLandCommand(String caption, Panel pnl)
	{
		super(caption);
		p = pnl;
	}
	public void Execute()
	{
		p.setBackground(Color.orange);
	}
}

