package com.javapatterns.composite.transparent;

import java.util.Enumeration;
public interface Component
{
    void sampleOperation();

    Composite getComposite();

    void add(Component component);

    void remove(Component component);

    Enumeration components();
}
