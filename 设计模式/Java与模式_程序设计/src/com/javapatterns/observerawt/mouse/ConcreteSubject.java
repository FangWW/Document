package com.javapatterns.observerawt.mouse;

import java.awt.Frame;
import java.awt.event.MouseListener;

public class ConcreteSubject  extends Frame
{
    /**
     * @link aggregation 
     */
    private static MouseListener m;

    public ConcreteSubject()
    {
    }

	public static void main(String[] argv)
    {
		ConcreteSubject s = new ConcreteSubject();
        m = new ConcreteListener();
		s.setBounds(100, 100, 100 , 100);
		s.addMouseListener(m);
        s.show();
	}
}
