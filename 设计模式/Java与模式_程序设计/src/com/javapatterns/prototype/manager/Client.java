package com.javapatterns.prototype.manager;

public class Client
{
    public void registerPrototype()
    {
        prototype = new ConcretePrototype();

        Prototype copytype = (Prototype) prototype.clone();

        mgr.add(copytype);
    }

    /**
     * @directed
     * @label Creates*/
    private PrototypeManager mgr;

    /**
     * @link aggregation
     * @directed
     * @label Uses 
     */
    private Prototype prototype;

}
