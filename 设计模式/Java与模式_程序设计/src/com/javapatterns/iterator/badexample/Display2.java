package com.javapatterns.iterator.badexample;

import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

public class Display2
{
    public List itemList = new LinkedList();

    public Display2()
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
