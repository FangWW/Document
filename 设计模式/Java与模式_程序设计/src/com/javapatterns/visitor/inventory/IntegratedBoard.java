package com.javapatterns.visitor.inventory;

public class IntegratedBoard extends Composite
{
  	public IntegratedBoard()
  	{
        super.add(new MainBoard());
        super.add(new Cpu());
  	}

	public void accept(Visitor v)
    {
        System.out.println("IntegratedBoard has been visited.");
		super.accept(v);
    }
}

