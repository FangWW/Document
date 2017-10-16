package stock;
import java.rmi.*;
/** 客户端的远程对象接口 */
public interface StockQuote extends Remote{
    public void quote(String stockSymbol, double price)throws RemoteException;
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
