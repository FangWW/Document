package com.javapatterns.multilingual.dice;

public class Client
{
    private static Die die1;private static Die die2;

    public static void main(String[] args)
    {
		die1 = Die.getInstance(1);
        die2 = Die.getInstance(2);

        die1.dice();
		die2.dice();
    }
}
