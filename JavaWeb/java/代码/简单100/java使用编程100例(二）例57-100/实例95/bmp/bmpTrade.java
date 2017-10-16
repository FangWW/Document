package bmp;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

/**
 * <p>Title: 远程接口</p>
 * <p>Description: 这是AccontBean的远程接口定义。远程接口中定义了客户端能远程调用EJBean的方法。这些方法除了
 *                 要抛出异常java.rmi.RemoteException之外，和EJBean中的定义是一致的。但并不是EJBean来实
 *                 现这个接口，而是由容器自动产生的类AccontBeanE实现的。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: bmpTrade.java</p>
 * @author 杜江
 * @version 1.0
 */
public interface bmpTrade extends EJBObject {
/*
*方法说明：添加资金
* @参数：fund 资金数
* @返回：
* @异常：RemoteException 当系统通信发生故障时
*/
  public void addFunds(double fund) throws Exception, RemoteException;
/*
*方法说明：提取资金
* @参数：fund 资金数
* @返回：
* @异常：RemoteException 当系统通信发生故障时
*/
  public void removeFunds(double fund) throws Exception, RemoteException;
/*
*方法说明：察看资金数目
* @参数：
* @返回：double 资金数
* @异常：RemoteException 当系统通信发生故障时
*/
  public double getBalance() throws RemoteException;
}
