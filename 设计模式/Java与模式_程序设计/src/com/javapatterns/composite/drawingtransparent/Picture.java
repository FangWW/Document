package com.javapatterns.composite.drawingtransparent;

import java.util.Vector;

public class Picture extends Graphics 
{
    private Vector list = new Vector(10);

    public void draw()
    {
        for (int i = 0 ; i < list.size(); i++)
        {
			Graphics g = (Graphics) list.get(i);

            g.draw();
        }
    }

    public void add(Graphics g)
    {
        list.add(g);
    }

    public void remove(Graphics g)
    {
        list.remove(g);
    }

    public Graphics getChild(int i)
    {
        return (Graphics) list.get(i);
    }
}

