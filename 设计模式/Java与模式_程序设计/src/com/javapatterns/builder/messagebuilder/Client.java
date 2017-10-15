package com.javapatterns.builder.messagebuilder;

public class Client
{
    private static Builder builder;
    private static Director director;

	static public void main(String[] args)
	{ 
		builder = new WelcomeBuilder();
		
		director = new Director(builder);
		director.construct( "hong.yan@citi.com", "jeff.yan@citicorp.com" ); 
	}
}
