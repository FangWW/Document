import java.sql.*;
import java.util.*;
import java.lang.reflect.*;

public class ConnectionPoolImpl2 implements ConnectionPool{
  private ConnectionProvider provider=new ConnectionProvider();
  private final ArrayList<Connection> pool = new ArrayList<Connection>();
  private int poolSize=5;

  public ConnectionPoolImpl2(){}
  public ConnectionPoolImpl2(int poolSize){
    this.poolSize=poolSize;
  }
  
  /** 从连接池中取出连接 */
  public Connection getConnection() throws SQLException {
    synchronized (pool) {
      if ( !pool.isEmpty()){
        int last = pool.size() - 1;
	Connection con =pool.remove(last);
	return con;
      }
    }
		
    Connection con= provider.getConnection();
    return getProxy(con,this);
  }
  
  /** 把连接返回连接池 */	
  public void releaseConnection(Connection con) throws SQLException {
    synchronized (pool) {
      int currentSize = pool.size();
      if( currentSize < poolSize ) {
	 pool.add(con);
         return;
      }
    }
    
    try {
      closeJdbcConnection(con);
    }catch (SQLException e) {e.printStackTrace();}
  }

  private void closeJdbcConnection(Connection con) throws SQLException {
    ConnectionP conP=(ConnectionP)con;
    conP.getJdbcConnection().close();
  }	
  protected void finalize() {
    close();
  }
  
  
  /** 关闭连接池*/	
  public void close() {
    Iterator<Connection> iter = pool.iterator();
    while ( iter.hasNext()) {
      try {
        closeJdbcConnection(iter.next());
      }catch (SQLException e){e.printStackTrace();}
    }
    pool.clear();		
  }

  private Connection getProxy(final Connection con,final ConnectionPool pool){
    InvocationHandler handler=new InvocationHandler(){
      public Object invoke(Object proxy,Method method,Object args[])
                                                       throws Exception{
        if(method.getName().equals("close")){
          pool.releaseConnection((Connection)proxy);
          return null;
        }else if(method.getName().equals("getJdbcConnection")){
          return con;
        }else{
          return method.invoke(con,args);
        }
      }
    };

    return (Connection)Proxy.newProxyInstance(ConnectionP.class.getClassLoader(),
                                          new Class[]{ConnectionP.class},
                                          handler);
  }

  interface ConnectionP extends Connection{
    public Connection getJdbcConnection(); 
  }

}









/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
