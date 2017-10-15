package com.javapatterns.liskov.version3;

public class Rectangle implements Quadrangle 
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
