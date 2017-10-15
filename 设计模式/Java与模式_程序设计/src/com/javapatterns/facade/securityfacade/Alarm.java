package com.javapatterns.facade.securityfacade;

public class Alarm
{
    public void activate()
    {
		System.out.println("Activating the alarm.");
    }

    public void deactivate()
    {
		System.out.println("Deactivating the alarm.");
    }

    public void ring()
    {
		System.out.println("Ring the alarm.");
    }

    public void stopRing()
    {
		System.out.println("Stop the alarm.");
    }
}

