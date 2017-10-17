//�Ựejb��ʵ���ࡣ
package cmp;

import javax.ejb.*;
import javax.rmi.PortableRemoteObject;
/*
*ʵ�����ࡣ����ʵ��SessionBean��*
*/
public class userinfoBean implements SessionBean {
  SessionContext sessionContext;
  TblUserInfoHome TUIHome;
  TblUserInfo  TUI;
/*
*����˵�������������userinfoHome.java�е����ӿ��е�create()�������Ӧ��
*���������Ĳ�����ͬ�����ͻ��˵������ӿڵ�userinfoHome.create()����ʱ��
*����������һ��EJBʵ��������������ejbCreate()��������������CMP��home�ӿڡ�
* @�쳣��CreateException ��ϵͳ����EJB����ʱ�׳�
*/
  public void ejbCreate() throws CreateException {
     try{
       Context ctx = new InitialContext();
       Object ref = ctx.lookup("TblUserInfo");
       TUIHome = (TblUserInfoHome)PortableRemoteObject.narrow(ref, TblUserInfoHome.class);
      }catch(Exception ex){
        System.out.println("create error!");
      }
  }
//ejb����ʵ�ֵķ���������û��ʹ�á�
  public void ejbRemove() { }
//ejb����ʵ�ֵķ���������û��ʹ�á�
  public void ejbActivate() { }
//ejb����ʵ�ֵķ���������û��ʹ�á�
  public void ejbPassivate() { }
//ejb����ʵ�ֵķ��������ûỰ�����ġ�
  public void setSessionContext(SessionContext sessionContext) {
    this.sessionContext = sessionContext;
  }
/*����˵����������ݼ�¼ 
* @������java.lang.String name �û���
* @������java.lang.String phone ��ϵ�绰
* @������java.lang.String home ��ͥסַ
* @������java.sql.Date brithday ��������
* @�쳣��Exception����CMP�Ľӿ�ʱ�׳�
*/
  public  void ist_info(java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday){
     try{
         TUI=TUIHome.create(name,phone,home,brithday);
     }catch(Exception ex){
         ex.printStackTrace();//��ӡ�����Ķ�ջ��Ϣ
     }
  }
/*����˵����ɾ��һ����¼ 
* @������java.lang.Integer id ����id
*/
  public  int del_info(java.lang.Integer id){
     try{
         TUI=TUIHome.findByPrimaryKey(id);
         if (TUI.getId() ==null)
         {  System.out.println("û�����ݣ�");
            return 1;
         }else{
           TUI.remove() ;
           return 0;
         } 
     }catch(Exception ex){
         ex.printStackTrace();//��ӡ�����Ķ�ջ��Ϣ
         return -1;
     }
  }
/*����˵�����޸�һ����¼ 
* @������java.lang.Integer id ����id
* @������java.lang.String name �û���
* @������java.lang.String phone ��ϵ�绰
* @������java.lang.String home ��ͥסַ
* @������java.sql.Date brithday ��������
*/
  public  int up_info(java.lang.Integer id, java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday){
     try{
         TUI=TUIHome.findByPrimaryKey(id);
         if (TUI.getId() ==null)
         {  System.out.println("û�����ݣ�");
            return 1;
         }else{
           TUI.setName(name) ;
           TUI.setPhone(phone);
           TUI.setHome(home);
           TUI.setBrithday(brithday);
           return 0;
         }
     }catch(Exception ex){
         ex.printStackTrace();//��ӡ�����Ķ�ջ��Ϣ
         return -1;
     }
   }
/*����˵����ͨ�����Ų�ѯ������Ϣ
* @������java.lang.Integer id ����id
* @���أ�Vector �û���Ϣ�����
*/
  public  java.util.Vector find_id(java.lang.Integer id){
     try{
         TUI=TUIHome.findByPrimaryKey(id);
         if (TUI.getId() ==null)
         {  System.out.println("û�����ݣ�");
            return null;
         }else{
           java.util.Vector vRst = new java.util.Vector();
           vRst.addElement(TUI.getId());
           vRst.addElement(TUI.getName());
           vRst.addElement(TUI.getPhone());
           vRst.addElement(TUI.getHome());
           vRst.addElement(TUI.getBrithday());
           return vRst;
         }
     }catch(Exception ex){
         ex.printStackTrace();//��ӡ�����Ķ�ջ��Ϣ
         return null;
     }
  }
  public int findUser(String username){
    try{
         TUI = TUIHome.findUserId(username);
         //TUI.getId();
         TUI.getName();
         TUI.getPhone();
         TUI.getHome();
         TUI.getBrithday();
         
         return TUI.getId().intValue();

     }catch(Exception ex){
         ex.printStackTrace();//��ӡ�����Ķ�ջ��Ϣ
         return -1;
     }
  }
}
