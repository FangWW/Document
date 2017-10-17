import javax.sql.DataSource;
public class DataSourceImpl implements DataSource{
  private ConnectionPool pool=new ConnectionPoolImpl2(); 
  public Connection getConnection()throws SQLException{return pool.getConnection();} 
  public Connection getConnection(String username, String password)throws SQLException{
    throw new UnsupportedOperationException();
  } 
  public int getLoginTimeout()throws SQLException{
    return DriverManager.getLoginTimeout();
  } 
  public PrintWriter getLogWriter()throws SQLException{
    return DriverManager.getLogWriter();
  } 
  public void setLoginTimeout(int seconds)throws SQLException{
    DriverManager.setLoginTimeout(seconds);
  } 
  public void setLogWriter(PrintWriter out)throws SQLException{
    DriverManager.setLogWriter(out);
  } 
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
