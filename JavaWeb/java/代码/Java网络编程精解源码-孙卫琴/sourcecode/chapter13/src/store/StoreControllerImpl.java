package store;
import java.util.*;
public class StoreControllerImpl implements StoreController{
  private StoreModel storeModel;
  private StoreView storeView;
  public StoreControllerImpl(StoreModel model, StoreView view ) {
    try{
      storeModel=model;
      storeView=view;
      view.addUserGestureListener(this); //向视图注册控制器自身
    }catch(Exception e){
      reportException(e);
    }
  }
  
  /** 报告异常信息 */
  private void reportException(Object o){
    try{
      storeView.showDisplay(o);
    }catch(Exception e){
      System.out.println("StoreControllerImpl reportException"+e);
    }
  }
  
  /** 处理根据ID查询客户的动作 */
  public void handleGetCustomerGesture(long id){
    Customer cust=null;
    try{
      cust=storeModel.getCustomer(id);
      storeView.showDisplay(cust);
    }catch(Exception e){
       reportException(e);
       cust=new Customer(id);
       try{
         storeView.showDisplay(cust);
       }catch(Exception ex){
         reportException(ex);
       }
     }
  }
  
  /** 处理添加客户的动作 */
  public void handleAddCustomerGesture(Customer c){
    try{
      storeModel.addCustomer(c);
    }catch(Exception e){
       reportException(e);
    }
  }
  
  /** 处理删除客户的动作 */
  public void handleDeleteCustomerGesture(Customer c){
    try{
      storeModel.deleteCustomer(c);
    }catch(Exception e){
      reportException(e);
    }
  }
  
  /** 处理更新客户的动作 */
  public void handleUpdateCustomerGesture(Customer c){
    try{
      storeModel.updateCustomer(c);
    }catch(Exception e){
      reportException(e);
    }
  }
  
  /** 处理列出所有客户清单的动作 */ 
  public void handleGetAllCustomersGesture(){
    Set<Customer> custs;
    try{
      custs=storeModel.getAllCustomers();
      storeView.showDisplay(custs);
    }catch(Exception e){
       reportException(e);
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
