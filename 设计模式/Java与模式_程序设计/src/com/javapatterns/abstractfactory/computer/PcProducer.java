package com.javapatterns.abstractfactory.computer;

public class PcProducer implements ComputerProducer
{
    public Cpu createCpu()
    {
        return new PcCpu();
    }

    public Ram createRam()
    {
        return new PcRam();
    }

    /** @link dependency 
     * @label Creates*/
    /*# PcCpu lnkPcCpu; */

    /** @link dependency 
     * @label Creates*/
    /*# PcRam lnkPcRam; */
}
