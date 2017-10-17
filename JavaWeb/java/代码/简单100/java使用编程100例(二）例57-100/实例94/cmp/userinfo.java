
package cmp;
import javax.ejb.*;
/**
 * <p>Title: �ỰEJBԶ�̿ڳ���</p>
 * <p>Description: ����ỰEJBʹ�÷���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: userinfo.java</p>
 * @author �Ž�
 * @version 1.0
 */
public interface userinfo extends javax.ejb.EJBObject {
/*����˵��������һ�����ݼ�¼ 
* @������java.lang.String name �û���
* @������java.lang.String phone ��ϵ�绰
* @������java.lang.String home ��ͥסַ
* @������java.sql.Date brithday ��������
* @�쳣��RemoteException��ϵͳͨѶ��������ʱ�׳�
*/
  public  void ist_info(java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday) throws RemoteException;
/*����˵����ɾ��һ����¼ 
* @������java.lang.Integer id ����id
* @�쳣��RemoteException��ϵͳͨѶ��������ʱ�׳�
*/
  public  int del_info(java.lang.Integer id) throws RemoteException;
/*����˵�����޸�һ����¼ 
* @������java.lang.Integer id ����id
* @������java.lang.String name �û���
* @������java.lang.String phone ��ϵ�绰
* @������java.lang.String home ��ͥסַ
* @������java.sql.Date brithday ��������
* @�쳣��RemoteException��ϵͳͨѶ��������ʱ�׳�
*/
  public  int up_info(java.lang.Integer id, java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday) throws RemoteException;
/*����˵������ѯһ����¼ 
* @������java.lang.Integer id ����id
* @�쳣��RemoteException��ϵͳͨѶ��������ʱ�׳�
*/
  public  Vector find_id(java.lang.Integer id) throws RemoteException;
  public  int findUser(String username) throws RemoteException;
}
