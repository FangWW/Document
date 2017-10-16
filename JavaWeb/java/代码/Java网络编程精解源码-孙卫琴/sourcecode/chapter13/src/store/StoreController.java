package store;
public interface StoreController {
  /** 处理查询客户的动作 */
  public void handleGetCustomerGesture(long id);
  /** 处理添加客户的动作 */
  public void handleAddCustomerGesture(Customer c);
  /** 处理删除客户的动作 */
  public void handleDeleteCustomerGesture(Customer c);
  /** 处理更新客户的动作 */
  public void handleUpdateCustomerGesture(Customer c);
  /** 处理列出所有客户清单的动作 */
  public void handleGetAllCustomersGesture();
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
