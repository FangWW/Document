package com.javapatterns.immutable.point;

public class Point implements java.io.Serializable {
    public int x;
    public int y;

    public Point(int x, int y)
    {
    	this.x = x;
        this.y = y;
    }

    public Point()
    {
		this(0, 0);
    }

    public Point(Point p)
    {
		this(p.x, p.y);
    }

    /**
     * Returns the X coordinate of the point in double precision.
     */
    public double getX()
    {
		return x;
    }

    /**
     * Returns the Y coordinate of the point in double precision.
     */
    public double getY()
    {
		return y;
    }

    /**
     * Returns the location of this point.
     * This method is included for completeness, to parallel the
     * <code>getLocation</code> method of <code>Component</code>.
     * @return      a copy of this point, at the same location.
     * @see         java.awt.Component#getLocation
     * @see         java.awt.Point#setLocation(java.awt.Point)
     * @see         java.awt.Point#setLocation(int, int)
     * @since       JDK1.1
     */
    public Point getLocation()
    {
		return new Point(x, y);
    }	

    /**
     * Sets the location of the point to the specificed location.
     * This method is included for completeness, to parallel the
     * <code>setLocation</code> method of <code>Component</code>.
     * @param       p  a point, the new location for this point.
     * @see         java.awt.Component#setLocation(java.awt.Point)
     * @see         java.awt.Point#getLocation
     * @since       JDK1.1
     */
    public Point setLocation(Point p)
    {
		return new Point(p.x, p.y);
    }	

    /**
     * Changes the point to have the specificed location.
     * <p>
     * This method is included for completeness, to parallel the
     * <code>setLocation</code> method of <code>Component</code>.
     * Its behavior is identical with <code>move(int,&nbsp;int)</code>.
     * @param       x  the <i>x</i> coordinate of the new location.
     * @param       y  the <i>y</i> coordinate of the new location.
     * @see         java.awt.Component#setLocation(int, int)
     * @see         java.awt.Point#getLocation
     * @see         java.awt.Point#move(int, int)
     * @since       JDK1.1
     */
    public Point setLocation(int x, int y)
    {
		return new Point(x, y);
    }	

    /**
     * Sets the location of this point to the specified float coordinates.
     */
    public Point setLocation(double x, double y)
    {
		return new Point((int) Math.round(x), (int) Math.round(y));
    }

    /**
     * Moves this point to the specificed location in the 
     * (<i>x</i>,&nbsp;<i>y</i>) coordinate plane. This method
     * is identical with <code>setLocation(int,&nbsp;int)</code>.
     * @param       x  the <i>x</i> coordinate of the new location.
     * @param       y  the <i>y</i> coordinate of the new location.
     * @see         java.awt.Component#setLocation(int, int)
     */
    public Point move(int x, int y)
    {
        return new Point(x, y);
    }	

    /**
     * Translates this point, at location (<i>x</i>,&nbsp;<i>y</i>), 
     * by <code>dx</code> along the <i>x</i> axis and <code>dy</code> 
     * along the <i>y</i> axis so that it now represents the point 
     * (<code>x</code>&nbsp;<code>+</code>&nbsp;<code>dx</code>, 
     * <code>y</code>&nbsp;<code>+</code>&nbsp;<code>dy</code>). 
     * @param       x   the distance to move this point 
     *                            along the <i>x</i> axis.
     * @param       y    the distance to move this point 
     *                            along the <i>y</i> axis.
     */
    public Point translate(int x, int y)
    {
		return new Point(this.x + x, this.y + y);
    }	

    /**
     * Determines whether an instance of <code>Point2D</code> is equal
     * to this point.  Two instances of <code>Point2D</code> are equal if
     * the values of their <code>x</code> and <code>y</code> member 
     * fields, representing their position in the coordinate space, are
     * the same.
     * @param      obj   an object to be compared with this point.
     * @return     <code>true</code> if the object to be compared is
     *                     an instance of <code>Point2D</code> and has
     *                     the same values; <code>false</code> otherwise.
     */
    public boolean equals(Object obj)
    {
		if (obj instanceof Point)
	    {
		    Point pt = (Point)obj;
		    return (x == pt.x) && (y == pt.y);
		}
		return super.equals(obj);
    }

    /**
     * Returns a string representation of this point and its location 
     * in the (<i>x</i>,&nbsp;<i>y</i>) coordinate space. This method is 
     * intended to be used only for debugging purposes, and the content 
     * and format of the returned string may vary between implementations. 
     * The returned string may be empty but may not be <code>null</code>.
     * 
     * @return  a string representation of this point.
     */
    public String toString()
    {
		return getClass().getName() + "[x=" + x + ",y=" + y + "]";
    }
}
