package com.javapatterns.visitor.visitoradapter;

public class NodeB extends Node
{
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    public String operationB()
    {
       return "NodeB is visited";
    }
}
