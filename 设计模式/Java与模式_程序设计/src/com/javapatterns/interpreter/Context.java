package com.javapatterns.interpreter;

import java.util.HashMap;

public class Context
{
    private HashMap map = new HashMap();

    public void assign(Variable var, boolean value)
    {
        map.put(var, new Boolean(value));
    }

    public boolean lookup(Variable var) throws IllegalArgumentException
    {
        Boolean value = (Boolean) map.get(var);

		if (value == null)
        {
	    	throw new IllegalArgumentException();
        }
	    return value.booleanValue();
    }
}
