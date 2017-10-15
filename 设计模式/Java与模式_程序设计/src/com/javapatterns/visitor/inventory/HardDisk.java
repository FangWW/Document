package com.javapatterns.visitor.inventory;

public class HardDisk extends Equipment
{
	public double price()
    {
        return 200.00;
    }

	public void accept(Visitor v)
    {
        System.out.println("HardDisk has been visited.");
        v.visitHardDisk(this);
    }
}

