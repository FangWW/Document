package com.javapatterns.factorymethod.farm;
                                                         
public class GrapeGardener implements FruitGardener 
{
    public Fruit factory()
    {
        return new Apple();
    }
}
