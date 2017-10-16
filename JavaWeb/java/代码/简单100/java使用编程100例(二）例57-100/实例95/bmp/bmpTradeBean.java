package bmp;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Vector;
import javax.ejb.CreateException;
import javax.ejb.DuplicateKeyException;
import javax.ejb.EJBException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.ejb.FinderException;
import javax.ejb.NoSuchEntityException;
import javax.ejb.ObjectNotFoundException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * <p>Title: 实现类</p>
 * <p>Description: EJBean管理的JDBC持续性管理和事务管理；在这个文件中的代码直接访问数据库；</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: bmpTradeBean.java</p>
 * @author 杜江
 * @version 1.0
 */
public class bmpTradeBean implements EntityBean {
//设置是否打印控制台
  final static private boolean VERBOSE = true;
//声明实体上下文变量
  private EntityContext ctx;
  private String FundId;
  private double baseFunds;
  /**
   * 为EJBean设置实体EJB上下文
   * @参数 ctx    EntityContext
   */
  public void setEntityContext(EntityContext ctx) {
    log("setEntityContext called");
    this.ctx = ctx;
  }
 //取消实体上下文设置
 public void unsetEntityContext() {
    log("unsetEntityContext (" + id() + ")");
    this.ctx = null;
  }
  //这是本类必须实现的方法，在本例中没有用到
   public void ejbActivate() {
    log("ejbActivate (" + id() + ")");
  }
 //这是本类必须实现的方法，在本例中没有用到
  public void ejbPassivate() {
    log("ejbPassivate (" + id() + ")");
  }
   /**
   * 从数据库中加载EJB
   * @异常  javax.ejb.NoSuchEntityException 如果在数据库中没有找到Bean
   * @异常    javax.ejb.EJBException    通讯或系统错误
   */
  public void ejbLoad() {
    log("ejbLoad: (" + id() +  ")");
    //声明数据库连接对象
    Connection con = null;
    //声明SQL命令预处理对象
    PreparedStatement ps = null;
    //找到账号主键
    FundId = (String) ctx.getPrimaryKey();
    try {
    	//获取数据库连接
      con = getConnection();
      //设置SQL命令，读取记录
      ps  = con.prepareStatement("select fund from tbl_Funds where id = ?");
      ps.setString(1, FundId);
      //执行SQL
      ps.executeQuery();
      //获取SQL结果
      ResultSet rs = ps.getResultSet();
      if (rs.next()) {
      	//取得数据
        baseFunds = rs.getDouble(1);
      } else {
        String error = "ejbLoad: beansTeadeBean (" + FundId + ") not found !";
        log(error);
        throw new NoSuchEntityException (error);
       }
    } catch (SQLException sqe) {
    	//数据库异常处理
      log("SQLException:  " + sqe);
      throw new EJBException(sqe);
    } finally {
      cleanup(con, ps);
    }
  }
 /**
   * 数据库中存入EJBean
   * @异常   javax.ejb.NoSuchEntityException     如果在数据库中没有找到Bean
   * @异常   javax.ejb.EJBException     通讯或系统错误
   */
  public void ejbStore() {
    log("ejbStore (" + id() + ")");
    //声明数据库连接对象
    Connection con = null;
    //声明SQL命令预处理对象
    PreparedStatement ps = null;
    try {
     //获取数据库连接
      con = getConnection();
      //设置SQL命令,更新数据库
      ps = con.prepareStatement("update tbl_Funds set fund = ? where id = ?");
      ps.setDouble(1, baseFunds);
      ps.setString(2, FundId);
      //执行SQL
      if (!(ps.executeUpdate() > 0)) {
        String error = "ejbStore: bmpTradeBean (" + FundId + ") not updated !";
        log(error);
        throw new NoSuchEntityException (error);
      }
    } catch(SQLException sqe) {
    	//数据库次操作异常处理
      log("SQLException:  " + sqe);
      throw new EJBException (sqe);
    } finally {
      cleanup(con, ps);
    }
  }
  /**
   * 这个方法和"TblUserInfoBean.java"中定义的的Bean的"ejbCreate"方法相对应
   * 这两个方法的参数应该相同。当客户端调用"TblUserInfoHome.create()"方法时，EJB容器
   * 会找到EJBean的实例，并调用它的"ejbCreate()"方法。
   * 对容器管理的ejb,ejbCreate方法返回为null，而bean管理的ejb，返回的是主键类。
   * @参数 FundId         String 账号ID
   * @参数 initialbaseFunds    double 初始化结算值
   * @异常 javax.ejb.CreateException   创建bean错误时抛出的异常
   */
  public String ejbCreate(String FundId, double initialbaseFunds)
    throws CreateException
  {
  	//日志信息
    log("bmpTradeBean.ejbCreate( id = " +FundId+ ", " + "initial baseFunds = $ " + initialbaseFunds + ")");
    this.FundId = FundId;
    this.baseFunds = initialbaseFunds;
    //声明数据库连接
    Connection con = null;
    PreparedStatement ps = null;
    try {
      //获取数据库连接
      con = getConnection();
      //执行sql语句，插入记录
      ps = con.prepareStatement("insert into tbl_Funds (id, fund) values (?, ?)");
      ps.setString(1, FundId);
      ps.setDouble(2, baseFunds);
      if (ps.executeUpdate() != 1) {
        String error = "JDBC did not create any row";
        log(error);
        throw new CreateException (error);
      }
      log("JDBC create one row!");
      return FundId;
    } catch (SQLException sqe) {
      ///异常处理
      try {
      	//查找主键
        ejbFindByPrimaryKey(FundId);
      } catch(ObjectNotFoundException onfe) {
        String error = "SQLException: " + sqe;
        log(error);
        throw new CreateException (error);
      }
      String error = "An Account already exists in the database with Primary Key " + FundId;
      log(error);
      throw new DuplicateKeyException(error);
    } finally {
      cleanup(con, ps);
    }
  }
  //这是本类必须实现的方法，在本例中没有用到
 public void ejbPostCreate(String FundId, double initialbaseFunds) {
    log("ejbPostCreate (" + id() + ")");
  }
  /**
   * 从数据库中删除EJBean
   * @异常  javax.ejb.NoSuchEntityException   如果数据库中没找到这个ejb
   * @异常  javax.ejb.EJBException     通信错误抛出的异常
   */
  public void ejbRemove() {
    log("ejbRemove (" + id() + ")");
    //声明数据库连接
    Connection con = null;
    PreparedStatement ps = null;
    try {
    	//获取连接
      con = getConnection();
      //获取主键
      FundId = (String) ctx.getPrimaryKey();
      //执行SQL语句，删除记录
      ps = con.prepareStatement("delete from tbl_Funds where id = ?");
      ps.setString(1, FundId);
      if (!(ps.executeUpdate() > 0)) {
        String error = "bmpTradeBean (" + FundId + " not found";
        log(error);
        throw new NoSuchEntityException (error);
      }
    } catch (SQLException sqe) {
      //异常处理
      log("SQLException:  " + sqe);
      throw new EJBException (sqe);
    } finally {
     //清除
      cleanup(con, ps);
    }
  }
  /**
   * 给定主键查找EJBean
   * @参数 pk    String 主键
   * @异常  javax.ejb.ObjectNotFoundException   EJBean没发现抛出的异常
   * @异常  javax.ejb.EJBException  系统出现通讯故障时抛出
   */
  public String ejbFindByPrimaryKey(String pk)
    throws ObjectNotFoundException
  {
    log("ejbFindByPrimaryKey (" + pk + ")");
    //声明数据库连接
    Connection con = null;
    PreparedStatement ps = null;
    try {
    	//获取连接
      con = getConnection();
      //查询主键对应的记录
      ps  = con.prepareStatement("select fund from tbl_Funds where id = ?");
      ps.setInt(1, Integer.parseInt(pk));
      ps.executeQuery();
      //获取结果集
      ResultSet rs = ps.getResultSet();
      if (rs.next()) {
        baseFunds = rs.getDouble(1);
      } else {
      	//没有发现这个主键值的ejb
        String error = "ejbFindByPrimaryKey: beansTeadeBean (" + pk + ") not found";
        log(error);
        throw new ObjectNotFoundException (error);
       }
    } catch (SQLException sqe) {
    	//异常处理
      log("SQLException:  " + sqe);
      throw new EJBException (sqe);
    } finally {
    	//清除
      cleanup(con, ps);
    }
    log("ejbFindByPrimaryKey (" + pk + ") found");
    return pk;
  }
  /**
   * 查找所有结算大于给定值的EJBeans
   * @参数 baseFundsGreaterThan double 账户资金
   * @返回  Collection
   * @异常  javax.ejb.EJBException    通信错误抛出的异常
   */
  public Collection ejbFindBigAccounts(double baseFundsGreaterThan) {
    log("ejbFindBigAccounts (baseFunds > " + baseFundsGreaterThan + ")");
    //声明数据库连接
    Connection con = null;
    PreparedStatement ps = null;
    try {
    	//获取连接
      con = getConnection();
      ps = con.prepareStatement("select id from tbl_Funds where fund > ?");
      ps.setDouble(1, baseFundsGreaterThan);
      ps.executeQuery();
      //获取结果集
      ResultSet rs = ps.getResultSet();
      Vector v = new Vector();
      String pk;
      while (rs.next()) {
        pk = rs.getString(1);
        v.addElement(pk);
      }
      //返回集合
      return v;
    } catch (SQLException sqe) {
    	//异常处理
      log("SQLException: " + sqe);
      throw new EJBException (sqe);
    } finally {
    	//清除
      cleanup(con, ps);
    }
  }
/*
*方法说明：添加资金
* @参数：baseFunds 资金数
* @返回：
* @异常：Exception 当增加资金为负数时
*/
  public void addFunds(double baseFunds) throws Exception {
    if (baseFunds<0)
        throw new Exception("Invalid baseFunds");
    this.baseFunds+=baseFunds;
  }
/*
*方法说明：提取资金
* @参数：baseFunds 资金数
* @返回：
* @异常：Exception 当增加资金为负数和所提取资金超过账户上资金时
*/
  public void removeFunds(double baseFunds) throws Exception {
    if(baseFunds<0)
        throw new Exception("Invalid baseFunds");
    if(this.baseFunds<baseFunds)
        throw new Exception("the baseFunds less than baseFunds");
   this.baseFunds-=baseFunds;
  }
/*
*方法说明：查询账户资金数
* @返回：double 资金数
*/
  public double getBalance() {
    return this.baseFunds;
  }
  /**
   * 从连接池中获取当前连接
   * @返回   连接
   * @异常  javax.ejb.EJBException   通信错误
   */
  private Connection getConnection()
    throws SQLException
  {
  	//声明初始化上下文
    InitialContext initCtx = null;
    try {
      initCtx = new InitialContext();
      //查找数据源
      DataSource ds = (javax.sql.DataSource)
        initCtx.lookup("java:comp/env/jdbc/myDB");
        //返回数据源连接
      return ds.getConnection();
    } catch(NamingException ne) {
    	//有异常
      log("UNABLE to get a connection from myDB!");
      log("Please make sure that you have setup the connection pool properly");
      throw new EJBException(ne);
    } finally {
      try {
        if(initCtx != null) initCtx.close();
      } catch(NamingException ne) {
        log("Error closing context: " + ne);
        throw new EJBException(ne);
      }
    }
  }
  // 也可以使用WebLogic的日志服务
  private void log(String s) {
    if (VERBOSE) System.out.println(s);
  }
  // 返回这个beans的id
  private String id() {
    return "PK = " + (String) ctx.getPrimaryKey();
  }
  //清除连接
  private void cleanup(Connection con, PreparedStatement ps) {
    try {
      if (ps != null) ps.close();
    } catch (Exception e) {
      log("Error closing PreparedStatement: "+e);
      throw new EJBException (e);
    }
    try {
      if (con != null) con.close();
    } catch (Exception e) {
      log("Error closing Connection: " + e);
      throw new EJBException (e);
    }
  }
}
