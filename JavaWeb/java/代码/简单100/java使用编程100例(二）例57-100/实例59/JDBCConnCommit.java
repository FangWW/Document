/**
 * <p>Title: JDBC�������ݿ�</p>
 * <p>Description: ��ʵ����ʾ���ʹ��JDBC����Oracle���ݿ⣬����ʾ������ݺͲ�ѯ���ݡ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDBCConnCommit.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class JDBCConnCommit{
  private static String url="";
  private static String username="";
  private static String password="";
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
        Connection con = DriverManager.getConnection(url, username, password);
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
  public void query(Connection con, String sql) throws SQLException,Exception {
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
	 rs.close();
	 stmt.close();
    }catch(Exception e){
      System.out.println("query error: sql = "+sql);
      System.out.println("query error:"+e);
      throw new SQLException("query error");
    }
  }
/**
 *<br>����˵����ִ�в��롢���¡�ɾ����û�з��ؽ������SQL���
 *<br>���������Connection con ���ݿ�����
 *<br>���������String sql Ҫִ�е�SQL���
 *<br>�������ͣ�
 */
   public void execute(Connection con, String sql) throws SQLException  {
    try{
     if(con==null) return;
     Statement stmt = con.createStatement();
     int i = stmt.executeUpdate(sql); 
     System.out.println("update row:"+i);
     stmt.close();
   }catch(Exception e){
      System.out.println("execute error: sql = "+sql);
      System.out.println(e);
      throw new SQLException("execute error");
    }
  }

/**
 *<br>����˵����ʵ����ʾ
 *<br>���������
 *<br>�������ͣ�
 */
  public void demo(){
      JDBCConnCommit oc = new JDBCConnCommit();
      Connection conn = oc.conn();
    try{
      conn.setAutoCommit( false );
      String sql = "";
      for(int i=0;i<4;i++){
      sql = "insert into TBL_USER(id,name,password)values(seq_user.nextval,'tom','haorenpingan')";
      oc.execute(conn,sql);
      }
      sql = "select * from TBL_USER where name='tom' order by id";
      oc.query(conn,sql);
      sql = "delete from TBL_USER where name='tom'";
      oc.execute(conn,sql);
      conn.commit();
    }catch(SQLException se){
      try{
        conn.rollback(); 
      }catch(Exception e){
      }
      System.out.println(se);
    }catch(Exception e){ 
      System.out.println(e);
    }finally
    { 
      try{
       conn.close();
     }catch(SQLException e){}
    }
    
  }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] arg){
    if(arg.length!=3){
      System.out.println("use: java JDBCConnCommit url username password");
      return;
    }
    JDBCConnCommit oc = new JDBCConnCommit();
    oc.url = arg[0];
    oc.username=arg[1];
    oc.password=arg[2];
    oc.demo();
  }
} 
  
