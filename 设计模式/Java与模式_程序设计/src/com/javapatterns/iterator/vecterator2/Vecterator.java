package com.javapatterns.iterator.vecterator2;

import java.util.NoSuchElementException;
import java.util.Vector;

public class Vecterator
{
     private Vector iteratee;
     private int count;

     public Vecterator(Vector v)
     {
          iteratee = (Vector) v.clone();
          count = 0;
     }

     public void first()
     {
		count = 0;
     }

     public boolean isDone()
     {
          return count < iteratee.size();
     }

     public void next()
     {
        if (count < iteratee.size())
		{
            count++;
        }
     }

     public Object currentItem()
     {
          synchronized (iteratee)
          {
               if (count < iteratee.size())
               {
                    return iteratee.elementAt(count);
               }
          }
	      throw new NoSuchElementException("Vecterator");
     }

}
