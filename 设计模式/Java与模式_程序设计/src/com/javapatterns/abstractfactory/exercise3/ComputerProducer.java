package com.javapatterns.abstractfactory.exercise3;

public abstract class ComputerProducer
{
    public abstract Cpu createCpu();

    public abstract Ram createRam();

    public static ComputerProducer getInstance(String which)
    {
		if (which.equalsIgnoreCase("Pc"))
        {
            return PcProducer.getInstance();
        }
        else if (which.equalsIgnoreCase("Mac"))
        {
            return MacProducer.getInstance();
        }
        return null;
    }
}
