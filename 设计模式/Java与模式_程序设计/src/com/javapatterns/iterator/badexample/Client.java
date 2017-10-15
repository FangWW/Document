package com.javapatterns.iterator.badexample;

import java.util.Hashtable;
import java.util.Vector;

public class Client
{
    private Vector v = new Vector();
    private Hashtable ht = new Hashtable();
    private Display disp = new Display();

    public void test()
    {
        System.out.println("Before testing...");
        disp.initList( v );
        System.out.println("After testing...");
    }
    public static void main(String[] args)
    {
        Client client = new Client();

        client.test();
    }
}
