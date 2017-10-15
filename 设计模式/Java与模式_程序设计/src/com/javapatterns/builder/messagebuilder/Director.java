package com.javapatterns.builder.messagebuilder;

public class Director
{
    /**
     * @link aggregation 
     */
    Builder builder;

    public Director(Builder builder)
    {
        this.builder = builder;
    }

    public void construct(String toAddress, String fromAddress)
    {
        this.builder.buildSubject();
        this.builder.buildBody();
        this.builder.buildTo( toAddress );
        this.builder.buildFrom( fromAddress );
        this.builder.buildSendDate();
        this.builder.sendMessage();
    }
}
