package com.javapatterns.simplefactory.exercise;

public class Triangle implements Shape
{
    public void draw()
    {
		System.out.println("Triangle.draw()");
    }

    public void erase()
    {
		System.out.println("Triangle.erase()");
    }
}
