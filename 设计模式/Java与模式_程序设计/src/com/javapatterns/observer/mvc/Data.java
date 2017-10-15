package com.javapatterns.observer.mvc;

/*
* This example is from javareference.com
* for more information visit,
* http://www.javareference.com
*/

//import statements
/**
* Data.java
* This class demonstrates the use of Observer Pattern
* This class represents the Data Object and extends the Observable
*
* @author Rahul Sapkal(rahul@javareference.com)
*/
public class Data extends Observable
{
    private int m_value;
        
    //Constructor
    public Data(int value)
    {
        m_value = value;
    }
    
    //set Data
    public void setData(int value)
    {
        m_value = value;
        
        //Make sure to set the hasChanged flas to true
        //otherwise the notifyObservers() call will not
        //notify the Observers
        setChanged();
    }

    //get Data
    public int getData()
    {
        return m_value;
    }
}

