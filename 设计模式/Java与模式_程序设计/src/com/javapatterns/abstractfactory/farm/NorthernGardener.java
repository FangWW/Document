package com.javapatterns.abstractfactory.farm;

public class NorthernGardener implements Gardener
{
    public Fruit createFruit(String name)
    {
        return new NorthernFruit(name);
    }

    public Veggie createVeggie(String name)
    {
        return new NorthernVeggie(name);
    }

    /** @link dependency
     * @label Creates*/
    /*# NorthernVeggie lnkNorthernVeggie; */

    /** @link dependency
     * @label Creates*/
    /*# NorthernFruit lnkNorthernFruit; */
}
