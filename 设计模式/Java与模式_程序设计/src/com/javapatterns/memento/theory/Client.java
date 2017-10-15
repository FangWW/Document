package com.javapatterns.memento.theory;

public class Client
{
    private static Originator o = new Originator();
    private static Caretaker c = new Caretaker();

    public static void main(String[] args)
    {
        o.setState("On");

	    // Store internal state
	    c.saveMemento( o.createMemento() );
	
	    // Continue changing originator
	    o.setState("Off");

	    // Restore saved state
	    o.restoreMemento( c.retrieveMemento() );

    }
}
