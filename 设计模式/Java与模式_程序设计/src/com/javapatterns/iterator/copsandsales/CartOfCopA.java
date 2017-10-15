
package com.javapatterns.iterator.copsandsales;
     
public class CartOfCopA extends ShoppingCart
{

	public CartOfCopA(){}
  
	public Iterator createIterator()
	{
		return new ForwardIterator(this);
	}
} 