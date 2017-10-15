package com.javapatterns.builder.simplified2;

public class Client
{
    private static Builder builder;

	static public void main(String[] args)
	{ 
		builder = new Builder();
        builder.construct();

        Product product = builder.retrieveResult();

        //continue here
	}
}
