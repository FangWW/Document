package com.javapatterns.visitor.inventory;

import java.util.Vector;

public class InventoryVisitor extends Visitor
{
	private Vector inv;

	public InventoryVisitor()
    {
        inv = new Vector(10,5);
    }

	public int size()
    {
        return inv.size();
    }

	public void visitCase(Case e)
    {
        inv.addElement(e);
    }

	public void visitCpu(Cpu e)
    {
        inv.addElement(e);
    }

	public void visitHardDisk(HardDisk e)
    {
        inv.addElement(e);
    }

	public void visitIntegratedBoard(IntegratedBoard e)
    {
        inv.addElement(e);
    }

	public void visitMainBoard(MainBoard e)
    {
        inv.addElement(e);
    }

	public void visitPc(Pc e)
    {
        inv.addElement(e);
    }
}

