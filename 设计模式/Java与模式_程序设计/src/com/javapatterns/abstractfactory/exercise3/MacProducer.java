package com.javapatterns.abstractfactory.exercise3;

public class MacProducer extends ComputerProducer
{
    private static MacProducer producer = new MacProducer();

    private MacProducer() {
    }

    public Cpu createCpu()
    {
        return new MacCpu();
    }

    public Ram createRam()
    {
        return new PcRam();
    }

    public static MacProducer getInstance()
    {
		return producer;
    }

    /** @link dependency 
     * @label Creates*/
    /*# MacRam lnkMacRam; */

    /** @link dependency 
     * @label Creates*/
    /*# MacCpu lnkMacCpu; */
}
