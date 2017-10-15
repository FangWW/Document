package com.javapatterns.memento.theory;

public class Caretaker
{
    /**
     * @link aggregation 
     * @label narrow
     */
    private Memento memento;

    public Memento retrieveMemento()
    {
        return this.memento;
    }

    public void saveMemento(Memento memento)
    {
     	this.memento = memento;
    }
}
