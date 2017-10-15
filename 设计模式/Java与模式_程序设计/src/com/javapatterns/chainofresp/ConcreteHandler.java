package com.javapatterns.chainofresp;

public class ConcreteHandler extends Handler
{
    public void handleRequest()
    {
        if (getSuccessor() != null)
        {
            System.out.println("The request is passed to " + getSuccessor());
            getSuccessor().handleRequest();
        }
        else
        {
            System.out.println("The request is handled here.");
        }
    }
}
