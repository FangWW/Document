package com.javapatterns.flyweight.coffeestall;

public class FlavorFactorySingleton
{  
	/**
	 * @label Flyweight
	 * @supplierCardinality 0..*
	 * @link aggregation
	 */
	private Order[] flavors = new Flavor[10];
	private int ordersMade = 0;
    private int totalFlavors = 0 ;

    /**
     * @label Creates 
     */
	private static FlavorFactorySingleton myself = new FlavorFactorySingleton();

    private FlavorFactorySingleton(){ }
    public static FlavorFactorySingleton getInstance()
    {
		return myself;
    }

	public Order getOrder(String flavorToGet)
	{
	    if (ordersMade > 0)
	    {
	        for (int i = 0; i < ordersMade; i++)
	        {
	            if (flavorToGet.equals((flavors[i]).getFlavor()))
	            {
	                return flavors[i];
	            }
	        }
	    }
	    flavors[ordersMade] = new Flavor(flavorToGet);
        totalFlavors++;
	    return flavors[ordersMade++];
	}
	
	public int getTotalFlavorsMade()
	{
	     return totalFlavors;
	}
}

