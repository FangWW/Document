package com.javapatterns.interpreter;

public class Not extends Expression
{
    /**
     * @link aggregation 
     */
    private Expression exp;

    public Not(Expression exp)
    {
		this.exp = exp;
    }

    public boolean interpret(Context ctx)
    {
		return !exp.interpret(ctx);
    }

    public boolean equals(Object o)
    {
        if (o != null && o instanceof Not)
        {
          return this.exp.equals(((Not) o).exp);
        }
        return false;
    }

    public int hashCode()
    {
        return (this.toString()).hashCode();
    }

    public String toString()
    {
		return " (Not " + exp.toString() + ")";
    }
}
