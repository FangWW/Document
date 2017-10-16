//文件名：StatefulTradeHome.java
//主接口文件
package stateful;
import javax.ejb.*;
import java.util.*;
import java.rmi.*;
public interface StatefulShopHome extends javax.ejb.EJBHome {
/*
*功能说明：必需实现的方法。与StatefulShopBean中ejbCreate方法对应。
* @异常：CreateException 创建EJB错误时抛出
* @异常：RemoteException 当系统通讯发生故障时抛出
*/
  public StatefulShop create() throws CreateException, RemoteException;
}
