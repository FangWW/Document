package com.javapatterns.doubledispatch.mixed;

public class Client
{
    private static Super a;
    private static Super b;
    public static void main(String[] args)
    {
		a = new SubA();
        b = new SubB();

        a.operation(b);
    }
}

