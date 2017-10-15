package com.javapatterns.strategy;

public class Context
{
    public void contextInterface()
    {
        strategy.strategyInterface();
    }

    /**
     * @link aggregation
     * @directed 
     */
    private Strategy strategy;
}
