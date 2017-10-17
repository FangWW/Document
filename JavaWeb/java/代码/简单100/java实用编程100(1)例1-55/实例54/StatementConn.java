/**
 * <p>Title: ʹ�����</p>
 * <p>Description: ��ʵ����ʾʹ����䷽ʽ��ѯ���ݿ�����������һ��Ԥ�����ִ�з�����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: StatementConn.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class StatementConn{
  private static String url="";
  private static String username="";
  private static String password="";
  Connection con = null;
  PreparedStatement updStmt=null;//������
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
        con = DriverManager.getConnection(url, username, password);
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
 *<br>����˵�����ر����ݿ�
 *<br>���������
 *<br>�������ͣ�
 */  
  public void close() 
  {
	try
	{
     con.close();
	}
	catch(Throwable e){
	  System.out.println(e);
	}
	con = null;
  }
/**
 *<br>����˵�������ִ��
 *<br>���������
 *<br>�������ͣ�
 */
  private PreparedStatement getStatement(String sql,Vector vCondition) throws SQLException{
	try
	{
      int i=0;
      Object temp;
      updStmt=conn().prepareStatement(sql);
       for (i=0;i<vCondition.size();i++){
        temp=vCondition.elementAt(i);
        if (temp instanceof Integer) {
          updStmt.setInt(i+1,((Integer)temp).intValue());
        }
        else if (temp instanceof Double) {
          updStmt.setDouble(i+1,((Double)temp).doubleValue());
        }
        else if (temp instanceof String) {
          String str=(temp.toString()).trim();
          updStmt.setString(i+1,str);
        }
        else {
        	updStmt.setObject(i+1,temp);
        }
      }
	}
	catch(SQLException e)
	{
		throw e;
	}
	return updStmt;
  }
/**
 *<br>����˵�����ر�������
 *<br>���������
 *<br>�������ͣ�
 */  
  private void closeUpdStmt()
  {
	try
	{
	  if(updStmt!=null)
		updStmt.close();
	}
	catch(Throwable e)
	{
      System.out.println(e);
	}
	updStmt=null;
  }	
/**
 *<br>����˵����ִ��SQL
 *<br>���������
 *<br>�������ͣ�
 */
  public Object execute(String sql,Vector vCondition) throws SQLException,Exception {
	java.sql.ResultSet rs = null;
	java.util.Vector vResult = null;
	try
	{
     if(!isSelect(sql))
	 {
      //insert,update,delete
	  try
	  {
		Integer iResult=new Integer(getStatement(sql,vCondition).executeUpdate());
        return iResult;
	  }
	  catch(SQLException e1)
	  {
         throw e1;
	  }
	}
	else {
      //select
      rs = getStatement(sql,vCondition).executeQuery();
      int columnCount = rs.getMetaData().getColumnCount();
	  vResult = new Vector();
	  while(rs.next())
	  {
	   Vector vTemp = new Vector();
	   for(int i = 0;i< columnCount;i++)
	   {
		String sTemp = rs.getString(i+1);
		vTemp.addElement(sTemp== null ? "" : sTemp.trim());
	   }
	   vResult.addElement(vTemp);
	  }
	  rs.close();
      closeUpdStmt();
	 }
    }
	catch(Exception e1)
	{
       throw e1;
	}
	finally
    {
      close();
    }
	return vResult;
  }
/**
 *<br>����˵����
 *<br>���������
 *<br>�������ͣ�
 */	
  protected boolean isSelect(String psql) {
	String sql = psql.trim().toUpperCase();
	if(sql.indexOf("SELECT") != 0) return false;
	return true;
  }
/**
 *<br>����˵����
 *<br>���������
 *<br>�������ͣ�
 */  
 public static void main(String[] arg){
    if(arg.length!=3){
      System.out.println("use: java StatementConn url username password");
      return;
    }
    
    url = arg[0];
    username=arg[1];
    password=arg[2];
    demo();
  }
/**
 *<br>����˵������ʾ����
 *<br>���������
 *<br>�������ͣ�
 */
  public static void demo(){
  	try{
      StatementConn oc = new StatementConn();
      String sql = "select * from TBL_USER where id>?";
      Vector vCondition =new Vector();
      vCondition.addElement(new Integer(3));
      Vector vResult = (Vector)oc.execute(sql,vCondition);
      for(int i=0;i<vResult.size();i++){
       System.out.println(vResult.elementAt(i));
      }
    }catch(Exception e){
      System.out.println(e);
    }
  }
}//end