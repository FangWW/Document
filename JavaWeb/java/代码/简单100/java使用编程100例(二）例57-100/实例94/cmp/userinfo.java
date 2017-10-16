
package cmp;
import javax.ejb.*;
import java.util.*;
import java.rmi.*;
/**
 * <p>Title: 会话EJB远程口程序</p>
 * <p>Description: 定义会话EJB使用方法</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: userinfo.java</p>
 * @author 杜江
 * @version 1.0
 */
public interface userinfo extends javax.ejb.EJBObject {
/*功能说明：插入一条数据记录 
* @参数：java.lang.String name 用户名
* @参数：java.lang.String phone 联系电话
* @参数：java.lang.String home 家庭住址
* @参数：java.sql.Date brithday 出生日期
* @异常：RemoteException当系统通讯发生故障时抛出
*/
  public  void ist_info(java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday) throws RemoteException;
/*功能说明：删除一条记录 
* @参数：java.lang.Integer id 主键id
* @异常：RemoteException当系统通讯发生故障时抛出
*/
  public  int del_info(java.lang.Integer id) throws RemoteException;
/*功能说明：修改一条记录 
* @参数：java.lang.Integer id 主键id
* @参数：java.lang.String name 用户名
* @参数：java.lang.String phone 联系电话
* @参数：java.lang.String home 家庭住址
* @参数：java.sql.Date brithday 出生日期
* @异常：RemoteException当系统通讯发生故障时抛出
*/
  public  int up_info(java.lang.Integer id, java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday) throws RemoteException;
/*功能说明：查询一条记录 
* @参数：java.lang.Integer id 主键id
* @异常：RemoteException当系统通讯发生故障时抛出
*/
  public  Vector find_id(java.lang.Integer id) throws RemoteException;
  public  int findUser(String username) throws RemoteException;
}
