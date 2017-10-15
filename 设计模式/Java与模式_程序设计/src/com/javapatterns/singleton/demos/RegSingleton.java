package com.javapatterns.singleton.demos;


import java.util.HashMap;

public class RegSingleton {

    protected RegSingleton() {}

    static public RegSingleton getInstance(String name)
    {
        if (name == null)
        {
            name = "com.javapatterns.singleton.demos.RegSingleton";
        }

        System.out.println("From RegSingleton: requesting for " + name );

        if (m_registry.get(name) == null)
        {
            try
            {
                m_registry.put( name, Class.forName(name).newInstance() ) ;
            }
            catch(ClassNotFoundException e)
            {
                System.out.println("Class " + name + " is not found.");
            }
            catch(InstantiationException e)
            {
                System.out.println("Class " + name + " can not be instantiated.");
            }
            catch(IllegalAccessException e)
            {
                System.out.println("Class " + name + " can not be accessed.");
            }
        }
        return  (RegSingleton) (m_registry.get(name) );
    }

    static private HashMap m_registry = new HashMap();

    /**
     * @label Creates
     * @directed*/
    /*# private RegSingletonChild lnkRegSingletonChild; */

    /**
     * @label Creates
     * @directed
     */
    /*# private RegSingleton lnkRegSingleton;  */

    static
    {
        RegSingleton x = new RegSingleton();
        m_registry.put( x.getClass().getName() , x);
    }

    public String about()
    {
        return "Hello, I am RegSingleton.";
    }

}
