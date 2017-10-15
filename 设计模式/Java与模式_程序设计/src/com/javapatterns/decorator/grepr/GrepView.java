package com.javapatterns.decorator.grepr;

import java.io.PrintStream;

public class GrepView
{
    PrintStream out ;
 	public GrepView()
 	{
		out = System.out;
	}

	public void println(String line)
    {
     	out.println(line);
    }
}
