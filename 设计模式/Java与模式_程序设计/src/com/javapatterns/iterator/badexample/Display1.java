package com.javapatterns.iterator.badexample;

import java.awt.Panel;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Display1 extends Panel
{
    public List itemList = new LinkedList();

    public Display1()
    {
        //write your code here
    }

    public void initList(Vector opts)
    {
        Enumeration enu = opts.elements();

        while(enu.hasMoreElements())
        {
            String item = (String) enu.nextElement();
            itemList.add(item);
        }
    }
}
