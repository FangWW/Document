package com.javapatterns.keygen.ver1;

public class KeyGenerator
{
    private static KeyGenerator keygen =
        new KeyGenerator();

    private int key = 1000;

    private KeyGenerator() {}

    public static KeyGenerator getInstance()
    {
        return keygen;
    }

    public synchronized int getNextKey()
    {
        return key++;
    }
}
