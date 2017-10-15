package com.javapatterns.prototype.cloneexample;

public class Sheep implements Cloneable
{

    private String name = "Dolly";

    public Object clone() throws CloneNotSupportedException
    {
		return super.clone();
    }
}
