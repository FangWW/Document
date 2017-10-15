package com.javapatterns.doubledispatch.mixed;

public class SubB extends Super 
{
    public void operation(SubB b)
    {
        System.out.println("This is SubB.operation(SubB);");
    }

    public void operation(SubA a)
    {
        System.out.println("This is SubB.operation(SubA);");
    }

    public void operation(Super s)
    {
        System.out.println("This is SubA.operation(Super);");
    }
}

