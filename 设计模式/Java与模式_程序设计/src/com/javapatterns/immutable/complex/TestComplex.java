package com.javapatterns.immutable.complex;

public class TestComplex
{
    public static void main(String[] args)
    {
        Complex c1 = new Complex(10,20);

        Complex c2 = new Complex(0, 1);

        Complex res = Complex.multiply(c1,  c2);

        System.out.println("Real part = " + res.real() );
        System.out.println("Imaginary part = " + res.imag() );
    }

    /** @link dependency */
    /*#Complex lnkComplex;*/
}
