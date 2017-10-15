package com.javapatterns.templatemethod;

public class ConcreteClass extends AbstractClass 
{
    public void doOperation1()
    {
        //write your code here
        System.out.println("doOperation1();");
    }

    public void doOperation2()
    {
        //The following should not happen:
        //doOperation3();

        //write your code here
        System.out.println("doOperation2();");
    }
}
