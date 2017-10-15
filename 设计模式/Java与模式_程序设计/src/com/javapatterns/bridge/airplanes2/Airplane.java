package com.javapatterns.bridge.airplanes2;

abstract public class Airplane {
    abstract public void fly() ;

    /**
     * @link aggregation
     * @directed
     * @clientRole 1
     * @supplierRole 1 
     */
    protected AirplaneMaker airplaneMaker;
}
