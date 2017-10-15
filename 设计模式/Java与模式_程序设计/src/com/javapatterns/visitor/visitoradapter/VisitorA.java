package com.javapatterns.visitor.visitoradapter;

public class VisitorA extends VisitorAdapter
{
    public void visit(NodeA nodeA)
    {
        System.out.println( nodeA.operationA() );
    }
    public void visit(NodeB nodeB)
    {
        System.out.println( nodeB.operationB() );
    }
}
