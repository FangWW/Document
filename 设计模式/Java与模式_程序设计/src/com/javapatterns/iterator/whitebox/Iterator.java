package com.javapatterns.iterator.whitebox;

public interface Iterator
{
    void first();

    void next();

    boolean isDone();

    Object currentItem();
}
