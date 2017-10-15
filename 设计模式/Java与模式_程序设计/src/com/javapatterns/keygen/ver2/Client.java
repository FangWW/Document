package com.javapatterns.keygen.ver2;

public class Client
{
    private static KeyGenerator keygen;

    public static void main(String[] args)
    {
        keygen = KeyGenerator.getInstance();

        System.out.println("key = " + keygen.getNextKey());
        System.out.println("key = " + keygen.getNextKey());
        System.out.println("key = " + keygen.getNextKey());
    }

}