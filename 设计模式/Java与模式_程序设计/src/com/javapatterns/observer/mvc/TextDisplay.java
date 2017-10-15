package com.javapatterns.observer.mvc;

/*
* This example is from javareference.com
* for more information visit,
* http://www.javareference.com
*/

//import statements
/**
* TextDisplay.java
* This class demonstrates the use of Observer Pattern
* This class represents the Text View Object and implements
* Observer interface
*
* @author Rahul Sapkal(rahul@javareference.com)
*/
public class TextDisplay extends TextField implements Observer
{
    private String m_text;
    
    //Constructor
    public TextDisplay(String text)
    {
        super(text);
        
        m_text = text;
    }
    
    //update method called when notified by the Observable
    public void update(Observable o, Object arg)
    {
        setText(m_text + ((Data)o).getData());
    }
}


