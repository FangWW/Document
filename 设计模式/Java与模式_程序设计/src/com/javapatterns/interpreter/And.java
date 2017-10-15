package com.javapatterns.interpreter;

public class And extends Expression
{
    /**
     * @link aggregation 
     */
    private Expression left, right;

    public And(Expression left, Expression right)
    {
		this.left = left;
		this.right = right;
    }

    public boolean interpret(Context ctx)
    {
        return left.interpret(ctx) && right.interpret(ctx);
    }

    public boolean equals(Object o)
    {
        if (o != null && o instanceof And)
        {
          return this.left.equals(((And) o).left) &&
                 this.right.equals(((And) o).right);
        }
        return false;
    }

    public int hashCode()
    {
        return (this.toString()).hashCode();
    }

    public String toString()
    {
		return "(" + left.toString() + " AND " + right.toString() + ")";
    }
}
