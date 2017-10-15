package com.javapatterns.iterator.vecterator1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

public class Vecterator implements Iterator
{
     Vector iteratee;
     int count;

     public Vecterator(Vector v)
     {
          iteratee = v;
          count = 0;
     }

     public boolean hasNext()
     {
          return count < iteratee.size();
     }

     public Object next()
     {
          synchronized (iteratee)
          {
               if (count < iteratee.size())
               {
                    return iteratee.elementAt(count++);
               }
          }
	     throw new NoSuchElementException("VectorIterator");
     }

     public void remove()
     {
		throw new UnsupportedOperationException();
     }
}
