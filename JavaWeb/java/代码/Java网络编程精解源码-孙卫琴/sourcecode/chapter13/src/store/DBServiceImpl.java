package store;

public class DBServiceImpl implements DBService{

  private ConnectionPool pool; //���ӳ�

  public DBServiceImpl() throws Exception{
    //ConnectionPoolImpl2���ӳ�ʵ���ṩConnection����Ķ�̬����
    pool=new ConnectionPoolImpl2(); 
  }
  /** ����������һ��Statement���� */
  public Statement getStatement() throws Exception{
    return pool.getConnection().createStatement();
  }
  
  /** �ر�Statement�����Լ���֮������Connection����*/
  public void closeStatement(Statement stmt){
    try{
    }finally{
      try{
        if(stmt!=null){
          Connection con=stmt.getConnection(); 
          stmt.close();
          //con����Connection����Ķ�̬�����������close()����������Ż����ӳ�
          con.close(); 
        }  
      }catch(Exception e){e.printStackTrace();}
    }
  }
  /** ִ��SQL update��delete��insert��� */ 
  public void modifyTable(String sql) throws Exception{
    Statement stmt=getStatement();
    try {
       stmt.executeUpdate(sql);
    }finally{closeStatement(stmt);}
  }
}



/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
