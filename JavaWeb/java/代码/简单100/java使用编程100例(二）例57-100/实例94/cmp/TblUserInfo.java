package cmp;
import javax.ejb.*;

/**
 * <p>Title: CMPԶ�̽ӿ�</p>
 * <p>Description: ����̳�javax.ejb.EJBLocalObject�ͱ��ֶ��໥��Ӧ��setXXX()��getXXX()����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author �Ž�
 * @version 1.0
 */
public interface TblUserInfo extends javax.ejb.EJBLocalObject {
  //��ȡ�û�����
  public java.lang.Integer getId();
  //�����û���
  public void setName(java.lang.String name);
  //��ȡ�û���
  public java.lang.String getName();
  //���õ绰
  public void setPhone(java.lang.String phone);
  //��ȡ�绰
  public java.lang.String getPhone();
  //���ü�ͥסַ
  public void setHome(java.lang.String home);
  //��ȡ��ͥסַ
  public java.lang.String getHome();
  //��������
  public void setBrithday(java.sql.Date brithday);
  //��ȡ����
  public java.sql.Date getBrithday();
}
