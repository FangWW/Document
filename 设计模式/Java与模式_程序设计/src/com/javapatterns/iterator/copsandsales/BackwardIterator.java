package com.javapatterns.iterator.copsandsales;

public class BackwardIterator implements Iterator
{             
	public BackwardIterator(ShoppingCart aCart)
    {
		cart = aCart;
	}

	public void first()
    {
		state = cart.count() - 1;
	}

	public void next()
    {
		if (!isDone())
		{
			state--;
		}
	}
	
	public boolean isDone()
    {
		if (state<0)
        {
			return true;
		}
		return false;
	}
	
	public Object currentItem()
    {
		return cart.currentItem(state);
	}
	
	private int state;
	private ShoppingCart cart;
}