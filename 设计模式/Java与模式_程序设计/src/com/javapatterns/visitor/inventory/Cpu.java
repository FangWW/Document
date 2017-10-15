package com.javapatterns.visitor.inventory;

public class Cpu extends Equipment
{
	public double price()
    {
        return 800.00;
    }

	public void accept(Visitor v)
    {
        System.out.println("CPU has been visited.");
        v.visitCpu(this);
    }
}
