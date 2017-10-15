package com.javapatterns.immutable.complex;

final public class Complex1 extends Number
		implements java.io.Serializable, Cloneable, Comparable
{
	/**	
	 *	The imaginary unit.
	 */
	static final public Complex1 i = new Complex1(0.0, 1.0);

	/**	
	 *	 Real part of the Complex.
	 */
	private double re;

	/**
	 *	 Imaginary part of the Complex.
	 */
	private double im;

	/**
	 *  String used in converting Complex to String.
	 */
	public static String suffix = "i";
	

	/** 
	 *	Constructs a Complex equal to the argument.
	 *	@param	z	A Complex object
	 *			If z is null then a NullPointerException is thrown.
	 */
	public Complex1(Complex1 z)
	{
		re = z.re;
		im = z.im;
	}


	/** 
	 *	Constructs a Complex with real and imaginary parts given
	 *	by the input arguments.
	 *	@param	re	A double value equal to the real part of the Complex object.
	 *	@param	im	A double value equal to the imaginary part of the Complex object.
	 */
	public Complex1(double re, double im)
	{
		this.re = re;
		this.im = im;
	}


	/** 
	 *	Constructs a Complex with a zero imaginary part. 
	 *	@param	re	A double value equal to the real part of the Complex object.
	 */
	public Complex1(double re)
	{
		this.re = re;
		this.im = 0.0;
	}


	/**
	 *	Constructs a Complex equal to zero.
	 */
	public Complex1()
	{
		re = 0.0;
		im = 0.0;
	}
	
	/**
	 *	Compares with another Complex. 
     *	<p><em>Note: To be useful in hashtables this method
     *	considers two NaN double values to be equal. This
     *	is not according to IEEE specification.</em>
	 *	@param	z	A Complex object.
	 *	@return True if the real and imaginary parts of this object
	 *			are equal to their counterparts in the argument; false, otherwise.
	 */
	public boolean equals(Complex1 z)
	{
		return (re == z.re  &&  im == z.im);
	}

   
	/**
     *	Compares this object against the specified object.
     *	@param	obj	The object to compare with.
     *	@return True if the objects are the same; false otherwise.
     */
    public boolean equals(Object obj)
	{
		if (obj == null)
        {
			return false;
		}
        else if (obj instanceof Complex)
        {
			return equals((Complex)obj);
		}
        else
        {
			return false;
		}
	}

    /**
     *	Returns a hashcode for this Complex.
     *	@return  A hash code value for this object. 
     */
    public int hashCode()
	{
		long re_bits = Double.doubleToLongBits(re);
		long im_bits = Double.doubleToLongBits(im);
		return (int)((re_bits^im_bits)^((re_bits^im_bits)>>32));
    }	 

	/**
	 *	Returns the value of the real part as a short. 
	 */
	public short shortValue() 
	{
		return (short)re;
	}

	/**
	 *	Returns the real part of a Complex object. 
	 *	@return	The real part of z.
	 */
	public double real()
	{
		return re;
	}

	/** 
	 *	Returns the imaginary part of a Complex object. 
	 *	@param	z	A Complex object.
	 *	@return	The imaginary part of z.
	 */
	public double imag()
	{
		return im;
	}

 	/**
	 *	Returns the value of the real part as an int. 
	 */
	public int intValue() 
	{
		return (int)re;
	}

	/** 
	 *	Returns the value of the real part as a byte. 
	 */
	public byte byteValue()
	{
		return (byte)re;
	}

	/**
	 *	Returns the value of the real part as a long. 
	 */
	public long longValue() 
	{
		return (long)re;
	}

	/**
	 *	Returns the value of the real part as a float. 
	 */       
	public float floatValue()
	{
		return (float)re;
	}

	/**
	 *	Returns the value of the real part as a double. 
	 */
	public double doubleValue()
	{
		return re;
	}

	/**
	 *	Compares this Complex to another Object. If the Object is a Complex,
	 *	this function behaves like compareTo(Complex). Otherwise, it throws
	 *	a ClassCastException (as Complex objects are comparable only to other
	 *	Complex objects).
	 */
	public int compareTo(Object obj)
	{
		return compareTo((Complex)obj);
	}

	/**
	 *	Compares two Complex objects.
	 *	<P>A lexagraphical ordering is used. First the real parts are compared
	 *	in the sense of Double.compareTo. If the real parts are unequal this
	 *	is the return value. If the return parts are equal then the comparison
	 *	of the imaginary parts is returned.
	 *	@return		The value 0 if z is  equal to this Complex;
	 *				a value less than 0 if this Complex is less than z;
	 *				and a value greater than 0 if this Complex is greater
	 *				than z.
	 */
	public int compareTo(Complex1 z)
	{
		int	compare = new Double(re).compareTo(new Double(z.re));
		if (compare == 0)
        {
			compare = new Double(im).compareTo(new Double(z.im));
		}
		return compare;
	}

	/** 
	 *	Returns the real part of a Complex object. 
	 *	@param	z	A Complex object.
	 *	@return	The real part of z.
	 */
	public static double real(Complex1 z)
	{
		return z.re;
	}

	/** 
	 *	Returns the imaginary part of a Complex object. 
	 *	@param	z	A Complex object.
	 *	@return	The imaginary part of z.
	 */
	public static double imag(Complex1 z)
	{
		return z.im;
	}


	/** 
	 *	Returns the negative of a Complex object, -z. 
	 *	@param	z	A Complex object.
	 *	@return A newly constructed Complex initialized to
	 *			the negative of the argument.
	 */
	public static Complex1 negate(Complex1 z)
	{
		return new Complex1(-z.re, -z.im);
	}

	
	/** 
	 *	Returns the complex conjugate of a Complex object.
	 *	@param	z	A Complex object.
	 *	@return A newly constructed Complex initialized to complex conjugate of z.
	 */
	public static Complex1 conjugate(Complex1 z)
	{
		return new Complex1(z.re, -z.im);
	}

	
	/** 
	 *	Returns the sum of two Complex objects, x+y.
	 *	@param	x	A Complex object.
	 *	@param	y	A Complex object.
	 *	@return A newly constructed Complex initialized to x+y.
	 */
	public static Complex1 add(Complex1 x, Complex1 y)
	{
		return new Complex1(x.re+y.re, x.im+y.im);
	}

	/** 
	 *	Returns the sum of a Complex and a double, x+y. 
	 *	@param	x	A Complex object.
	 *	@param	y	A double value.
	 *	@return A newly constructed Complex initialized to x+y.
	 */
	public static Complex1 add(Complex1 x, double y)
	{
		return new Complex1(x.re+y, x.im);
	}

	/** 
	 *	Returns the sum of a double and a Complex, x+y. 
	 *	@param	x	A double value.
	 *	@param	y	A Complex object.
	 *	@return A newly constructed Complex initialized to x+y.
	 */
	public static Complex1 add(double x, Complex1 y)
	{
		return new Complex1(x+y.re, y.im);
	}


	/** 
	 *	Returns the difference of two Complex objects, x-y.
	 *	@param	x	A Complex object.
	 *	@param	y	A Complex object.
	 *	@return A newly constructed Complex initialized to x-y.
	 */
	public static Complex1 subtract(Complex1 x, Complex1 y)
	{
		return new Complex1(x.re-y.re, x.im-y.im);
	}

	/** 
	 *	Returns the difference of a Complex object and a double, x-y. 
	 *	@param	x	A Complex object.
	 *	@param	y	A double value.
	 *	@return A newly constructed Complex initialized to x-y.
	 */
	public static Complex1 subtract(Complex1 x, double y)
	{
		return new Complex1(x.re-y, x.im);
	}
	
	/** 
	 *	Returns the difference of a double and a Complex object, x-y. 
	 *	@param	x	A double value.
	 *	@param	y	A Complex object.
	 *	@return A newly constructed Complex initialized to x-y..
	 */
	public static Complex1 subtract(double x, Complex1 y)
	{
		return new Complex1(x-y.re, -y.im);
	}


	/** 
	 *	Returns the product of two Complex objects, x*y. 
	 *	@param	x	A Complex object.
	 *	@param	y	A Complex object.
	 *	@return A newly constructed Complex initialized to x*y.
	 */
	public static Complex1 multiply(Complex1 x, Complex1 y)
	{
		return new Complex1(x.re*y.re-x.im*y.im, x.re*y.im+x.im*y.re);
	}

	/** 
	 *	Returns the product of a Complex object and a double, x*y. 
	 *	@param	x	A Complex object.
	 *	@param	y	A double value.
	 *	@return  A newly constructed Complex initialized to x*y.
	 */
	public static Complex1 multiply(Complex1 x, double y)
	{
		return new Complex1(x.re*y, x.im*y);
	}

	/** 
	 *	Returns the product of a double and a Complex object, x*y. 
	 *	@param	x	A double value.
	 *	@param	y	A Complex object.
	 *	@return A newly constructed Complex initialized to x*y.
	 */
	public static Complex1 multiply(double x, Complex1 y)
	{
		return new Complex1(x*y.re, x*y.im);
	}

	/** 
	 *	Returns the product of a Complex object and a pure
	 *	imaginary double, x * iy. 
	 *	@param	x	A Complex object.
	 *	@param	y	A pure imaginary, double value.
	 *	@return  A newly constructed Complex initialized to x * iy.
	 */
	public static Complex1 multiplyImag(Complex1 x, double y)
	{
		return new Complex1(-x.im*y, x.re*y);
	}

	/** 
	 *	Returns the product of a pure imaginary double and a
	 *	Complex object and a , ix * y. 
	 *	@param	x	A pure imaginary, double value.
	 *	@param	y	A Complex object.
	 *	@return  A newly constructed Complex initialized to ix * y.
	 */
	public static Complex1 multiplyImag(double x, Complex1 y)
	{
		return new Complex1(-x*y.im, x*y.re);
	}

	/** 
	 *	Returns Complex object divided by a Complex object, x/y. 
	 *	@param	x	The numerator, a Complex object.
	 *	@param	y	The denominator, a Complex object.
	 *	@return A newly constructed Complex initialized to x/y.
	 */
	public static Complex1 divide(Complex1 x, Complex1 y)
	{
		double	a = x.re;
		double	b = x.im;
		double	c = y.re;
		double	d = y.im;

		double scale = Math.max(Math.abs(c), Math.abs(d));

		double den = c*c + d*d;
		return new Complex1((a*c+b*d)/den, (b*c-a*d)/den);
	}

	
	/** 
	 *	Returns Complex object divided by a double, x/y.
	 *	@param	x	The numerator, a Complex object.
	 *	@param	y	The denominator, a double.
	 *	@return A newly constructed Complex initialized to x/y.
	 */
	public static Complex1 divide(Complex1 x, double y)
	{
		return new Complex1(x.re/y, x.im/y);
	}

	/** 
	 *	Returns a double divided by a Complex object, x/y. 
	 *	@param	x	A double value.
	 *	@param	y	The denominator, a Complex object.
	 *	@return A newly constructed Complex initialized to x/y.
	 */
	public static Complex1 divide(double x, Complex1 y)
	{
        double	den, t;
		Complex1 z;
        if (Math.abs(y.re) > Math.abs(y.im))
        {
            t = y.im / y.re;
            den = y.re + y.im*t;
            z = new Complex1(x/den, -x*t/den);
        }
        else
        {
            t = y.re / y.im;
            den = y.im + y.re*t;
            z = new Complex1(x*t/den, -x/den);
        }
        return z;
	}



	/** 
	 *	Returns the absolute value (modulus) of a Complex, |z|. 
	 *	@param	z	A Complex object.
	 *	@return A double value equal to the absolute value of the argument.
	 */
	public static double abs(Complex1 z)
	{
		return z.re * z.re - z.im * z.im;
	}


	/** 
	 *	Returns the argument (phase) of a Complex, in radians,
	 *	with a branch cut along the negative real axis.
	 *	@param	z	A Complex object.
	 *	@return A double value equal to the argument (or phase) of a Complex.
	 *			It is in the interval [-<img src="images/pi.gif">,<img src="images/pi.gif">].
	 */
	public static double argument(Complex1 z)
	{
		return Math.atan2(z.im, z.re);
	}	

	/** 
	 *	Returns a String representation for the specified Complex. 
	 *	@return A String representation for this object.
	 */
	public String toString()
	{
		if (im == 0.0)
			return String.valueOf(re);

		if (re == 0.0)
			return String.valueOf(im) + suffix;

		String sign = ((im < 0.0) ? "" : "+");
		return (String.valueOf(re) + sign + String.valueOf(im) + suffix);
	}
}
