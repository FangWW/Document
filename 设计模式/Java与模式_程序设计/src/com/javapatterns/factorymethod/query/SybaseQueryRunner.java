package com.javapatterns.factorymethod.query;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SybaseQueryRunner extends QueryRunner
{
    public Connection createConnection()
    {
        //mockup
		return null;
    }

    protected String createSql()
    {
		return "SELECT * FROM customers";
    }

    protected ResultSet runSql(Connection conn, String sql)
        throws Exception
    {
		Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }
}
