package com.javapatterns.templatemethod;

abstract public class AbstractClass
{
    public void TemplateMethod()
    {
        doOperation1();

        doOperation2();

        doOperation3();
    }

    protected abstract void doOperation1();

    protected abstract void doOperation2();

    private final void doOperation3()
    {
        //do something
    }
}
