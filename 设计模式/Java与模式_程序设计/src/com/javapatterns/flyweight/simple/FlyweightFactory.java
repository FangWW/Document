package com.javapatterns.flyweight.simple;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FlyweightFactory
{
    private HashMap flies = new HashMap();
    /**
     * @link aggregation
     * @directed
     * @clientRole Flyweights
     */
    private Flyweight lnkFlyweight;

	public FlyweightFactory(){}
	
	public synchronized Flyweight factory(Character state)
	{ 
		if ( flies.containsKey( state ) )
        {
            return (Flyweight) flies.get( state );
        }
        else
        {
			Flyweight fly = new ConcreteFlyweight( state );
            flies.put( state , fly);
            return fly;
        }
	}
	
	public void checkFlyweight()
	{ 
		Flyweight fly ;
        int i = 0;

        System.out.println("\n==========checkFlyweight()=============");
		for ( Iterator it = flies.entrySet().iterator() ; it.hasNext() ;  )
        {
			Map.Entry e = (Map.Entry) it.next();
            System.out.println("Item " + (++i) + " : " + e.getKey());
        }
        System.out.println("==========checkFlyweight()=============");
	}

}
