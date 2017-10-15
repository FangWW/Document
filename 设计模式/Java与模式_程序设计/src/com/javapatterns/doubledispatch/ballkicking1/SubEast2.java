package com.javapatterns.doubledispatch.ballkicking1;

public class SubEast2 extends East 
{
	public void goEast(West west)
    {
		west.goWest2(this);
    }

    public String myName2()
    {
        return "SubEast2";
    }
}
