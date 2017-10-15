package com.javapatterns.abstractfactory.exercise3;

public class PcProducer extends ComputerProducer
{
    private static PcProducer producer = new PcProducer();

    private PcProducer()
    {
    }

    public Cpu createCpu()
    {
        return new PcCpu();
    }

    public Ram createRam()
    {
        return new PcRam();
    }

    public static PcProducer getInstance()
    {
		return producer;
    }

    /** @link dependency
     * @label Creates*/
    /*# PcCpu lnkPcCpu; */

    /** @link dependency 
     * @label Creates*/
    /*# PcRam lnkPcRam; */
}
