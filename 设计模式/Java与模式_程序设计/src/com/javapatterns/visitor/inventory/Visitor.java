package com.javapatterns.visitor.inventory;

public abstract class Visitor
{
	public abstract void visitCase(Case e);

	public abstract void visitCpu(Cpu e);

	public abstract void visitHardDisk(HardDisk e);

	public abstract void visitIntegratedBoard(IntegratedBoard e);

	public abstract void visitMainBoard(MainBoard e);

	public abstract void visitPc(Pc e);
}

