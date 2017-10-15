package com.javapatterns.prototype;

public class Client
{
    public void operation(Prototype example)
    {
        Prototype p = (Prototype) example.clone();
    }

    /**
     * @directed
     * @clientRole prototype 
     * @link aggregation
     * @clientCardinality 1
     * @supplierCardinality 1..*
     */
    private Prototype prototype;
}
