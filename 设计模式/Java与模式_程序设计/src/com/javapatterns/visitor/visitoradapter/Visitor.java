package com.javapatterns.visitor.visitoradapter;

public interface Visitor
{
    void visit(NodeA node);

    void visit(NodeB node);
}
