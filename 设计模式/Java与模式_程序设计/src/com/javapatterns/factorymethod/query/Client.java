package com.javapatterns.factorymethod.query;

import java.sql.ResultSet;

public class Client
{
    /**
     * @directed
     * @link aggregation 
     */
    private static QueryRunner runner;

    public static void main(String[] args)
        throws Exception
    {
		runner = new SybaseQueryRunner();

        ResultSet rs = runner.run();
    }

}
