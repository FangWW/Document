package com.javapatterns.abstractfactory.farm;

public class TropicalVeggie implements Veggie
{
    private String name;

    public TropicalVeggie(String name)
    {
    }

    public String getName()
    {
		return name;
	}

    public void setName(String name)
    {
		this.name = name;
	}

}
