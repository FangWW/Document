package com.javapatterns.visitor.inventory;

public class PriceVisitor extends Visitor
{
	private float total;   

	public PriceVisitor()
    {
        total = 0;
    }

	public float value()
    {
        return total;
    }

	public void visitCase(Case e)
    {
        total += e.price();
    }

	public void visitCpu(Cpu e)
    {
        total += e.price();
    }

	public void visitHardDisk(HardDisk e)
    {
        total += e.price();
    }

	public void visitIntegratedBoard(IntegratedBoard e)
    {
        total += e.price();
    }

	public void visitMainBoard(MainBoard e)
    {
        total += e.price();
    }

	public void visitPc(Pc e)
    {
        total += e.price();
    }
}

