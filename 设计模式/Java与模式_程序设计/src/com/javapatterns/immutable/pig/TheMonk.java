package com.javapatterns.immutable.pig;

public class TheMonk
{
    private static Pig bajie;
    public static void main(String[] args)
    {
        System.out.println("==============starting==============");
    	bajie = new Bajie();

        bajie.speak();

        bajie.walk();
        System.out.println("==============finishing==============");

        System.out.println("==============starting==============");
    	bajie = new Bajie("Hello");

        bajie.speak();

        bajie.walk();
        System.out.println("==============finishing==============");



    }
}
