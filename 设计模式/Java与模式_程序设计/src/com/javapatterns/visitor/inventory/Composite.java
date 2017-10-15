package com.javapatterns.visitor.inventory;

import java.util.Vector;

abstract class Composite extends Equipment
{
    /**
     * @directed
     * @link aggregation
     * @clientCardinality 1 
     */
	private Vector parts = new Vector(10);

	public Composite()
    {
    }

    public void add(Equipment equip)
    {
		parts.add(equip);
    }

	public double price()
    {
		double total=0;

		for (int i=0; i < parts.size(); i++)
        {
         	total += ((Equipment) parts.get(i)).price();
        }

        return total;
    }

	public void accept(Visitor v)
    {
		for (int i=0; i < parts.size(); i++)
        {
			((Equipment) parts.get(i)).accept(v);
        }
	}
}
