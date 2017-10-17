//�ļ�����StatefulTradeHome.java
//���ӿ��ļ�
package stateful;
import javax.ejb.*;
public interface StatefulShopHome extends javax.ejb.EJBHome {
/*
*����˵��������ʵ�ֵķ�������StatefulShopBean��ejbCreate������Ӧ��
* @�쳣��CreateException ����EJB����ʱ�׳�
* @�쳣��RemoteException ��ϵͳͨѶ��������ʱ�׳�
*/
  public StatefulShop create() throws CreateException, RemoteException;
}
