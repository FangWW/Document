package com.javapatterns.adapter.kittie2puppie;

public interface Puppie
{
    /**
     * Class Adaptee contains operation sampleOperation1. 
     */
    void wao();

    /**
     * Class Adaptee doesn't contain operation sampleOperation2. 
     */
    void fetchBall();

    void run();

    void sleep();

    void setName(String name);

    String getName();
}
