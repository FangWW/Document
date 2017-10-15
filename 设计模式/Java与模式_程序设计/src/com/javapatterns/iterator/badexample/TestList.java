package com.javapatterns.iterator.badexample;

import java.util.Hashtable;
import java.util.Vector;

public class TestList
{
    private Vector v = new Vector();
    private Hashtable ht = new Hashtable();
    private Display1 disp1 = new Display1();
    private Display2 disp2 = new Display2();

    public void test1()
    {
        disp1.initList( v );
    }

    public void test2()
    {
        disp2.initList( v.elements() );
        disp2.initList( ht.elements() );
    }
}
