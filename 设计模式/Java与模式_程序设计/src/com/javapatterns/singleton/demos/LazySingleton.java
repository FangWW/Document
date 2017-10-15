package com.javapatterns.singleton.demos;

/**
 * Only once instance of the class may be created during the
 * execution of any given program. Instances of this class should
 * be aquired through the getInstance() method. Notice that there
 * are no public constructors for this class.
 */

public class LazySingleton
{
    private LazySingleton() { }

    synchronized public static LazySingleton getInstance()
    {
	    if (m_instance == null)
	    {
	        m_instance = new LazySingleton();
	    }
	    return m_instance;
	}

    /**
     * @label Creates 
     */
    private static LazySingleton m_instance = null;
}
