package com.javapatterns.visitor.parts;

public class Pc extends Composite
{
    public Pc()
    {
        super.add(new IntegratedBoard());
        super.add(new HardDisk());
        super.add(new Case());

    }
} 

