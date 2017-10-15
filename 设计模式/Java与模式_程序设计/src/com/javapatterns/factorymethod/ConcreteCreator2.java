package com.javapatterns.factorymethod;

public class ConcreteCreator2 implements Creator
{
    public Product factory()
    {
        return new ConcreteProduct2();
    }
}
