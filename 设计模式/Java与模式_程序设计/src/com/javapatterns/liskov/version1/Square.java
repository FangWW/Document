package com.javapatterns.liskov.version1;

public class Square
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
}