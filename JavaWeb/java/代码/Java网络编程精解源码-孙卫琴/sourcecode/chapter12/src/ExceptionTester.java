public class ExceptionTester{
  public static void main(String args[]){
    try{
      Connection con=new ConnectionProvider().getConnection();
      Statement stmt=con.createStatement();
      ResultSet rs=stmt.executeQuery("select FIRSTNAME from CUSTOMERS");
    }catch(SQLException e){
      System.out.println("ErrorCode:"+e.getErrorCode());
      System.out.println("SQLState:"+e.getSQLState());
      System.out.println("Reason:"+e.getMessage());
    }
  }
} 


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
