package com.javapatterns.immutable.pig;

public class Pig
{
    public Pig()
    {
        System.out.println("I am a pig.");
    }

    public Pig(String greeting)
    {
        System.out.println("Hello from a pig");
    }

    public static void speak()
    {
        System.out.println("!@#$%^&!");
    }

    public void walk()
    {
        System.out.println("I walk a pig's walk");
    }
}
