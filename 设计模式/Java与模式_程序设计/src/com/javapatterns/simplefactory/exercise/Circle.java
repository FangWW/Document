package com.javapatterns.simplefactory.exercise;

public class Circle implements Shape
{
    public void draw()
    {
    		System.out.println("Circle.draw()");
	}

    public void erase()
    {
    		System.out.println("Circle.erase()");
    }
}
