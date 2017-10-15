package com.javapatterns.simplefactory.exercise;

public class Square implements Shape
{
    public void draw()
    {
		System.out.println("Square.draw()");
    }

    public void erase()
    {
		System.out.println("Square.erase()");
    }
}
