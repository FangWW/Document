package com.javapatterns.visitor.visitoradapter;

public class NodeA extends Node
{
    public void accept(Visitor visitor)
    {
        visitor.visit(this);
    }

    public String operationA()
    {
        return "NodeA is visited";
    }
}
