package com.javapatterns.builder.simplified2;

public class Builder
{
    /**
     * @directed 
     */
    private Product product = new Product();

    public void buildPart1()
    {
        // Write your code here
    }

    public void buildPart2()
    {
        // Write your code here
    }

    public Product retrieveResult()
    {
        return product;
    }

    public void construct()
    {
		buildPart1();
		buildPart2();

        Product product = retrieveResult();
    }
}
