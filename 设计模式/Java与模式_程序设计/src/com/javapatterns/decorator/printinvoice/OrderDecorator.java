package com.javapatterns.decorator.printinvoice;

abstract public class OrderDecorator extends Order
{
    /**
     * @link aggregation
     * @directed
     * @supplierCardinality 1
     * @clientCardinality 0..1
     */
    protected Order order;

    public OrderDecorator(Order order)
    {
        this.order = order;
        this.setSalesDate( order.getSalesDate() );
        this.setCustomerName( order.getCustomerName() );
    }

    public void print()
    {
		super.print();
    }
}
