package com.javapatterns.iterator.whitebox;

public class ConcreteIterator implements Iterator
{
    /**
     * @directed 
     */
    private ConcreteAggregate agg;
	private int index = 0;
    private int size = 0;

    public ConcreteIterator(ConcreteAggregate agg)
    {
		this.agg = agg;
        size = agg.size();
        index = 0 ;
    }

    public void first()
    {
		index = 0 ;
    }

    public void next()
    {
		if (index < size)
        {
			index++;
        }
    }

    public boolean isDone()
    {
        return (index >= size);
    }

    public Object currentItem()
    {
        return agg.getElement(index);
    }
}
