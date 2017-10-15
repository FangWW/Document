package com.javapatterns.visitor.visitor0;

public class VisitorB implements Visitor
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
