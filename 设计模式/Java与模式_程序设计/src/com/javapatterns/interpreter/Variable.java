package com.javapatterns.interpreter;

public class Variable extends Expression
{
    private String name;

    public Variable(String name)
    {
		this.name = name;
    }

    public boolean interpret(Context ctx)
    {
		return ctx.lookup(this);
    }

    public boolean equals(Object o)
    {
        if (o != null && o instanceof Variable)
        {
          return this.name.equals(((Variable) o).name);
        }
        return false;
    }

    public int hashCode()
    {
        return (this.toString()).hashCode();
    }

    public String toString()
    {
		return name;
    }
}
