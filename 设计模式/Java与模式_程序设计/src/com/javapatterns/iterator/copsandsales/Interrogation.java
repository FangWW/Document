package com.javapatterns.iterator.copsandsales;

public class Interrogation
{
    private static ShoppingCart copA;
    private static ShoppingCart copB;

    private static Iterator it;

	public static void main(String[] args)
    {

	    copA = new CartOfCopA();
	    copA.append(new String("No. 1: Dish Washer"));
	    copA.append(new String("No. 2: Hair Dresser"));
	    copA.append(new String("No. 3: Microwave"));
	
	    copB = new CartOfCopB();
	    copB.append(new String("No. 1: Hair Dresser"));
	    copB.append(new String("No. 2: Diskman"));
	    copB.append(new String("No. 3: Digital Camera"));
	    copB.append(new String("No. 4: PC"));
	    copB.append(new String("No. 5: Dish Washer"));
	
	    System.out.println("Creating forward iterator:");
	    Iterator it = copA.createIterator();

	    it.first();
	    while (!it.isDone())
	    {
			String item = (String)it.currentItem();
			it.next();
			System.out.println("Item: " + item);
	    }

	    System.out.println("Creating backward iterator:");
	    it = copB.createIterator();

	    it.first();
	    while (!it.isDone())
	    {
			String item = (String)it.currentItem();
			it.next();
			System.out.println("Item : " + item);
	    }
	}
}