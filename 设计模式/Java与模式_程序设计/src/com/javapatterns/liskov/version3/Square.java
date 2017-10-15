package com.javapatterns.liskov.version3;

public class Square implements Quadrangle 
{
	private long side;

    public void setSide(long side)
    {
		this.side = side;
    }

    public long getSide()
    {
        return side;
    }

    public long getWidth()
    {
        return getSide();
    }

    public long getHeight()
    {
        return getSide();
    }
}