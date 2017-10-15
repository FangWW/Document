package com.javapatterns.memento.whiteboxcheckpoints;

import java.util.Vector;

public class Memento
{
	private Vector states;
	private int index;
	
	public Memento(Vector states, int index)
	{ 
		this.states = (Vector) states.clone();
		this.index = index;
	}
	
	Vector getStates()
	{ 
		return states;
	}
	
	int getIndex()
	{ 
		return this.index;
	}
}
