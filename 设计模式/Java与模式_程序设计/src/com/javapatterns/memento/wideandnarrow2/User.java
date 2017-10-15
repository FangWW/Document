package com.javapatterns.memento.wideandnarrow2;

public class User
{
    class ConcreteClass implements Narrow
    {
        private void operation1()
        {
    		System.out.println("operation1()");
        }

        private void operation2()
        {
    		System.out.println("operation2()");
        }
    }

    public Narrow getConcreteClass()
    {
		return (Narrow) new ConcreteClass();
    }
}
