//文件名：statelessTradeHome.java
//主接口
package stateless;
import javax.ejb.*;
import java.util.*;
import java.rmi.*;
public interface statelessTradeHome extends javax.ejb.EJBHome {
/*
*功能说明：必需实现的方法。与StatefulTradeBean中ejbCreate方法对应。
* @异常：CreateException 创建EJB错误时抛出
* @异常：RemoteException 当系统通讯发生故障时抛出
*/
  public statelessTrade create() throws CreateException, RemoteException;
}
