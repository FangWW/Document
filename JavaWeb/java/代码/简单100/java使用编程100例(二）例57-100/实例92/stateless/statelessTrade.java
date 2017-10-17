//�ļ�����statelessTrade.java
//Զ�̽ӿ�
package stateless;
import javax.ejb.*;
/*
*����StatefulTrade��Զ�̽ӿڶ��塣Զ�̽ӿڶ����˿ͻ�����Զ�̵��õ�EJB�ķ�����
*��Щ���������׳��쳣java.rmi.RemoteException����EJB�ж�����һ�µġ�
*������EJB��ʵ������ӿڣ������������ֵ��������StatefulTradeBeanEʵ�֡�
*/
public interface statelessTrade extends javax.ejb.EJBObject {
/*
*����˵��������ʽ�
* @������fund �ʽ���
* @���أ�
* @�쳣��RemoteException ��ϵͳͨ�ŷ�������ʱ
*/
  public void addFunds(double fund) throws Exception, RemoteException;
/*
*����˵������ȡ�ʽ�
* @������fund �ʽ���
* @���أ�
* @�쳣��RemoteException ��ϵͳͨ�ŷ�������ʱ
*/
  public void removeFunds(double fund) throws Exception, RemoteException;
/*
*����˵�����쿴�ʽ���Ŀ
* @������
* @���أ�double �ʽ���
* @�쳣��RemoteException ��ϵͳͨ�ŷ�������ʱ
*/
  public double getBalance() throws RemoteException;
}
