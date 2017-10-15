package com.javapatterns.flyweight.composite;

public class ClientSingleton
{
    private static FlyweightFactorySingleton factory;

    public static void main(String[] args)
	{ 
		factory = FlyweightFactorySingleton.getInstance();

		Flyweight fly;
/*        fly = factory.factory(new Character('a'));
		fly.operation();
		
		fly = factory.factory(new Character('b'));
		fly.operation();
		
		fly = factory.factory(new Character('a'));
		fly.operation();
*/
        fly = factory.factory("abc");
        fly.operation("Composite Call");
		
// intrinsic Flyweight
		factory.checkFlyweight();
	} 
}
