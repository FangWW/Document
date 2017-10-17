package cmp;
import javax.ejb.*;
/**
 * <p>Title: CMP���ӿ�</p>
 * <p>Description: ����̳�javax.ejb.EJBLocalHome </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: TblUserInfoHome.java</p>
 * @author �Ž�
 * @version 1.0
 */
public interface TblUserInfoHome extends javax.ejb.EJBLocalHome {
/*����˵��������ʵ�ֵķ�������TblUserInfoBean��ejbCreate������Ӧ��
* @������java.lang.String name �û���
* @������java.lang.String phone ��ϵ�绰
* @������java.lang.String home ��ͥסַ
* @������java.sql.Date brithday ��������
* @�쳣��CreateException ����EJB����ʱ�׳�
*/
  public TblUserInfo create(java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday) throws CreateException;
/*����˵���������������󣬷����û���Ϣ��
* @������java.lang.Integer id ����id
* @�쳣��FinderException����������ʱ�׳�
*/
  public TblUserInfo findByPrimaryKey(java.lang.Integer id) throws FinderException;
  public TblUserInfo findUserId(String uaername) throws FinderException;
}
