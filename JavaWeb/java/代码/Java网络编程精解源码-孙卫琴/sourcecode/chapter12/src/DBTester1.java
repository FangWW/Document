public class DBTester1{
  public static void main(String args[])throws Exception{
    Connection con;
    Statement stmt;
    ResultSet rs;
    //����������������Ĵ���Ϊ����MySQL������
    Class.forName("com.mysql.jdbc.Driver");
    //ע��MySQL������
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    //���ӵ����ݿ��URL
    String dbUrl =
"jdbc:mysql://localhost:3306/STOREDB?useUnicode=true&characterEncoding=GB2312";
    String dbUser="dbuser";
    String dbPwd="1234";
    //�������ݿ�����
    con = java.sql.DriverManager.getConnection(dbUrl,dbUser,dbPwd);
    //����һ��Statement����
    stmt = con.createStatement();
    
    //�����¼�¼
stmt.executeUpdate("insert into CUSTOMERS (NAME,AGE,ADDRESS) " 
+"VALUES ('С��',20,'�Ϻ�')");

    //��ѯ��¼
    rs= stmt.executeQuery("SELECT ID,NAME,AGE,ADDRESS from CUSTOMERS");
    //�����ѯ���
    while (rs.next()){
      long id = rs.getLong(1);
      String name = rs.getString(2);
      int age = rs.getInt(3);
      String address = rs.getString(4);
        
      //��ӡ����ʾ������
      System.out.println("id="+id+",name="+name+",age="+age+",address="+address);
    }
 
    //ɾ�������ӵļ�¼
    stmt.executeUpdate("delete from CUSTOMERS where name='С��'");

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
