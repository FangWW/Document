package com.javapatterns.builder;

public class ConcreteBuilder extends Builder
{
    /**
     * @label Creates
     */
    private Product product = new Product() ;

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
