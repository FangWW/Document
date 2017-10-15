package com.javapatterns.singleton.answers;


/**
 * Only once instance of the class may be created during the
 * execution of any given program. Instances of this class should
 * be aquired through the getInstance() method. Notice that there
 * are no public constructors for this class.
 */

import java.util.Date;

public class LazySingleton1 {

    private LazySingleton1()
    {
        m_instanceNumber = ++m_numberOfInstances;
        m_creationDate = new Date();
    }
    public static LazySingleton1 getInstance()
    {
        if (m_instance == null)
        {
            synchronized(LazySingleton1.class)
            {
                if (m_instance == null)
                {
                    m_instance = new LazySingleton1();
                }
            }
        }
        return m_instance;
    }

    public String aboutMe()
    {
        return "Hi, I'm an instance of LazySingleton1.\n" +
          "My classloader is " + getClass().getClassLoader() + ".\n" +
          "My identity hash code " + System.identityHashCode(this) + "\n" +
          "So far, since the class was loaded, " + m_numberOfInstances + " instance" +
          (m_numberOfInstances==1?"":"s") +
          " of this Singleton class " + (m_numberOfInstances==1?"has":"have")+
          " been created.\nI'm instance number " + m_instanceNumber + ".\n" +
          "I was created at " + m_creationDate + "\n" +
          "If there's really only one instance of my class, " +
          "these items will be constant for all instances of LazySingleton1.";
    }
    /**
     * @link
     * @shapeType PatternLink
     * @pattern <{Singleton}>
     * @supplierRole Singleton factory
     */
    /*# private LazySingleton1 _lazySingleton; */

    /**
     * @label Creates
     */
    private static LazySingleton1 m_instance = null;

    // These fields help us see whether one or
    // more Singletons were created. There is usually
    // no need for them in a Singleton.
    private int m_instanceNumber;
    private Date m_creationDate;

    // Counts instantiations. Should always be  0 or 1
    private static int m_numberOfInstances;

}
