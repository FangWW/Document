package store;
import java.io.Serializable;

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
    //��ͼ�ν���ע�������
    gui.addSelectionPanelListeners(selectionPanelListeners);
    gui.addCustPanelListeners(custPanelListeners);
  }
  
  /** ���������*/
  public void addUserGestureListener(StoreController b) throws StoreException,RemoteException{
    storeControllers.add(b);
  }
  /** ��ͼ�ν�����չʾ����displayָ�������� */
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

  /** ˢ�½����ϵĿͻ���Ϣ*/
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
  
  /** ����ͼ�ν�����[��ѯ�ͻ�]��ť��ActionEvent�ļ����� */
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
  
  /** ����ͼ�ν�����[��ӿͻ�]��ť��ActionEvent�ļ����� */
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

  /** ����ͼ�ν�����[ɾ���ͻ�]��ť��ActionEvent�ļ����� */
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

  /** ����ͼ�ν�����[���¿ͻ�]��ť��ActionEvent�ļ����� */
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

  /** ����ͼ�ν�����[�ͻ���ϸ��Ϣ]��ť��ActionEvent�ļ����� */
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

  /** ����ͼ�ν�����[���пͻ��嵥]��ť��ActionEvent�ļ����� */
  transient ActionListener allCustsPageHandler=new ActionListener(){
     public void actionPerformed(ActionEvent e){
        StoreController sc;
        for(int i=0;i<storeControllers.size();i++){
          sc=storeControllers.get(i);
          sc.handleGetAllCustomersGesture();
        }

     }
  };
  
  /** ������������ͻ����custPan�ϵ����а�ť��ActionEvent�¼��ļ����� */  
  transient ActionListener custPanelListeners[] ={custGetHandler,custAddHandler,
   custDeleteHandler,custUpdateHandler};

  /** �������ѡ�����selPan�ϵ����а�ť��ActionEvent�¼��ļ����� */    
  transient ActionListener selectionPanelListeners[]={
     custDetailsPageHandler,allCustsPageHandler};
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
