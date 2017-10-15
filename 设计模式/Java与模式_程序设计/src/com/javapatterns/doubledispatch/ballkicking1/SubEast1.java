package com.javapatterns.doubledispatch.ballkicking1;

public class SubEast1 extends East 
{
	public void goEast(West west)
    {
		west.goWest1(this);
    }

    public String myName1()
    {
        return "SubEast1";
    }
}
