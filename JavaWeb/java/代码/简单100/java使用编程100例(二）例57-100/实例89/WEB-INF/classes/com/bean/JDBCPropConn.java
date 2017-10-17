package  com.bean; 

/**
 * <p>Title: JSP+Bean�������ݿ�</p>
 * <p>Description: ��ʵ����ʾJSP+Bean���ʵ�����ݿ��ѯ������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: JDBCPropConn.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class JDBCPropConn{

/**
 *<br>����˵���������������
 *<br>���������
 *<br>�������ͣ�Connection ���Ӷ���
 */  
  public Connection conn(){
     try {
         String connectstring = loadEnv.getProperty("database.connectstring", "jdbc:weblogic:pool:orderPool");
         String connectdriver = loadEnv.getProperty("database.driver", "weblogic.jdbc.pool.Driver");
         String user = loadEnv.getProperty("database.user", null);
         String password = loadEnv.getProperty("database.password", null);
     	//����JDBC����
        Class.forName(connectdriver);
        //�������ݿ�����
        Connection conn = null;
        if(user == null)
            conn = DriverManager.getConnection(connectstring, null);
         else
          conn = DriverManager.getConnection(connectstring, user, password);
        
         return conn;
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
  public Vector query( String sql){
  	Connection con = conn();
    Vector vResult = new Vector();
    try{
     if(con==null){
       throw new Exception("database connection can't use!");
     }
     if(sql==null) throw new Exception("check your parameter: 'sql'! don't input null!");
     
     //�������
     Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     //ִ�в�ѯ
     ResultSet rs = stmt.executeQuery(sql); 
     //��ȡ��¼����  
     rs.last();  
     int intRowCount = rs.getRow(); 
     ResultSetMetaData rmeta = rs.getMetaData();
     //��������ֶθ���
     int numColumns = rmeta.getColumnCount();

     while(rs.next())
	 {
	   Vector vTemp = new Vector();
	   for(int i = 0;i< numColumns;i++)
	   {
		String sTemp = rs.getString(i+1);
		vTemp.addElement(sTemp);
	   }
	   vResult.addElement(vTemp);
	 }
	 rs.close();
    stmt.close();
    }catch(Exception e){
      System.out.println("query error:"+e);
    }finally{
     try{
     con.close();
    }catch(SQLException e){}
    }
    return vResult;
  }
/**
 *<br>����˵����ִ�в�ѯSQL���
 *<br>���������Connection con ���ݿ�����
 *<br>���������String sql Ҫִ�е�SQL���
 *<br>���������pageNo  ҳ����
 *<br>���������pageSize  ��¼����
 *<br>�������ͣ�
 */
  public Vector query(String sql,int pageNo,int pageSize){
    Vector vResult = new Vector();
    Connection con = conn();
    try{
     if(con==null){
       throw new Exception("database connection can't use!");
     }
     if(sql==null) throw new Exception("check your parameter: 'sql'! don't input null!");
     int intRowCount;//��¼����
     int intPageCount;//��ҳ��
     //�������
     Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
     //ִ�в�ѯ
     ResultSet rs = stmt.executeQuery(sql); 
     ResultSetMetaData rmeta = rs.getMetaData();
     //��������ֶθ���
     int numColumns = rmeta.getColumnCount();
     //��ȡ��¼����  
     rs.last();  
     intRowCount = rs.getRow(); 
     vResult.addElement(String.valueOf(intRowCount));
     //������ҳ��  
     intPageCount = (intRowCount+pageSize-1) / pageSize;
	 //��������ʾ��ҳ��  
     if(pageNo>intPageCount) pageNo = intPageCount; 

	 if(intPageCount>0){
     //����¼ָ�붨λ������ʾҳ�ĵ�һ����¼��  
     rs.absolute((pageNo-1) * pageSize + 1); 
     int i = 0;
     while(i<pageSize && !rs.isAfterLast())
	 {
	   Vector vTemp = new Vector();
	   for(int j = 0;j< numColumns;j++)
	   {
	   	
		String sTemp = rs.getString(j+1);
		vTemp.addElement(sTemp);
	   }
	   i++;
	   rs.next();
	  vResult.addElement(vTemp);	
	 }
	}
	rs.close();
    stmt.close();
    }catch(Exception e){
      System.out.println("query error:"+e);
    }finally{
    try{
      con.close();
    }catch(SQLException e){}
   }
    return vResult;
  }
/**
 *<br>����˵����ִ�в��롢���¡�ɾ����û�з��ؽ������SQL���
 *<br>���������Connection con ���ݿ�����
 *<br>���������String sql Ҫִ�е�SQL���
 *<br>�������ͣ�
 */
   public int execute( String sql){
    Connection con = conn();
    try{
     if(con==null) return -2;
     Statement stmt = con.createStatement();
     return stmt.executeUpdate(sql); 
    }catch(Exception e){
      System.out.println("query error:"+e);
      return -1;
    }finally{
     try{
      con.close();
    }catch(SQLException e){}
    }
  }

} 