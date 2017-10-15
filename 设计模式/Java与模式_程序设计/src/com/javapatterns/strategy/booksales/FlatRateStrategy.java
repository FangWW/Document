package com.javapatterns.strategy.booksales;

public class FlatRateStrategy extends DiscountStrategy
{
    private double amount;
    private double price = 0;
    private int copies = 0;

    public FlatRateStrategy(double price, int copies)
    {
        this.price = price;
        this.copies = copies;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public double calculateDiscount()
    {
		return copies * amount;
    }
}