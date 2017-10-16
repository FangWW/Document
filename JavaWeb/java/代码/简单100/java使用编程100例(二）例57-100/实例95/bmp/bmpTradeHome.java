package bmp;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
import java.rmi.RemoteException;
import java.util.Collection;
/**
 * <p>Title: 主接口</p>
 * <p>Description: 这是bmpTradeBean的主接口定义，这个接口是被EJB容器产生的类bmpTradeBean实现的
 *                在这里只需定义EJB创建的方法，这些方法要和EJBean中的"ejbCreate"方法对应。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: bmpTradeHome.java</p>
 * @author 杜江
 * @version 1.0
 */
public interface bmpTradeHome extends EJBHome {
   /**
   * 这个方法和"TblUserInfoBean.java"中定义的的Bean的"ejbCreate"方法相对应
   * 这两个方法的参数应该相同。当客户端调用"TblUserInfoHome.create()"方法时，EJB容器
   * 会找到EJBean的实例，并调用它的"ejbCreate()"方法。
   * @参数 accountID         String 账号ID
   * @参数 initialBalance    double 初始化结算值
   * @返回 bmpTrade 远程对象
   * @异常 javax.ejb.CreateException      创建bean错误时抛出的异常
   * @异常 RemoteException 当系统通讯发生故障时抛出
   */
  public bmpTrade create(String accountId, double initialBalance)
    throws CreateException, RemoteException;
 /**
   * 根据主键对象，返回账号对象
   * @参数 primaryKey   主键
   * @返回 TblUserInfo 账号
   * @异常 javax.ejb.FinderException   访问数据库错误抛出的异常
   * @异常 RemoteException 当系统通讯发生故障时抛出
   */
  public bmpTrade findByPrimaryKey(String primaryKey)
    throws FinderException, RemoteException;
  /**
   * 找到所有结算值大于balanceGreaterThan的账号
   * @返回 Enumeration 所有账号枚举
   * @参数 double balanceGreaterThan,给定的结算值
   * @异常 javax.ejb.FinderException   访问数据库错误抛出的异常
   * @异常 RemoteException 当系统通讯发生故障时抛出
   */
  public Collection findBigAccounts(double balanceGreaterThan)
    throws FinderException, RemoteException;
}
