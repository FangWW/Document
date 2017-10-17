/**
 * <p>Title: JDBC�������ݿ�</p>
 * <p>Description: ��ʵ����ʾ���ʹ��JDBC����Oracle���ݿ⣬����ʾ������ݺͲ�ѯ���ݡ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDBCSTMConn.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class JDBCSTMConn{
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
 *<br>����˵�������ô洢���̣��쿴���ݽ��
 *<br>���������Connection con ���ݿ�����
 *<br>���������String sql Ҫִ�е�SQL���
 *<br>�������ͣ�
 */
   public void execute(Connection con){
    CallableStatement toesUp = null; 
    try { 
      con.setAutoCommit(false); 
      //���ô洢����
      toesUp = con.prepareCall("{call p_test(?)}"); 
      //���ݲ������洢����
      toesUp.setInt(1, 6);
      //ִ�д洢����
      toesUp.executeQuery();

      Statement stmt = con.createStatement();
      ResultSet rs = (ResultSet) stmt.executeQuery("SELECT * FROM TEST"); 
      while (rs.next()) { 
        String ID = rs.getString(1); 
        String NAME = rs.getString(2); 
        System.out.println(ID+ " " +NAME);
      } 
      rs.close(); 
    } catch (SQLException e) { 
    System.out.println(e);
    try{
    toesUp.close(); 
    con.close();
    }catch(Exception es){System.out.println(es);} 
   }
  }

/**
 *<br>����˵����ʵ����ʾ
 *<br>���������
 *<br>�������ͣ�
 */
  public void demo(){
    try{
      JDBCSTMConn oc = new JDBCSTMConn();
      Connection conn = oc.conn();
      oc.execute(conn);
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
      System.out.println("use: java JDBCSTMConn url username password");
      return;
    }
    JDBCSTMConn oc = new JDBCSTMConn();
    oc.url = arg[0];
    oc.username=arg[1];
    oc.password=arg[2];
    oc.demo();
  }
} 
  
