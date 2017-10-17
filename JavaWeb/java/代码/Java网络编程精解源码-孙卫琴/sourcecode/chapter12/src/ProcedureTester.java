public class ProcedureTester{
  public static void main(String args[])throws Exception{
    Connection con=new ConnectionProvider().getConnection();
    CallableStatement cStmt = con.prepareCall("{call demoSp(?, ?)}");
    //���õ�һ��������ֵ
    cStmt.setString(1, "Tom");
    //cStmt.setString("inputParam", "Tom");
    
    //ע��ڶ�������������
    cStmt.registerOutParameter(2, Types.INTEGER);
    //cStmt.registerOutParameter("inOutParam", Types.INTEGER);
    
    //���õڶ���������ֵ
    cStmt.setInt(2, 1);
    //cStmt.setInt("inOutParam", 1);
   
    //ִ�д洢����
    boolean hadResults = cStmt.execute();
    //���ʽ����
    if(hadResults) {
      ResultSet rs = cStmt.getResultSet();
      //SQLExecutor��μ�12.1�ڵ�����12-1
      SQLExecutor.showResultSet(rs);
    }
    
    //��õڶ������������ֵ
    int outputValue = cStmt.getInt(2); // index-based
    //int outputValue = cStmt.getInt("inOutParam"); // name-based
   
    con.close();
  }
}


/*************************
������MySQL���ݿ��д洢���̵Ķ���

CREATE PROCEDURE demoSp(IN inputParam VARCHAR(255), INOUT inOutParam INT)
BEGIN
    DECLARE z INT;
    SET z = inOutParam + 1;
    SET inOutParam = z;

    SELECT CONCAT('hello ', inputParam);
END
**************************/


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
