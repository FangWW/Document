public class ConnectionPoolImpl1 implements ConnectionPool {
  private ConnectionProvider provider=new ConnectionProvider();
  private final ArrayList<Connection> pool = new ArrayList<Connection>();
  private int poolSize=5;

  public ConnectionPoolImpl1(){}
  public ConnectionPoolImpl1(int poolSize){
    this.poolSize=poolSize;
  }
  
  /** �����ӳ���ȡ������ */
  public Connection getConnection() throws SQLException {
    synchronized (pool) {
      if ( !pool.isEmpty()){
        int last = pool.size() - 1;
	Connection con =pool.remove(last);
	return con;
      }
    }
		
    Connection con= provider.getConnection();
    return con;
  }
  
  /** �����ӷ������ӳ� */	
  public void releaseConnection(Connection con) throws SQLException {
    synchronized (pool) {
      int currentSize = pool.size();
      if( currentSize < poolSize ) {
	 pool.add(con);
         return;
      }
    }
    
    try {
      con.close();
    }catch (SQLException e) {e.printStackTrace();}
  }
	
  protected void finalize() {
    close();
  }

  /** �ر����ӳ�*/	
  public void close() {
    Iterator<Connection> iter = pool.iterator();
    while ( iter.hasNext()) {
      try {
        iter.next().close();
      }catch (SQLException e){e.printStackTrace();}
    }
    pool.clear();		
  }
}









/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
