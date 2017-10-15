package com.javapatterns.iterator.goodexample;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public class Display
{
    public List itemList = new LinkedList();

    public Display()
    {
        //write your code here
    }

    public void initList(Enumeration enu)
    {

        while(enu.hasMoreElements())
        {
            String item = (String) enu.nextElement();
            itemList.add(item);
        }
    }
}
