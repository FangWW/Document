package com.javapatterns.interpreter;

public class Client
{
    private static Context ctx;
    private static Expression exp ;

    public static void main(String[] args)
    {
        ctx = new Context();

		Variable x = new Variable("x");
        Variable y = new Variable("y");
		Constant c = new Constant(true);

        ctx.assign(x, false);
        ctx.assign(y, true);

        exp = new Or( new And(c, x) , new And(y, new Not(x)));
		System.out.println( "x = " + x.interpret(ctx));
		System.out.println( "y = " + y.interpret(ctx));
		System.out.println( exp.toString() + " = " + exp.interpret(ctx));
    }
}
