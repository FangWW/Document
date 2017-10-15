package com.javapatterns.strategy.booksales;

public class NoDiscountStrategy extends DiscountStrategy
{
    private double price = 0.0;
    private int copies = 0;

    public NoDiscountStrategy(double price, int copies)
    {
        this.price = price;
        this.copies = copies;
    }

    public double calculateDiscount()
    {
		return 0.0;
    }
}
