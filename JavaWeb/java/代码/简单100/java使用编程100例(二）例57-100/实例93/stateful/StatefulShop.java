package stateful;

import javax.ejb.*;
/*
*����StatefulTrade��Զ�̽ӿڶ��塣Զ�̽ӿڶ����˿ͻ�����Զ�̵��õ�EJB�ķ�����
*��Щ���������׳��쳣java.rmi.RemoteException����EJB�ж�����һ�µġ�
*������EJB��ʵ������ӿڣ������������ֵ��������StatefulTradeBeanEʵ�֡�
*/
public interface StatefulShop extends javax.ejb.EJBObject {
/*
*����˵���������Ʒ
* @������value �ʽ���
* @���أ�
* @�쳣��RemoteException ��ϵͳͨ�ŷ�������ʱ
*/
  public void addGoods(int id,String goods,double value) throws Exception, RemoteException;
/*
*����˵�����Ƴ���Ʒ
* @������fund �ʽ���
* @���أ�
* @�쳣��RemoteException ��ϵͳͨ�ŷ�������ʱ
*/
  public void removeGoods(int id) throws Exception, RemoteException;
/*
*����˵�����쿴��Ʒ
* @������
* @���أ�Hastable ��Ʒ
* @�쳣��RemoteException ��ϵͳͨ�ŷ�������ʱ
*/
  public Vector  seeGoods() throws RemoteException;
}
