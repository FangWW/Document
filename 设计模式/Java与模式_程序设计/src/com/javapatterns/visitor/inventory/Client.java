package com.javapatterns.visitor.inventory;

public class Client
{
	private static PriceVisitor pv;
    private static InventoryVisitor iv;
    private static Equipment equip;

	public static void main(String[] argv)
	{
		equip = new Pc();

		pv = new PriceVisitor();
		equip.accept(pv);
		System.out.println("Price: " + pv.value() );

		System.out.println("\n");
		iv = new InventoryVisitor();
		equip.accept(iv);
		System.out.println("Number of parts: " + iv.size());

	}
}
