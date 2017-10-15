package com.javapatterns.builder.extended;

public class Director
{
    /**
     * @link aggregation
     * @directed
     * @clientRole builder*/
    private Builder builder;

    public Director(Builder builder)
    {
        this.builder = builder;
    }

    public void construct()
    {
		builder = new ConcreteBuilder1();

        builder.buildPart1();

        builder.buildPart2();

        builder.retrieveResult();

        //continue with other code
    }
}
