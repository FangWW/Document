package com.javapatterns.interpreter;

public class Constant extends Expression
{
    private boolean value;

    public Constant(boolean value)
    {
		this.value = value;
    }

    public boolean interpret(Context ctx)
    {
		return value;
    }

    public boolean equals(Object o)
    {
        if (o != null && o instanceof Constant)
        {
          return this.value == ((Constant) o).value;
        }
        return false;
    }

    public int hashCode()
    {
        return (this.toString()).hashCode();
    }

    public String toString()
    {
		return new Boolean(value).toString();
    }
}
