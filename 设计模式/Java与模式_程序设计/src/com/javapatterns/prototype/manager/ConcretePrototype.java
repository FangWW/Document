package com.javapatterns.prototype.manager;

public class ConcretePrototype implements Prototype
{
    public synchronized Object clone()
    {
		Prototype temp = null;

		try
		{
			temp = (Prototype) super.clone();

			return temp;
		}
		catch(CloneNotSupportedException e)
		{
			System.out.println("Clone failed.");
		}
		finally
		{
			return temp;
		}
    }
}
