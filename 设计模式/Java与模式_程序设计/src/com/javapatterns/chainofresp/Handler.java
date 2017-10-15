package com.javapatterns.chainofresp;

public abstract class Handler
{
    /**
     * @link aggregation 
     * @supplierCardinality 0..1
     */
    protected Handler successor;

    public abstract void handleRequest();

    public void setSuccessor(Handler successor)
    {
        this.successor = successor;
    }

    public Handler getSuccessor()
    {
        return successor;
    }
}
