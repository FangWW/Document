package com.javapatterns.prototype.monkeyking;

public class GoldRingedStaff implements Cloneable
{
    public GoldRingedStaff()
    {
        //write your code here
    }

    public void grow()
    {
        this.diameter *= 2.0;
        this.height *= 2;
    }

    public void shrink()
    {
        this.diameter /= 2;
        this.height /= 2;
    }

    public void move()
    {
        //write your code for moving the staff
    }

    public float getHeight()
    {
        return height;
    }

    public void setHeight(float height)
    {
        this.height = height;
    }

    public float getDiameter()
    {
        return diameter;
    }

    public void setDiameter(float diameter)
    {
        this.diameter = diameter;
    }

    private float height = 100.0F;
    private float diameter = 10.0F;
}
