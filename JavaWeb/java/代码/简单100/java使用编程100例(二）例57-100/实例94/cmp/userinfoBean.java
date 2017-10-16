//会话ejb的实现类。
package cmp;

import javax.ejb.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;
/*
*实例化类。必需实现SessionBean。*
*/
public class userinfoBean implements SessionBean {
  SessionContext sessionContext;
  TblUserInfoHome TUIHome;
  TblUserInfo  TUI;
/*
*方法说明：这个方法与userinfoHome.java中的主接口中的create()方法相对应，
*两个方法的参数相同。当客户端调用主接口的userinfoHome.create()方法时，
*容器将分配一个EJB实例，并调用它的ejbCreate()方法。本例创建CMP的home接口。
* @异常：CreateException 当系统创建EJB出错时抛出
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
//ejb必需实现的方法。本例没有使用。
  public void ejbRemove() { }
//ejb必需实现的方法。本例没有使用。
  public void ejbActivate() { }
//ejb必需实现的方法。本例没有使用。
  public void ejbPassivate() { }
//ejb必需实现的方法。设置会话上下文。
  public void setSessionContext(SessionContext sessionContext) {
    this.sessionContext = sessionContext;
  }
/*功能说明：添加数据记录 
* @参数：java.lang.String name 用户名
* @参数：java.lang.String phone 联系电话
* @参数：java.lang.String home 家庭住址
* @参数：java.sql.Date brithday 出生日期
* @异常：Exception创建CMP的接口时抛出
*/
  public  void ist_info(java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday){
     try{
         TUI=TUIHome.create(name,phone,home,brithday);
     }catch(Exception ex){
         ex.printStackTrace();//打印出错点的堆栈信息
     }
  }
/*功能说明：删除一条记录 
* @参数：java.lang.Integer id 主键id
*/
  public  int del_info(java.lang.Integer id){
     try{
         TUI=TUIHome.findByPrimaryKey(id);
         if (TUI.getId() ==null)
         {  System.out.println("没有数据！");
            return 1;
         }else{
           TUI.remove() ;
           return 0;
         } 
     }catch(Exception ex){
         ex.printStackTrace();//打印出错点的堆栈信息
         return -1;
     }
  }
/*功能说明：修改一条记录 
* @参数：java.lang.Integer id 主键id
* @参数：java.lang.String name 用户名
* @参数：java.lang.String phone 联系电话
* @参数：java.lang.String home 家庭住址
* @参数：java.sql.Date brithday 出生日期
*/
  public  int up_info(java.lang.Integer id, java.lang.String name, java.lang.String phone, java.lang.String home, java.sql.Date brithday){
     try{
         TUI=TUIHome.findByPrimaryKey(id);
         if (TUI.getId() ==null)
         {  System.out.println("没有数据！");
            return 1;
         }else{
           TUI.setName(name) ;
           TUI.setPhone(phone);
           TUI.setHome(home);
           TUI.setBrithday(brithday);
           return 0;
         }
     }catch(Exception ex){
         ex.printStackTrace();//打印出错点的堆栈信息
         return -1;
     }
   }
/*功能说明：通过ｉｄ号查询数据信息
* @参数：java.lang.Integer id 主键id
* @返回：Vector 用户信息结果集
*/
  public  java.util.Vector find_id(java.lang.Integer id){
     try{
         TUI=TUIHome.findByPrimaryKey(id);
         if (TUI.getId() ==null)
         {  System.out.println("没有数据！");
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
         ex.printStackTrace();//打印出错点的堆栈信息
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
         ex.printStackTrace();//打印出错点的堆栈信息
         return -1;
     }
  }
}
