package com.javapatterns.doubledispatch;

import java.awt.Canvas;

public class Client
{
    private static Point p ;
    private static Point pc;

    public static void main(String[] args)
    {
    	p = new Point();
        pc = new ColorPoint();

        //static multi-dispatch
        p.translate(5);  	// one int version
        p.translate(1,2);   // two int version

        //dynamic uni-dispatch
        Canvas aCanvas = new Canvas();
        p.draw(aCanvas); 	//Point.draw()
        pc.draw(aCanvas);   //ColorPoint.draw();

    }

}
