package com.javapatterns.doubledispatch;

import java.awt.Canvas;

public class Point
{
	private int x, y;
	
    public Point() {
    }

	public void draw(Canvas c)
	{
		//write you code here
	}
	
	public void translate(int d)
	{
		x += d;
		y += d;
	}
	public void translate(int dx, int dy)
	{
		x += dx;
		y += dy;
	}
}

