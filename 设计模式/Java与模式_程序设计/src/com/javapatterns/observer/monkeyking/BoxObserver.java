package com.javapatterns.observer.monkeyking;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;

public class BoxObserver extends Frame
{
	Observable notifier = new BoxObservable();

	public BoxObserver(int grid)
    {
	  setTitle("Demonstrates Observer pattern");
	  setLayout(new GridLayout(grid, grid));
	  for(int x = 0; x < grid; x++)
	    for(int y = 0; y < grid; y++)
	      add(new OCBox(x, y, notifier));
	}   
	public static void main(String[] args)
    {
	  int grid = 8;
	  if(args.length > 0)
	    grid = Integer.parseInt(args[0]);
	  Frame f = new BoxObserver(grid);
	  f.setSize(500, 400);
	  f.setVisible(true);
	  f.addWindowListener(
	    new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        System.exit(0);
	      }
	    });
	}
}


