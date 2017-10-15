package com.javapatterns.visitor.visitor0;

public class ConcreteVisitorB implements Visitor
{
    public void visit(NodeA nodeA)
    {
        // Write your code here
        System.out.println( nodeA.operationA() );
    }

    public void visit(NodeB nodeB)
    {
        // Write your code here
        System.out.println( nodeB.operationB() );
    }
}
