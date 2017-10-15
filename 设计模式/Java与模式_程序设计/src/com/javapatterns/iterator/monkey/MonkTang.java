package com.javapatterns.iterator.monkey;

import com.javapatterns.iterator.vecterator1.*;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.Vector;

public class MonkTang
{
    /**
     * @link aggregation
     * @directed 
     */
    private static Vector desciples;

    /**
     * @link aggregation
     * @directed
     * @clientCardinality 1
     * @supplierCardinality * 
     */
    private Desciple lnkDesciple;

    public static void main(String[] args)
    {
		desciples = new Vector(3);

        Desciple monkey = new Monkey();
        Desciple pigsy = new Pigsy();
        Desciple sandy = new Sandy();

        desciples.add(monkey);
        desciples.add(pigsy);
        desciples.add(sandy);

        System.out.println("The following is from polymorphic iterator");
        listPolymorphic();

        System.out.println("The following is from concrete iterator");
        listConcrete();

        System.out.println("The above are also external iterators");
        System.out.println("The follwoing is from internal iterator");
        listInternal();

        System.out.println("The follwoing from robust iterator");
        listRobust();
    }

    private static void listPolymorphic()
    {
        Desciple desciple ;

        Iterator it = desciples.iterator();

		while(it.hasNext())
        {
			desciple = (Desciple) it.next();
			desciple.speak();
        }

    }

    private static void listConcrete()
    {
        Desciple desciple ;

        Vecterator vect = new Vecterator(desciples);

		while(vect.hasNext())
        {
			desciple = (Desciple) vect.next();
			desciple.speak();
        }

    }

    private static void listInternal()
    {
        Desciple desciple ;

        for(int i = 0 ; i < desciples.size() ; i++)
        {
			desciple = (Desciple) desciples.elementAt(i);
			desciple.speak();
        }
    }

	private static void listRobust()
    {
        Desciple desciple ;

        ListIterator it = desciples.listIterator();

		while(it.hasNext())
        {
			desciple = (Desciple) it.next();
			if (desciple instanceof Monkey )
            {
				it.remove();
                it.next();

                it.add(new Horse());
                it.previous();
            }
			desciple.speak();
        }

    }
}
