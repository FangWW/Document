package store;
public interface DBService {
  /**  ���Statement���� */
  public Statement getStatement() throws Exception;
  /** �ر�Statement�����Լ���֮������Connection���� */
  public void closeStatement(Statement stmt);
  /** ִ��SQL update��delete��insert���*/
  public void modifyTable(String sql) throws Exception;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
