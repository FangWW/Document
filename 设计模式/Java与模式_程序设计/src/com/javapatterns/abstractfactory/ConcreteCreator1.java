package com.javapatterns.abstractfactory;

public class ConcreteCreator1 implements Creator
{
    public ProductA factoryA()
    {
        return new ProductA1();
    }

    public ProductB factoryB()
    {
        return new ProductB1();
    }

    /** @link dependency */
    /*# ProductA1 lnkProductA1; */

    /** @link dependency */
    /*# ProductB1 lnkProductB1; */
}
