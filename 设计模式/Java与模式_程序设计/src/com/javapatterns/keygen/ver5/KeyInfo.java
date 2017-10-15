package com.javapatterns.keygen.ver5;

class KeyInfo
{
    private int keyMax;
    private int keyMin;
    private int nextKey;
    private int poolSize;
    private String keyName;

    public KeyInfo(int poolSize, String keyName)
    {
		this.poolSize = poolSize;
        this.keyName = keyName;
        retrieveFromDB();
    }

    public int getKeyMax()
    {
		return keyMax;
	}

    public int getKeyMin()
    {
		return keyMin;
	}

    public synchronized int getNextKey()
    {
        if (nextKey > keyMax)
        {
			retrieveFromDB();
        }
        return nextKey++;
    }

    private void retrieveFromDB()
    {
		String sql1 = "UPDATE KeyTable SET keyValue = keyValue + "
            + poolSize + " WHERE keyName = '"
            + keyName + "'";

        String sql2 = "SELECT keyValue FROM KeyTable WHERE KeyName = '"
            + keyName + "'";

        // execute the above queries in a transaction and commit it
        // assume the value returned is 1000

		int keyFromDB = 1000;

		keyMax = keyFromDB;
        keyMin = keyFromDB - poolSize + 1;
        nextKey = keyMin;
    }
}