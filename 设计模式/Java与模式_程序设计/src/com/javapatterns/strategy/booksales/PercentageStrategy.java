package com.javapatterns.strategy.booksales;

public class PercentageStrategy extends DiscountStrategy
{
    private double percent = 0.0;
    private double price = 0.0;
    private int copies = 0;

    public PercentageStrategy(double price, int copies)
    {
        this.price = price;
        this.copies = copies;
    }

    public double getPercent()
    {
        return percent;
    }

    public void setPercent(double percent)
    {
        this.percent = percent;
    }

    public double calculateDiscount()
    {
		return copies * price * percent;
    }

}
