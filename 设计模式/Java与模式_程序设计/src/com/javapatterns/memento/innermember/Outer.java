package com.javapatterns.memento.innermember;

public class Outer
{

    public Outer()
    {
		System.out.println("Outer has been instantiated.");
    }

    public Inner instantiateInner()
    {
		return new Inner();
    }

    public class Inner
    {
		public Inner()
        {
			System.out.println("Inner has been instantiated.");
        }

        public void testThis()
        {
			System.out.println("This is this from Inner: " + this);
			System.out.println("This is Outer.this from Inner: " + Outer.this);
        }
    }
}

