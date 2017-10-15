package com.javapatterns.templatemethod.hook;

abstract public class AbstractClass
{
    public void hookMethod()
    {}

    public abstract void abstractMethod();

    public void concreteMethod()
    {
        System.out.println("This is a concrete method.");
    }
}

