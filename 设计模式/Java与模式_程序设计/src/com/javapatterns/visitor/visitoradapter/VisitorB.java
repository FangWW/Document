package com.javapatterns.visitor.visitoradapter;

public class VisitorB extends VisitorAdapter
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
