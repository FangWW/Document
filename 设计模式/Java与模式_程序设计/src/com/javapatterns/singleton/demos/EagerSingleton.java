package com.javapatterns.singleton.demos;

/**
 * Only once instance of the class may be created during the execution of any given program. Instances of this class should
 * be aquired through the getInstance() method. Notice that there are no public constructors for this class.
 */
public class EagerSingleton {
    private EagerSingleton() { }

    public static EagerSingleton getInstance() {
        return m_instance;
    }

    /** @label Creates */
    private static final EagerSingleton m_instance = new EagerSingleton();
}
