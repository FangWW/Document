package com.javapatterns.composite;

import java.util.Enumeration;
import java.util.Vector;

public class Composite implements Component
{
    public Composite getComposite()
    {
        return this;
    }

    public void sampleOperation()
    {
        java.util.Enumeration enumeration = components();
        while (enumeration.hasMoreElements())
        {
            ((Component)enumeration.nextElement()).sampleOperation();
        }
    }

    public void add(Component component)
    {
        componentVector.addElement(component);
    }

    public void remove(Component component)
    {
        componentVector.removeElement(component);
    }

    public Enumeration components()
    {
        return componentVector.elements();
    }

    /**
     * @associates <{Component}>
     * @link aggregation 
     * @supplierCardinality 0..*
     */
    private Vector componentVector = new java.util.Vector();
}
