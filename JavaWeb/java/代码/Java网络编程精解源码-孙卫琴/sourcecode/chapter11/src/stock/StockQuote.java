package stock;
/** �ͻ��˵�Զ�̶���ӿ� */
public interface StockQuote extends Remote{
    public void quote(String stockSymbol, double price)throws RemoteException;
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
