package com.javapatterns.templatemethod.hook;

public class ConcreteClass extends AbstractClass 
{
    public void hookMethod()
    {
        System.out.println("This is a re-implemented hook method.");
    }

    public void abstractMethod()
    {
        System.out.println("This is an implementation of an abstract method.");
    }
}

