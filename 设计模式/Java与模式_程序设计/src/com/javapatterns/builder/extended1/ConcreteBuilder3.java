package com.javapatterns.builder.extended1;

public class ConcreteBuilder3 extends Builder
{
    /**
     * @label Creates
     */
    private Product3 product = new Product3() ;

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
