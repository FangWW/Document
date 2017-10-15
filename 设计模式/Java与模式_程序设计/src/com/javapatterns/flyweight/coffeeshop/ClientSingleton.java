package com.javapatterns.flyweight.coffeeshop;

public class ClientSingleton
{
    /**
     * @label Creates
     * @supplierCardinality 0..* 
     */
	private static Order[] flavors = new Flavor[100];

	private static int ordersMade = 0;


    /** @link dependency */
    /*#Table lnkTable;*/

    /**
     * @directed 
     */
    private static FlavorFactorySingleton flavorFactory;
	 
	private static void takeOrders(String aFlavor)
	{
	    flavors[ordersMade++] = flavorFactory.getOrder(aFlavor);
	}
	 
	public static void main(String[] args) 
	{
	    flavorFactory = FlavorFactorySingleton.getInstance();
	    
	    takeOrders("Black Coffee");
	    takeOrders("Capucino");
	    takeOrders("Espresso");
	    takeOrders("Espresso");
	    takeOrders("Capucino");
	    takeOrders("Capucino");
	    takeOrders("Black Coffee");
	    takeOrders("Espresso");
	    takeOrders("Capucino");
	    takeOrders("Black Coffee");
	    takeOrders("Espresso");

	    for (int i = 0; i < ordersMade; i++)
	    {
	        flavors[i].serve(new Table(i));
	    }

	    System.out.println("\nTotal teaFlavor objects made: " +
            flavorFactory.getTotalFlavorsMade());
	}
}