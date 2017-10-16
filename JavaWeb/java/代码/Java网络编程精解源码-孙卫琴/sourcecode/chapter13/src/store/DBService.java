package store;
import java.sql.*;

public interface DBService {
  /**  获得Statement对象 */
  public Statement getStatement() throws Exception;
  /** 关闭Statement对象，以及与之关联的Connection对象 */
  public void closeStatement(Statement stmt);
  /** 执行SQL update、delete和insert语句*/
  public void modifyTable(String sql) throws Exception;
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
