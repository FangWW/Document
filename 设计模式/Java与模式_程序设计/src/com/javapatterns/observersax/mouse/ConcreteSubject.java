package com.javapatterns.observersax.mouse;

import java.awt.Frame;
import java.awt.event.MouseListener;

public class ConcreteSubject  extends Frame
{
    public ConcreteSubject()
    {
    }

	public static void main(String[] argv)
    {
		ConcreteSubject s = new ConcreteSubject();
        MouseListener m = new ConcreteListener();
		s.setBounds(100, 100, 100 , 100);
		s.addMouseListener(m);
        s.show();
	}
}
