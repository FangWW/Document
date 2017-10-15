package com.javapatterns.builder.simplified1;

abstract public class Director
{

    /**
     * @link aggregation
     * @directed
     * @clientCardinality 1
     * @supplierCardinality 1
     * @clientRole builder*/
    private ConcreteBuilder builder;

    public void construct()
    {
		builder.buildPart1();
		builder.buildPart2();

        Product product = builder.getResult();
    }
}
