package com.javapatterns.adapter.iterenum;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Itermeration implements Enumeration
{
	Iterator it;

    public Itermeration(Iterator it)
    {
        this.it = it;
    }

    public boolean hasMoreElements()
    {
        return it.hasNext();
    }

    public Object nextElement() throws NoSuchElementException
    {
		return it.next();
    }
}
