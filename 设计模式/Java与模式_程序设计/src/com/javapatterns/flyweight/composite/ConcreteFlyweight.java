package com.javapatterns.flyweight.composite;

public class ConcreteFlyweight extends Flyweight
{
    private Character intrinsicState = null;

	public ConcreteFlyweight(Character state)
	{ 
		this.intrinsicState = state;
	}
	
	public void operation(String state)
	{ 
		System.out.print( "\nInternal State = " +
            intrinsicState + " Extrinsic State = " + state );
	}
}
