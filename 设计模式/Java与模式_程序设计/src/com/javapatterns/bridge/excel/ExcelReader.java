package com.javapatterns.bridge.excel;

public class ExcelReader
{
	public static String readExcel(String ODBCEntry , String sheetNumber)
	{
		StringBuffer ret = new StringBuffer(1000);
		Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        ResultSetMetaData rsmeta = null;

		try
        {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			connection = DriverManager.getConnection( "jdbc:odbc:" + ODBCEntry );

			stmt = connection.createStatement();
			rs = stmt.executeQuery( "Select * from [Sheet" + sheetNumber + "$]" );
			
			rsmeta = rs.getMetaData();
			int numberOfColumns = rsmeta.getColumnCount();

			while (rs.next())
            {
   				for (int i = 1; i <= numberOfColumns; i++)
   				{
   					if (i > 1)
   					{
					    ret.append(",");
	   					ret.append(rs.getString(i));
	   				}
	   			}
   				ret.append("\n");
       		}
        }
        catch(Exception e)
   		{
   			e.printStackTrace();
   		}
        finally
        {
            try
            {
				stmt.close();
   				connection.close();
            }
            catch(Exception e) {}

            return ret.toString();
        }
    }

    public static void main(String[] args)
    {
     	System.out.println(readExcel("myExcelFile", "1"));
    }

}
