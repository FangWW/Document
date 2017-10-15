package com.javapatterns.flyweight.coffeeshop;

public class Table
{
    private int number;

    public Table(int number)
    {
        this.number = number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    public int getNumber()
    {
        return number;
    }

}

