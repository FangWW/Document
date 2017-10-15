package com.javapatterns.multilingual.dice;

import java.util.Date;
import java.util.Random;

public class Die
{
    private static Die die1 = new Die();
    private static Die die2 = new Die();

    private Die()
    {
    }

    public static Die getInstance(int whichOne)
    {
        if (whichOne == 1)
        {
            return die1;
        }
        else
        {
            return die2;
        }
    }

    public synchronized int dice()
    {
		Date d = new Date();

		Random r = new Random( d.getTime() );
		int value = r.nextInt();
		value = Math.abs(value);

		value = value % 6;
		value += 1;
		System.out.println(value);
        return value;
    }

}
