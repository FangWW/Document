package com.javapatterns.observerawt.mouse;

public class ConcreteListener implements MouseListener
{
	ConcreteListener()
    {
	}

	public void mouseClicked(MouseEvent e)
    {
		System.out.println(e.getWhen());
	}

    public void mousePressed(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}
}
