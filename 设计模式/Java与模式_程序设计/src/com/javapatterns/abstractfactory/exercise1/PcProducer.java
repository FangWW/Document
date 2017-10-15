package com.javapatterns.abstractfactory.exercise1;

public class PcProducer extends ComputerProducer
{
    public Cpu createCpu()
    {
        return new PcCpu();
    }

    public Ram createRam()
    {
        return new PcRam();
    }

    /**
     * @directed 
     */
    private PcCpu lnkPCCPU;

    /**
     * @directed 
     */
    private PcRam lnkPCRAM;

    /**
     * @directed
     * @label Creates 
     */
    private PcRam lnkPCRAM1;

    /**
     * @label Creates
     * @directed 
     */
    private PcCpu lnkPCCPU1;
}
