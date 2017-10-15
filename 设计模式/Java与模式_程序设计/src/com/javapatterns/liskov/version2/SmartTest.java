package com.javapatterns.liskov.version2;

public class SmartTest
{
    public void resize(Rectangle r)
    {
		while (r.getHeight() <= r.getWidth() )
        {
			r.setWidth(r.getWidth() + 1);
        }
    }

    /** @link dependency */
    /*#Rectangle lnkRectangle;*/
}
