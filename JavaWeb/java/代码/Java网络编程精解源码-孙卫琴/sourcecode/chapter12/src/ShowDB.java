public class ShowDB {
  public static void main(String[] args)throws SQLException{  
    Connection con=new ConnectionProvider().getConnection();
    DatabaseMetaData metaData=con.getMetaData();
    System.out.println("��������������Ϊ:"+metaData.getMaxConnections());
    System.out.println("һ����������ͬʱ�򿪵�Statement�������ĿΪ:"+metaData.getMaxStatements());
  
    ResultSet tables=metaData.getTables(null,null,null,new String[]{"TABLE"});
    SQLExecutor.showResultSet(tables);
    con.close();
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
