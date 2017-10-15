package com.javapatterns.memento.historyonself;

public class Client
{
    private static Originator o;
    private static MementoIF memento;

    public static void main(String[] args)
    {
		o = new Originator();

        o.changeState("State 1");
        memento = o.createMemento();

        o.changeState("State 2");
        o.restoreMemento(memento);
    }
}

