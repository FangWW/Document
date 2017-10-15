package com.javapatterns.doubledispatch.mixed;

public class SubA extends Super 
{
    public void operation(SubA a)
    {
        System.out.println("This is SubA.operation(SubA);");
    }

    public void operation(SubB b)
    {
        System.out.println("This is SubA.operation(SubB);");
    }

    public void operation(Super s)
    {
        System.out.println("This is SubA.operation(Super);");
    }
}

