package com.javapatterns.facade.security;

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
		System.out.println("Ringing the alarm.");
    }

    public void stopRing()
    {
		System.out.println("Ringing stopped.");
    }
}

