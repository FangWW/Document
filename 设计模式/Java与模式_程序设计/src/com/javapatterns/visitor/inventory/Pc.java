package com.javapatterns.visitor.inventory;

public class Pc extends Composite
{
    public Pc()
    {
        super.add(new IntegratedBoard());
        super.add(new HardDisk());
        super.add(new Case());
    }

	public void accept(Visitor v)
	{
        System.out.println("Pc has been visited.");
		super.accept(v);
	}
} 

