package com.javapatterns.keygen.ver2;

public class KeyGenerator
{
    /**
     * @label creates
     */
	private static KeyGenerator keygen =
        new KeyGenerator();

    private int key ;
    private static final int POOL_SIZE = 20;

    private KeyGenerator() {}

    public static KeyGenerator getInstance()
    {
    	return keygen;
    }

    public synchronized int getNextKey()
    {
		return getNextKeyFromDB();
    }

    private int getNextKeyFromDB()
    {
		String sql1 = "UPDATE KeyTable SET keyValue = keyValue + 1 ";

        String sql2 = "SELECT keyValue FROM KeyTable";

        //execute the update SQL
        //run the SELECT query
        //mockup
		return 1000;
    }

}
