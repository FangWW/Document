package com.javapatterns.memento.innermember;

public class Client
{
    private static Outer outer;

    public static void main(String[] args)
    {
		outer = new Outer();

        Outer.Inner inner = outer.instantiateInner();

        inner.testThis();
    }
}

