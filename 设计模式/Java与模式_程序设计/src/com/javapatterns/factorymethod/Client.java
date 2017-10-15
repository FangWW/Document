package com.javapatterns.factorymethod;

public class Client
{
    private static Creator creator1, creator2;
    private static Product prod1, prod2;

    public static void main(String[] args)
    {
		creator1 = new ConcreteCreator1();
        prod1 = creator1.factory();

		creator2 = new ConcreteCreator2();
        prod2 = creator2.factory();

    }
}
