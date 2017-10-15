package com.javapatterns.simplefactory.simplified;

public class Creator
{
    public static ConcreteProduct factory()
    {
        return new ConcreteProduct();
    }

    /** @link dependency 
     * @label Creates*/
    /*# com.javapatterns.simplefactory.simplified.ConcreteProduct lnkConcreteProduct; */
}
