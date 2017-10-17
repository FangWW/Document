package stock;

public interface StockQuoteRegistry extends Remote{
  public void registerClient(StockQuote client)throws RemoteException;
  public void unregisterClient(StockQuote client)throws RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
