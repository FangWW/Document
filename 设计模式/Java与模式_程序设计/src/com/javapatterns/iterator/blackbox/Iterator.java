package com.javapatterns.iterator.blackbox;

public interface Iterator
{
    void first();

    void next();

    boolean isDone();

    Object currentItem();
}
