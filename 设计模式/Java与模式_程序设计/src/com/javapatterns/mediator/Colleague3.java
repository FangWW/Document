package com.javapatterns.mediator;

public class Colleague3 extends Colleague
{
	public Colleague3(Mediator m)
	{
		super( m );
	}

    public void action()
    {
        System.out.println("This is an action from Colleague 3");
    }
}

