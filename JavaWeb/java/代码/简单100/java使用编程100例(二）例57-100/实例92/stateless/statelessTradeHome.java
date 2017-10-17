//�ļ�����statelessTradeHome.java
//���ӿ�
package stateless;
import javax.ejb.*;
public interface statelessTradeHome extends javax.ejb.EJBHome {
/*
*����˵��������ʵ�ֵķ�������StatefulTradeBean��ejbCreate������Ӧ��
* @�쳣��CreateException ����EJB����ʱ�׳�
* @�쳣��RemoteException ��ϵͳͨѶ��������ʱ�׳�
*/
  public statelessTrade create() throws CreateException, RemoteException;
}
