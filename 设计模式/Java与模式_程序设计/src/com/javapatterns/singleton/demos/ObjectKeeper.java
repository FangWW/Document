package com.javapatterns.singleton.demos;


import java.util.Vector;

    /**
    * This class keeps your objects from garbage collected
    */
public class ObjectKeeper extends Thread {

    private ObjectKeeper()
    {
        new Thread(this).start();
    }

    public void run()
    {
        try
        {
            join();
        }
        catch (InterruptedException e)
        {

        }
    }

    /**
    * Any object passed here will be kept until you call discardObject()
    */
    public static void keepObject(Object myObject)
    {
		System.out.println(" Total number of kept objects: " + m_keptObjects.size());
		m_keptObjects.add(myObject);
		System.out.println(" Total number of kept objects: " + m_keptObjects.size());
    }

    /**
    * This method will remove the protect of the object you pass in and make it
    * available for Garbage Collector to collect.
    */
    public static void discardObject(Object myObject)
    {
        System.out.println(" Total number of kept objects: " + m_keptObjects.size());
        m_keptObjects.remove(myObject);
        System.out.println(" Total number of kept objects: " + m_keptObjects.size());
    }

    /**
     * @label Creates
     */
    private static ObjectKeeper m_keeper = new ObjectKeeper();
    private static Vector m_keptObjects = new Vector();
}
