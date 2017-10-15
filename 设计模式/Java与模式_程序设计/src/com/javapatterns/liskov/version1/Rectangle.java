package com.javapatterns.liskov.version1;

public class Rectangle
{
    private long width;
    private long height;
    
    public void setWidth(long width)
    {
        this.width = width;
    }
    public long getWidth()
    {
        return this.width;
    }
    public void setHeight(long height)
    {
        this.height = height;
    }
    public long getHeight()
    {
        return this.height;
    }
}
