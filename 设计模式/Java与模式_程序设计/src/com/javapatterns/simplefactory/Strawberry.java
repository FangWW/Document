package com.javapatterns.simplefactory;

public class Strawberry implements Fruit
{
    
    public void grow()
    {
    	System.out.println("Strawberry is growing...");
    }

    public void harvest()
    {
    	System.out.println("Strawberry has been harvested.");
    }

    public void plant()
    {
        System.out.println("Strawberry has been planted.");
    }

}
