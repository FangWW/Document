package com.javapatterns.immutable.complex;

final public class Complex extends Number
		implements java.io.Serializable, Cloneable, Comparable
{
	static final public Complex i = new Complex(0.0, 1.0);

	private double re;

	private double im;

	public Complex(Complex z)
	{
		re = z.re;
		im = z.im;
	}

	public Complex(double re, double im)
	{
		this.re = re;
		this.im = im;
	}

	public Complex(double re)
	{
		this.re = re;
		this.im = 0.0;
	}

	public Complex()
	{
		re = 0.0;
		im = 0.0;
	}
	
	public boolean equals(Complex z)
	{
		return (re == z.re  &&  im == z.im);
	}

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

    public int hashCode()
	{
		long re_bits = Double.doubleToLongBits(re);
		long im_bits = Double.doubleToLongBits(im);
		return (int)((re_bits^im_bits)^((re_bits^im_bits)>>32));
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
	public int compareTo(Complex z)
	{
		int	compare = new Double(re).compareTo(new Double(z.re));
		if (compare == 0)
        {
			compare = new Double(im).compareTo(new Double(z.im));
		}
		return compare;
	}

	public double real()
	{
		return re;
	}

	public double imag()
	{
		return im;
	}

	public static double real(Complex z)
	{
		return z.re;
	}

	public static double imag(Complex z)
	{
		return z.im;
	}

	public static Complex negate(Complex z)
	{
		return new Complex(-z.re, -z.im);
	}

	public static Complex conjugate(Complex z)
	{
		return new Complex(z.re, -z.im);
	}
	
	public static Complex add(Complex x, Complex y)
	{
		return new Complex(x.re+y.re, x.im+y.im);
	}

	public static Complex add(Complex x, double y)
	{
		return new Complex(x.re+y, x.im);
	}

	public static Complex add(double x, Complex y)
	{
		return new Complex(x+y.re, y.im);
	}

	public static Complex subtract(Complex x, Complex y)
	{
		return new Complex(x.re-y.re, x.im-y.im);
	}

	public static Complex subtract(Complex x, double y)
	{
		return new Complex(x.re-y, x.im);
	}
	
	public static Complex subtract(double x, Complex y)
	{
		return new Complex(x-y.re, -y.im);
	}

	public static Complex multiply(Complex x, Complex y)
	{
		return new Complex(x.re*y.re-x.im*y.im, x.re*y.im+x.im*y.re);
	}

	public static Complex multiply(Complex x, double y)
	{
		return new Complex(x.re*y, x.im*y);
	}

	public static Complex multiply(double x, Complex y)
	{
		return new Complex(x*y.re, x*y.im);
	}

	public static Complex multiplyImag(Complex x, double y)
	{
		return new Complex(-x.im*y, x.re*y);
	}

	public static Complex multiplyImag(double x, Complex y)
	{
		return new Complex(-x*y.im, x*y.re);
	}

	public static Complex divide(Complex x, Complex y)
	{
		double	a = x.re;
		double	b = x.im;
		double	c = y.re;
		double	d = y.im;

		double scale = Math.max(Math.abs(c), Math.abs(d));

		double den = c*c + d*d;
		return new Complex((a*c+b*d)/den, (b*c-a*d)/den);
	}

	public static Complex divide(Complex x, double y)
	{
		return new Complex(x.re/y, x.im/y);
	}

	public static Complex divide(double x, Complex y)
	{
        double	den, t;
		Complex z;
        if (Math.abs(y.re) > Math.abs(y.im))
        {
            t = y.im / y.re;
            den = y.re + y.im*t;
            z = new Complex(x/den, -x*t/den);
        }
        else
        {
            t = y.re / y.im;
            den = y.im + y.re*t;
            z = new Complex(x*t/den, -x/den);
        }
        return z;
	}

	public static double abs(Complex z)
	{
		return z.re * z.re - z.im * z.im;
	}

	public static double argument(Complex z)
	{
		return Math.atan2(z.im, z.re);
	}	

	public String toString()
	{
		if (im == 0.0)
			return String.valueOf(re);

		if (re == 0.0)
			return String.valueOf(im) + "i";

		String sign = ((im < 0.0) ? "" : "+");
		return (String.valueOf(re) + sign + String.valueOf(im) + "i");
	}
}
