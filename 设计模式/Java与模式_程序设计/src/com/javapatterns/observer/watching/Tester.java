package com.javapatterns.observer.watching;

import java.util.Observer;

public class Tester
{
	static private Watched watched;
	static private Observer watcher;

    public static void main(String[] args)
    {
		watched = new Watched();

        watcher = new Watcher(watched);

        watched.changeData("In C, we create bugs.");
        watched.changeData("In Java, we inherit bugs.");
        watched.changeData("In Java, we inherit bugs.");
        watched.changeData("In Visual Basic, we visualize bugs.");
    }
}
