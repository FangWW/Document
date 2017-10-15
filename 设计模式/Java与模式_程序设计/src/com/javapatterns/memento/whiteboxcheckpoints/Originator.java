package com.javapatterns.memento.whiteboxcheckpoints;

import java.util.Enumeration;
import java.util.Vector;

public class Originator
{
	private Vector states;
	private int index;

    /** @link dependency 
     * @label wide*/
    /*#Memento lnkMemento;*/
	
	public Originator()
	{ 
		states = new Vector();
		index = 0;
	}
	
	public Memento createMemento()
	{ 
		return new Memento(states, index);
	}
	
	public void restoreMemento(Memento memento)
	{ 
		states = memento.getStates();
		index = memento.getIndex();
	}
	
	public void setState(String state)
	{ 
		this.states.addElement(state);
		index++;
	}
	
	public void printStates()
	{ 
		System.out.println("Total number of states : " + index);
		for(Enumeration e = states.elements(); e.hasMoreElements(); )
		{ 
			System.out.println( e.nextElement() );
		}
	}
}
