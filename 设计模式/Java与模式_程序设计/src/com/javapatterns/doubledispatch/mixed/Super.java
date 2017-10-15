package com.javapatterns.doubledispatch.mixed;

public class Super
{
    public void operation(Super s)
    {
        System.out.println("This is Super.operation();");
        s.operation(this);
    }
}

