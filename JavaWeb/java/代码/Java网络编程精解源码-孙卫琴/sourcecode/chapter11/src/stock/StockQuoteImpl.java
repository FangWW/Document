 
package stock;

public class StockQuoteImpl extends UnicastRemoteObject
                                      implements StockQuote{
  public StockQuoteImpl()throws RemoteException{}

  public void quote(String symbol, double value)throws RemoteException{
    System.out.println(symbol+": "+value);
  }
}
                


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
