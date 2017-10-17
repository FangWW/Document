/**
 * <p>Title: JDBC�������ݿ�</p>
 * <p>Description: ��ʵ����ʾ���ʹ��JDBC����Oracle���ݿ⣬����ʾ������ݺͲ�ѯ���ݡ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDBCConn.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class JDBCConn{
  private String url="";
  private  String username="";
  private  String password="";
/**
 *<br>����˵���������������
 *<br>���������
 *<br>�������ͣ�Connection ���Ӷ���
 */  
  public Connection conn(){
     try {
     	//����JDBC����
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //�������ݿ�����
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ORCL", "test", "test");
        return con;
    }catch(ClassNotFoundException cnf){
    	System.out.println("driver not find:"+cnf);
    	return null;
    }catch(SQLException sqle){
    	System.out.println("can't connection db:"+sqle);
    	return null;
    } catch (Exception e) {
        System.out.println("Failed to load JDBC/ODBC driver.");
        return null;
     }
  }
/**
 *<br>����˵����ִ�в�ѯSQL���
 *<br>���������Connection con ���ݿ�����
 *<br>���������String sql Ҫִ�е�SQL���
 *<br>�������ͣ�
 */
  public void query(Connection con, String sql){
    try{
     if(con==null){
       throw new Exception("database connection can't use!");
     }
     if(sql==null) throw new Exception("check your parameter: 'sql'! don't input null!");
     //�������
     Statement stmt = con.createStatement();
     //ִ�в�ѯ
     ResultSet rs = stmt.executeQuery(sql); 
     ResultSetMetaData rmeta = rs.getMetaData();
     //��������ֶθ���
     int numColumns = rmeta.getColumnCount();
     while(rs.next())
	 {
	   for(int i = 0;i< numColumns;i++)
	   {
		String sTemp = rs.getString(i+1);
		System.out.print(sTemp+"  ");
	   }
	  System.out.println("");	
	 }
    }catch(Exception e){
      System.out.println("query error:"+e);
    }
  }
/**
 *<br>����˵����ִ�в��롢���¡�ɾ����û�з��ؽ������SQL���
 *<br>���������Connection con ���ݿ�����
 *<br>���������String sql Ҫִ�е�SQL���
 *<br>�������ͣ�
 */
   public void execute(Connection con, String sql){
    try{
     if(con==null) return;
     Statement stmt = con.createStatement();
    stmt.executeUpdate(sql); 

   }catch(Exception e){
      System.out.println("execute error: sql = "+sql);
      System.out.println(e);
    }
  }
/**
 *<br>����˵����ʵ����ʾ
 *<br>���������
 *<br>�������ͣ�
 */
  public void demo(){
    try{
      JDBCConn oc = new JDBCConn();
      Connection conn = oc.conn();
      String sql = "insert into TBL_USER(id,name,password)values(seq_user.nextval,'switch','haorenpingan')";
      oc.execute(conn,sql);
      sql = "select * from TBL_USER";
      oc.query(conn,sql);
      conn.close();
    }catch(SQLException se){
      System.out.println(se);
    }catch(Exception e){
      System.out.println(e);
    }
    
  }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] arg){
    if(arg.length!=3){
      System.out.println("use: java JDBCConn url username password");
      return;
    }
    JDBCConn oc = new JDBCConn();
    oc.url = arg[0];
    oc.username=arg[1];
    oc.password=arg[2];
    oc.demo();
  }
} 
  
