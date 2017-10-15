package com.javapatterns.doubledispatch.ballkicking;

public class West
{
	public void goWest(East east)
    {
		east.goEast(this);
    }
}
