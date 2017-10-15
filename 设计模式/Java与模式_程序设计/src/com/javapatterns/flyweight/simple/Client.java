package com.javapatterns.flyweight.simple;

public class Client
{
    private static FlyweightFactory factory;

    static public void main(String[] args)
	{ 
		factory = new FlyweightFactory();

		Flyweight fly = factory.factory(new Character('a'));
		fly.operation("First Call");
		
		fly = factory.factory(new Character('b'));
		fly.operation("Second Call");
		
		fly = factory.factory(new Character('a'));
		fly.operation("Third Call");
		
		// intrinsic Flyweight
		factory.checkFlyweight();
	} 
}
