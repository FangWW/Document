package com.javapatterns.observerawt.mouse3;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConcreteListener extends MouseAdapter
{
	ConcreteListener()
    {
	}

	public void mouseClicked(MouseEvent e)
    {
		System.out.println(e.getWhen());
	}
}
