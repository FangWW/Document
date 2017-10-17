package cmp;
import javax.ejb.*;
/**
 * <p>Title: �ỰEJB���ӿ�</p>
 * <p>Description: �ͻ��˵���create����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: userinfoHome.java</p>
 * @author �Ž�
 * @version 1.0
 */
public interface userinfoHome extends javax.ejb.EJBHome {
//����ʵ�ֵķ���������ejbʱ���á�
  public userinfo create() throws CreateException, RemoteException;
}
