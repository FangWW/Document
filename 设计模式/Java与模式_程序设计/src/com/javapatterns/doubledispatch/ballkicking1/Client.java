package com.javapatterns.doubledispatch.ballkicking1;

public class Client
{
    private static East east;
    private static West west;

	public static void main(String[] args)
    {
        // combination 1
		east = new SubEast1();
        west = new SubWest1();

        east.goEast(west);

        // combination 2
		east = new SubEast1();
        west = new SubWest2();

        east.goEast(west);

    }
}
