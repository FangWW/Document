package com.javapatterns.iterator.blackbox;

public class ConcreteAggregate extends Aggregate
{
    private Object[] objs = {"Monk Tang",
        "Monkey", "Pigsy",
        "Sandy", "Horse"};

    public Iterator createIterator()
    {
        return new ConcreteIterator();
    }

	private class ConcreteIterator
        implements Iterator
	{
		private int currentIndex = 0;

		public void first()
        {
			currentIndex = 0;
        }

		public void next()
        {
            if ( currentIndex < objs.length )
            {
				currentIndex++;
            }
        }

		public boolean isDone()
		{
			return (currentIndex == objs.length);
		}
	
		public Object currentItem()
		{
			return objs[currentIndex];
		}
	}
}
