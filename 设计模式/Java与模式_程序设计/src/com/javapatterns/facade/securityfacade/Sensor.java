package com.javapatterns.facade.securityfacade;

public class Sensor
{
    public void activate()
    {
		System.out.println("Activating on the sensor.");
    }

    public void deactivate()
    {
		System.out.println("Deactivating on the sensor.");
    }

    public void trigger()
    {
		System.out.println("The sensor has been triggered.");
    }
}

