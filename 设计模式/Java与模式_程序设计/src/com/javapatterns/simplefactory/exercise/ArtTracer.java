package com.javapatterns.simplefactory.exercise;

public class ArtTracer
{
    public static Shape factory(String which) throws BadShapeException
    {
        if (which.equalsIgnoreCase("circle"))
        {
            return new Circle();
        }
        else if (which.equalsIgnoreCase("square"))
        {
            return new Square();
        }
        else if (which.equalsIgnoreCase("triangle"))
        {
            return new Triangle();
    	}
        else
        {
            throw new BadShapeException(which);
        }
    }

    /** @link dependency 
     * @label Creates*/
    /*#Shape lnkShape;*/

    /** @link dependency 
     * @label Raises Exception*/
    /*#BadShapeException lnkBadShapeException;*/
}
