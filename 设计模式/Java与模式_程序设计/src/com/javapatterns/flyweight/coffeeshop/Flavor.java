package com.javapatterns.flyweight.coffeeshop;

public class Flavor extends Order
{  
	private String flavor;
	
	public Flavor(String flavor)
	{
	    this.flavor = flavor;
	}
	
	public String getFlavor()
	{
	    return this.flavor;
	}
	
	public void serve(Table table)
	{
	    System.out.println("Serving table " + table.getNumber() + " with flavor " + flavor );
	}
}

