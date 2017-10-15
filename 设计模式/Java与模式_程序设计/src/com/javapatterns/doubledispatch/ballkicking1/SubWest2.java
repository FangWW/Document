package com.javapatterns.doubledispatch.ballkicking1;

public class SubWest2 extends West 
{
	public void goWest1(SubEast1 east)
    {
		System.out.println( "SubWest2 + " + east.myName1() );
    }

	public void goWest2(SubEast2 east)
    {
		System.out.println( "SubWest2 + " + east.myName2() );
    }

}
