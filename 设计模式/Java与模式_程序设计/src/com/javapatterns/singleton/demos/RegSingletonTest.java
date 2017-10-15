package com.javapatterns.singleton.demos;


public class RegSingletonTest
{
    public static void main(String[] args)
    {
        //(1) Test eager
        //System.out.println( EagerSingleton.getInstance() );

        //RegSingletonTest test = new RegSingletonTest();

        System.out.println( RegSingleton.getInstance("com.javapatterns.singleton.demos.RegSingleton").about() )   ;
        System.out.println( RegSingleton.getInstance(null).about() )   ;

        System.out.println( RegSingleton.getInstance("com.javapatterns.singleton.demos.RegSingletonChild").about() )   ;
        System.out.println( RegSingletonChild.getInstance().about())   ;

    }
}
