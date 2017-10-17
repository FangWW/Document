package store;
public interface StoreModel extends Remote {
  /** ע����ͼ���Ա㵱ģ���޸������ݿ��еĿͻ���Ϣʱ�����Իص���ͼ��ˢ�½���ķ��� */
  public void addChangeListener(StoreView sv) throws StoreException,RemoteException;
  /** �����ݿ������һ���µĿͻ� */
  public void addCustomer(Customer cust) throws StoreException,RemoteException;
  /** �����ݿ���ɾ��һ���ͻ� */ 
  public void deleteCustomer(Customer cust) throws StoreException,RemoteException;
  /** �������ݿ��еĿͻ� */ 
  public void updateCustomer(Customer cust) throws StoreException,RemoteException;
  /** ���ݲ���id�����ͻ� */
  public Customer getCustomer(long id) throws StoreException,RemoteException;
  /** �������ݿ������еĿͻ��嵥 */
  public Set<Customer> getAllCustomers() throws StoreException,RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
