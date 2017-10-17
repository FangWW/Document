/**
 * <p>Title: ODBC�������ݿ�</p>
 * <p>Description: ��ʵ����ʾ���ʹ��JDBC-ODBC�Ų������ݿ⡣</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: odbcConn.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class odbcConn{
  private String url="";
  private String username="";
  private String password="";
/**
 *<br>����˵���������������
 *<br>���������
 *<br>�������ͣ�Connection ���Ӷ���
 */  
  public Connection conn(){
     try {
        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        Connection con = DriverManager.getConnection(url, username, password);
        return con;
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
     if(con==null) return;
     Statement stmt = con.createStatement();
     ResultSet rs = stmt.executeQuery(sql); 
     ResultSetMetaData rmeta = rs.getMetaData();
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
    }finally{
       try{
       con.close();
      }catch(SQLException se){}
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
      System.out.println("query error:"+e);
    }finally{
      try{
       con.close();
      }catch(SQLException se){}
    }
  }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] arg){
    if(arg.length!=3){
      System.out.println("use: java odbcConn url username password");
      return;
    }
    odbcConn oc = new odbcConn();
    oc.url = arg[0];
    oc.username=arg[1];
    oc.password=arg[2];
    oc.execute(oc.conn(),"insert into userinfo(name,address)values('switch','new York')");
    oc.query(oc.conn(),"select * from userinfo");
  }
} 
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  