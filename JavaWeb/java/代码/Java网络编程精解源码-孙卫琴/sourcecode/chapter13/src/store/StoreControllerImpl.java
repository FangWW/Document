package store;
public class StoreControllerImpl implements StoreController{
  private StoreModel storeModel;
  private StoreView storeView;
  public StoreControllerImpl(StoreModel model, StoreView view ) {
    try{
      storeModel=model;
      storeView=view;
      view.addUserGestureListener(this); //����ͼע�����������
    }catch(Exception e){
      reportException(e);
    }
  }
  
  /** �����쳣��Ϣ */
  private void reportException(Object o){
    try{
      storeView.showDisplay(o);
    }catch(Exception e){
      System.out.println("StoreControllerImpl reportException"+e);
    }
  }
  
  /** �������ID��ѯ�ͻ��Ķ��� */
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
  
  /** ������ӿͻ��Ķ��� */
  public void handleAddCustomerGesture(Customer c){
    try{
      storeModel.addCustomer(c);
    }catch(Exception e){
       reportException(e);
    }
  }
  
  /** ����ɾ���ͻ��Ķ��� */
  public void handleDeleteCustomerGesture(Customer c){
    try{
      storeModel.deleteCustomer(c);
    }catch(Exception e){
      reportException(e);
    }
  }
  
  /** ������¿ͻ��Ķ��� */
  public void handleUpdateCustomerGesture(Customer c){
    try{
      storeModel.updateCustomer(c);
    }catch(Exception e){
      reportException(e);
    }
  }
  
  /** �����г����пͻ��嵥�Ķ��� */ 
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
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
