package cmp;
import javax.ejb.*;
import java.util.*;
import java.rmi.*;
/**
 * <p>Title: 会话EJB主接口</p>
 * <p>Description: 客户端调用create方法</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: userinfoHome.java</p>
 * @author 杜江
 * @version 1.0
 */
public interface userinfoHome extends javax.ejb.EJBHome {
//必需实现的方法。创建ejb时调用。
  public userinfo create() throws CreateException, RemoteException;
}
