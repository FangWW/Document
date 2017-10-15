package com.javapatterns.iterator.copsandsales;
                 
import java.util.Vector;

public abstract class ShoppingCart
{
	public abstract Iterator createIterator();

	public void append(Object anObj)
	{
		elements.addElement(anObj);
	}
	
	public void remove(Object anObj)
	{
		elements.removeElement(anObj);
	}
	
	public Object currentItem(int n)
	{
	  	return elements.elementAt(n);
	}
	
	public int count()
	{
		return elements.size();
	}
	
	private Vector elements = new Vector(5);
}