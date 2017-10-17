package bmp;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 * <p>Title: Զ�̽ӿ�</p>
 * <p>Description: ����AccontBean��Զ�̽ӿڶ��塣Զ�̽ӿ��ж����˿ͻ�����Զ�̵���EJBean�ķ�������Щ��������
 *                 Ҫ�׳��쳣java.rmi.RemoteException֮�⣬��EJBean�еĶ�����һ�µġ���������EJBean��ʵ
 *                 ������ӿڣ������������Զ���������AccontBeanEʵ�ֵġ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: bmpTrade.java</p>
 * @author �Ž�
 * @version 1.0
 */
public interface bmpTrade extends EJBObject {
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
