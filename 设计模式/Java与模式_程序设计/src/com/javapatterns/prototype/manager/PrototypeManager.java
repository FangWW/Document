package com.javapatterns.prototype.manager;

import java.util.Vector;

public class PrototypeManager
{
    public void add(Prototype object)
    {
        objects.add(object);
    }

    public Prototype get(int i)
    {
        return (Prototype) objects.get(i);
    }

    public int getSize()
    {
        return objects.size();
    }

    private Vector objects = new Vector();
}
