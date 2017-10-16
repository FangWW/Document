package store;
import java.util.*;
import java.io.Serializable;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.rmi.*;
import java.rmi.server.*;

public class StoreViewImpl extends UnicastRemoteObject
implements StoreView,Serializable{
  private transient StoreGui gui;
  private StoreModel storemodel;
  private Object display;

  private ArrayList<StoreController> storeControllers=new ArrayList<StoreController>(10);

  public StoreViewImpl(StoreModel model)throws RemoteException {
    try{
       storemodel=model;
       model.addChangeListener(this);

    }catch(Exception e){
      System.out.println("StoreViewImpl constructor "+e);
    }

    gui=new StoreGui();
    //向图形界面注册监听器
    gui.addSelectionPanelListeners(selectionPanelListeners);
    gui.addCustPanelListeners(custPanelListeners);
  }
  
  /** 加入控制器*/
  public void addUserGestureListener(StoreController b) throws StoreException,RemoteException{
    storeControllers.add(b);
  }
  /** 在图形界面上展示参数display指定的数据 */
  public void showDisplay(Object display) throws StoreException,RemoteException{
    if(!(display instanceof Exception))this.display=display;

    if(display instanceof Customer){
       gui.refreshCustPane((Customer)display);
    }

    if(display instanceof Set){
       gui.refreshAllCustPan((Set<Customer>)display);
    }

    if(display instanceof Exception){
         gui.updateLog(((Exception)display).getMessage());
    }

  }

  /** 刷新界面上的客户信息*/
  public void handleCustomerChange(Customer cust)throws StoreException,RemoteException{
     long cIdOnPan=-1;

    try{
      if(display instanceof Set){
         gui.refreshAllCustPan(storemodel.getAllCustomers());
         return;
      }
      if(display instanceof Customer){
          cIdOnPan=gui.getCustIdOnCustPan();
          if(cIdOnPan!=cust.getId())return;

          gui.refreshCustPane(cust);
      }
    }catch(Exception e){
      System.out.println("StoreViewImpl processCustomer "+e);
    }
  }
  
  /** 监听图形界面上[查询客户]按钮的ActionEvent的监听器 */
  transient ActionListener custGetHandler=new ActionListener(){
     public void actionPerformed(ActionEvent e){
        StoreController sc;
        long custId;
        custId=gui.getCustIdOnCustPan();

        for(int i=0;i<storeControllers.size();i++){
          sc=storeControllers.get(i);
          sc.handleGetCustomerGesture(custId);
        }
     }
  };
  
  /** 监听图形界面上[添加客户]按钮的ActionEvent的监听器 */
  transient ActionListener custAddHandler=new ActionListener(){
     public void actionPerformed(ActionEvent e){
        StoreController sc;
        Customer cust;
        cust=gui.getCustomerOnCustPan();

        for(int i=0;i<storeControllers.size();i++){
          sc=storeControllers.get(i);
          sc.handleAddCustomerGesture(cust);
        }
     }
  };

  /** 监听图形界面上[删除客户]按钮的ActionEvent的监听器 */
  transient ActionListener custDeleteHandler=new ActionListener(){
     public void actionPerformed(ActionEvent e){
        StoreController sc;
        Customer cust;
        cust=gui.getCustomerOnCustPan();

        for(int i=0;i<storeControllers.size();i++){
          sc=storeControllers.get(i);
          sc.handleDeleteCustomerGesture(cust);
        }
     }
  };

  /** 监听图形界面上[更新客户]按钮的ActionEvent的监听器 */
  transient ActionListener custUpdateHandler=new ActionListener(){
     public void actionPerformed(ActionEvent e){
        StoreController sc;
        Customer cust;
        cust=gui.getCustomerOnCustPan();

        for(int i=0;i<storeControllers.size();i++){
          sc=storeControllers.get(i);
          sc.handleUpdateCustomerGesture(cust);
        }
     }
  };

  /** 监听图形界面上[客户详细信息]按钮的ActionEvent的监听器 */
  transient ActionListener custDetailsPageHandler=new ActionListener(){
     public void actionPerformed(ActionEvent e){
        StoreController sc;
        long custId;
        custId=gui.getCustIdOnCustPan();
        if(custId==-1){
          try{
            showDisplay(new Customer(-1));
          }catch(Exception ex){ex.printStackTrace();}
        }else{ 
          for(int i=0;i<storeControllers.size();i++){
            sc=storeControllers.get(i);
            sc.handleGetCustomerGesture(custId);
          }
        } 
     }
  };

  /** 监听图形界面上[所有客户清单]按钮的ActionEvent的监听器 */
  transient ActionListener allCustsPageHandler=new ActionListener(){
     public void actionPerformed(ActionEvent e){
        StoreController sc;
        for(int i=0;i<storeControllers.size();i++){
          sc=storeControllers.get(i);
          sc.handleGetAllCustomersGesture();
        }

     }
  };
  
  /** 负责监听单个客户面板custPan上的所有按钮的ActionEvent事件的监听器 */  
  transient ActionListener custPanelListeners[] ={custGetHandler,custAddHandler,
   custDeleteHandler,custUpdateHandler};

  /** 负责监听选择面板selPan上的所有按钮的ActionEvent事件的监听器 */    
  transient ActionListener selectionPanelListeners[]={
     custDetailsPageHandler,allCustsPageHandler};
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
