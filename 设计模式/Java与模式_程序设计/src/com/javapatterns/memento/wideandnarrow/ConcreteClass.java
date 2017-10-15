package com.javapatterns.memento.wideandnarrow;

public class ConcreteClass implements Narrow, Wide
{
    public void operation1()
    {
		System.out.println("operation1()");
    }

    public void operation2()
    {
		System.out.println("operation2()");
    }
}
