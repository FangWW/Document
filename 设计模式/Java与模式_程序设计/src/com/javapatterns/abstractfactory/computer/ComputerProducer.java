package com.javapatterns.abstractfactory.computer;

public interface ComputerProducer
{
    Cpu createCpu();

    Ram createRam();
}
