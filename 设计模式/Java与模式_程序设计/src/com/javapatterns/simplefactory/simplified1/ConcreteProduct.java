package com.javapatterns.simplefactory.simplified1;

public class ConcreteProduct 
{
	public ConcreteProduct(){}

    public static ConcreteProduct factory()
    {
        return new ConcreteProduct();
    }

    /** @link dependency 
     * @label Creates*/
    /*# ConcreteProduct lnkConcreteProduct; */
}
