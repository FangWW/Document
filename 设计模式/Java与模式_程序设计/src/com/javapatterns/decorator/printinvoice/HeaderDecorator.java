package com.javapatterns.decorator.printinvoice;

public class HeaderDecorator extends OrderDecorator
{
    public HeaderDecorator(Order anOrder)
    {
        super(anOrder);
    }

    public void print()
    {
        this.printHeader();
        super.order.print();
    }

    private void printHeader()
    {
        System.out.println("\t***\tI N V O I C E\t***\nXYZ Incorporated\nDate of Sale: "
            + order.getSalesDate());
        System.out.println("========================================================");
        System.out.println("Item\t\tUnits\tUnit Price\tSubtotal");
    }
}
