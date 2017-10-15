package com.javapatterns.abstractfactory.exercise1;

abstract public class ComputerProducer
{
    public static ComputerProducer getProducer(String which)
    {
        if (which.equalsIgnoreCase("PC"))
        {
            return new PcProducer();
        }
        else if (which.equalsIgnoreCase("Mac"))
        {
            return new MacProducer();
        }
        else
        {
            return null;
        }
    }

    /** @link dependency 
     * @label Creates*/
    /*# MacProducer lnkMacProducer; */

    /** @link dependency 
     * @label Creates*/
    /*# PcProducer lnkPcProducer; */
}
