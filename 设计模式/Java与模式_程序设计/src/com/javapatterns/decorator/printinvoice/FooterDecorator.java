package com.javapatterns.decorator.printinvoice;

public class FooterDecorator extends OrderDecorator
{
    public FooterDecorator(Order anOrder)
    {
        super(anOrder);
    }

    public void print()
    {
        super.order.print();
        printFooter();
    }

    private void printFooter()
    {

        System.out.println("========================================================");
        System.out.println("Total\t\t\t\t" +
            formatCurrency(super.order.getGrandTotal()));
    }

}
