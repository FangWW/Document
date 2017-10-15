package com.javapatterns.flyweight.simple;

public class ConcreteFlyweight extends Flyweight
{
    private Character intrinsicState = null;

	public ConcreteFlyweight(Character state)
	{ 
		this.intrinsicState = state;
	}
	
	public void operation(String state)
	{ 
		System.out.print( "\nIntrinsic State = " + intrinsicState +
            ", Extrinsic State = " + state);
	}
}
