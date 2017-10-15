package com.javapatterns.observer;

import java.util.Enumeration;
import java.util.Vector;

public class ConcreteSubject implements Subject
{
    public void attach(Observer observer)
    {
        observersVector.addElement(observer);
    }

    public void detach(Observer observer)
    {
        observersVector.removeElement(observer);
    }

    public void notifyObservers()
    {
		java.util.Enumeration enumeration = observers();
		while (enumeration.hasMoreElements())
        {
			((Observer)enumeration.nextElement()).update();
		}
	}

    public Enumeration observers()
    {
        return ((java.util.Vector) observersVector.clone()).elements();
    }

    /**
     * @associates <{Observer}>
     * @link aggregation
     * @supplierCardinality 0..*
     */
    private Vector observersVector = new java.util.Vector();
}
