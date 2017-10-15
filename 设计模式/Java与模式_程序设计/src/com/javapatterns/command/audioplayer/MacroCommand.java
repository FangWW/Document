package com.javapatterns.command.audioplayer;

public interface MacroCommand extends Command
{
	void execute();
	
	void remove(Command toRemove);
	
	void add(Command toAdd);
}

