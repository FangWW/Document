package com.javapatterns.immutable.pig;

public class Bajie extends Pig 
{
    public Bajie()
    {
        System.out.println("I am Ba Jie.");
    }

    public Bajie(String greeting)
    {
        System.out.println("Hello from Ba Jie");
    }

    public static void speak()
    {
        System.out.println("I am Ba Jie, a beast that talks");
    }

    public void walk()
    {
        System.out.println("I walk a man's walk");
    }
}
