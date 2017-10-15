package com.javapatterns.memento.whiteboxenhanced;

public class Client
{
    private static Originator o = new Originator();
    private static Caretaker c = new Caretaker(o);

    public static void main(String[] args)
    {
        o.setState("On");

	    // Store internal state
        c.createMemento();

	    // Continue changing originator
	    o.setState("Off");

	    // Restore saved state
	    c.restoreMemento();

    }
}
