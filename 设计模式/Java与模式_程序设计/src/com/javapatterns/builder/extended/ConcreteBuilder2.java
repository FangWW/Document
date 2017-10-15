package com.javapatterns.builder.extended;

public class ConcreteBuilder2 extends Builder
{
    /**
     * @label Creates
     */
    private Product2 product = new Product2() ;

    public void buildPart1()
    {
        //build the first part of the product
    }

    public void buildPart2()
    {
        //build the second part of the product
    }

    public Product retrieveResult()
    {
		return product;
    }
}
