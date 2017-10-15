package com.javapatterns.observerawt.mouse4;

import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConcreteSubject extends Frame
{
    public ConcreteSubject()
    {
    }

	public static void main(String[] argv)
    {
		ConcreteSubject s = new ConcreteSubject();

		s.setBounds(100, 100, 100 , 100);
		s.addMouseListener( new MouseAdapter() {

			public void mouseClicked(MouseEvent e)
		    {
				System.out.println(e.getWhen());
			}
        }
        );
        s.show();
	}

    /** @link dependency */
    /*#MouseAdapter lnkMouseAdapter;*/
}
