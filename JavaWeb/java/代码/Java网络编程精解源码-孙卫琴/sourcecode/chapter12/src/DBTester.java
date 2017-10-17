public class DBTester{
  public static void main(String args[])throws Exception{
    Connection con;
    Statement stmt;
    ResultSet rs;
    //����������������Ĵ���Ϊ����MySQL������
    Class.forName("com.mysql.jdbc.Driver");
    //ע��MySQL������
    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
    //���ӵ����ݿ��URL
    String dbUrl = "jdbc:mysql://localhost:3306/STOREDB";
    String dbUser="dbuser";
    String dbPwd="1234";
    //�������ݿ�����
    con = java.sql.DriverManager.getConnection(dbUrl,dbUser,dbPwd);
    //����һ��Statement����

    stmt = con.createStatement();
    //�����¼�¼
    String name1=new String("С��".getBytes("GB2312"),"ISO-8859-1");
    String address1=new String("�Ϻ�".getBytes("GB2312"),"ISO-8859-1");
    stmt.executeUpdate("insert into CUSTOMERS (NAME,AGE,ADDRESS) VALUES ('"+name1+"',20,'"+address1+"')");

    //��ѯ��¼
    rs= stmt.executeQuery("SELECT ID,NAME,AGE,ADDRESS from CUSTOMERS");
    //�����ѯ���
    while (rs.next()){
      long id = rs.getLong(1);
      String name = rs.getString(2);
      int age = rs.getInt(3);
      String address = rs.getString(4);
      
      //�ַ�����ת�� 
      if(name!=null)name=new String(name.getBytes("ISO-8859-1"),"GB2312");  
      if(address!=null)address=new String(address.getBytes("ISO-8859-1"),"GB2312");            
      //��ӡ����ʾ������
      System.out.println("id="+id+",name="+name+",age="+age+",address="+address);
    }
 
    //ɾ�������ӵļ�¼
    stmt.executeUpdate("delete from CUSTOMERS where name='"+name1+"'");

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
