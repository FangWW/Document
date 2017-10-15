package com.javapatterns.memento.whiteboxcheckpoints;

public class Client
{

	private static Originator o = new Originator();
	private static Caretaker c = new Caretaker(o);

	static public void main(String[] args)
	{ 
		o.setState("state 0");
		c.createMemento();
		
		o.setState("state 1");
		c.createMemento();
		
		o.setState("state 2");
		c.createMemento();
		
		o.setState("state 3");
		c.createMemento();
		
		o.setState("state 4");
		c.createMemento();
		
		o.printStates();

		System.out.println("Restoring to 2");
		c.restoreMemento(2);
		o.printStates();
		
		System.out.println("Restoring to 0");
		c.restoreMemento(0);
		o.printStates();
		
		System.out.println("Restoring to 3");
		c.restoreMemento(3);
		o.printStates();
		
	} 
}
