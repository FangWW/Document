package com.javapatterns.bridge.simplified;

abstract public class Abstraction
{
    public void operation()
    {
    	impl.operationImp();
    }

    /**
     * @link aggregation
     * @directed
     * @supplierRole imp*/
    private ConcreteImplementor impl;

    /**
     * @directed
     * @link aggregation 
     * @supplierRole impl*/
    private ConcreteImplementor lnkConcreteImplementorB;
}
