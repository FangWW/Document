package store;
public interface ConnectionPool{
  /** �����ӳ���ȡ������ */
  public Connection getConnection()throws SQLException; 
    
  /** �����ӷ������ӳ� */
  public void releaseConnection(Connection con)throws SQLException;

  /** �ر����ӳ�*/	
  public void close();
}









/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
