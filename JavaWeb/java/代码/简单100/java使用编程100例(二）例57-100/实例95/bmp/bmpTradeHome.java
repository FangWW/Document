package bmp;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;
/**
 * <p>Title: ���ӿ�</p>
 * <p>Description: ����bmpTradeBean�����ӿڶ��壬����ӿ��Ǳ�EJB������������bmpTradeBeanʵ�ֵ�
 *                ������ֻ�趨��EJB�����ķ�������Щ����Ҫ��EJBean�е�"ejbCreate"������Ӧ��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: bmpTradeHome.java</p>
 * @author �Ž�
 * @version 1.0
 */
public interface bmpTradeHome extends EJBHome {
   /**
   * ���������"TblUserInfoBean.java"�ж���ĵ�Bean��"ejbCreate"�������Ӧ
   * �����������Ĳ���Ӧ����ͬ�����ͻ��˵���"TblUserInfoHome.create()"����ʱ��EJB����
   * ���ҵ�EJBean��ʵ��������������"ejbCreate()"������
   * @���� accountID         String �˺�ID
   * @���� initialBalance    double ��ʼ������ֵ
   * @���� bmpTrade Զ�̶���
   * @�쳣 javax.ejb.CreateException      ����bean����ʱ�׳����쳣
   * @�쳣 RemoteException ��ϵͳͨѶ��������ʱ�׳�
   */
  public bmpTrade create(String accountId, double initialBalance)
    throws CreateException, RemoteException;
 /**
   * �����������󣬷����˺Ŷ���
   * @���� primaryKey   ����
   * @���� TblUserInfo �˺�
   * @�쳣 javax.ejb.FinderException   �������ݿ�����׳����쳣
   * @�쳣 RemoteException ��ϵͳͨѶ��������ʱ�׳�
   */
  public bmpTrade findByPrimaryKey(String primaryKey)
    throws FinderException, RemoteException;
  /**
   * �ҵ����н���ֵ����balanceGreaterThan���˺�
   * @���� Enumeration �����˺�ö��
   * @���� double balanceGreaterThan,�����Ľ���ֵ
   * @�쳣 javax.ejb.FinderException   �������ݿ�����׳����쳣
   * @�쳣 RemoteException ��ϵͳͨѶ��������ʱ�׳�
   */
  public Collection findBigAccounts(double balanceGreaterThan)
    throws FinderException, RemoteException;
}
