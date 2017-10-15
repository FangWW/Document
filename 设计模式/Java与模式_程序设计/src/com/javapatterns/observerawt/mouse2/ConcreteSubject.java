package com.javapatterns.observerawt.mouse2;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConcreteSubject extends Frame
{
    public ConcreteSubject()
    {
    }

	public static void main(String[] argv)
    {
		ConcreteSubject s = new ConcreteSubject();

		s.setBounds(100, 100, 100 , 100);
		s.addMouseListener( new MouseListener() {

			public void mouseClicked(MouseEvent e)
		    {
				System.out.println(e.getWhen());
			}
		
		    public void mousePressed(MouseEvent e){}
		
		    public void mouseReleased(MouseEvent e){}
		
		    public void mouseEntered(MouseEvent e){}
		
		    public void mouseExited(MouseEvent e){}

        }
        );
        s.show();
	}

    /**
     * @link aggregation
     * @directed
     * @supplierCardinality 0..* 
     */
    private MouseListener lnkMouseListener;
}
