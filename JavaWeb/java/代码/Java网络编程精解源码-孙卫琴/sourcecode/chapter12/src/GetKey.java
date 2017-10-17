public class GetKey{
  public static void main(String args[])throws Exception{
    Connection con=new ConnectionProvider().getConnection();
    Statement stmt = con.createStatement();
    
    //�����¼�¼
    stmt.executeUpdate("insert into CUSTOMERS (NAME,AGE,ADDRESS) " 
       +"VALUES ('С��',20,'�Ϻ�')", Statement.RETURN_GENERATED_KEYS);
    ResultSet rs=stmt.getGeneratedKeys();
    //�����ѯ���
    if (rs.next()){
      System.out.println("id="+rs.getInt(1));
    }

    //�ͷ������Դ
    rs.close();
    stmt.close();
    con.close();
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
