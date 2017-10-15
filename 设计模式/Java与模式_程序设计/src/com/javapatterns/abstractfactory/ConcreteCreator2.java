package com.javapatterns.abstractfactory;

public class ConcreteCreator2 implements Creator
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
    /*# ProductA2 lnkProductA2; */

    /** @link dependency */
    /*# ProductB2 lnkProductB2; */
}
