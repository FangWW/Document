package com.javapatterns.builder;

abstract public class Builder
{
    public abstract void buildPart1();

    public abstract void buildPart2();

    public abstract Product retrieveResult();
}
