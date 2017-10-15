package com.javapatterns.memento.whiteboxenhanced;

public class Caretaker
{
    private Originator o ;

    /**
     * @link aggregation 
     * @label wide
     */
    private Memento memento;

    public Caretaker(Originator o)
    {
        this.o = o;
    }

    public void restoreMemento()
    {
	    this.o.restoreMemento(this.memento );
    }

    public void createMemento()
    {
        this.memento = this.o.createMemento();
    }
}
