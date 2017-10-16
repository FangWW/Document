package stock;

import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class StockQuoteRegistryImpl extends UnicastRemoteObject
           implements StockQuoteRegistry, Runnable{
  protected HashSet<StockQuote> clients;

  public StockQuoteRegistryImpl()throws RemoteException{
        clients = new HashSet<StockQuote>();
  }

  public void run(){
    //创建一些股票代号
    String[] symbols = new String[] {"SUNW", "MSFT", "DAL", "WUTK", "SAMY", "KATY"};
    Random rand = new Random();

    double values[] = new double[symbols.length];

    //为每个股票分配任意价格
    for(int i=0; i < values.length; i++){
      values[i] = 25.0 + rand.nextInt(100);
    }

    for (;;){
      //随机取出一个股票
      int sym = rand.nextInt(symbols.length);
 
      // 修改股票的价格
      int change = 100 - rand.nextInt(201);
      values[sym] = values[sym] + ((double) change) / 100.0;
      if (values[sym] < 0) values[sym] = 0.01;

      Iterator<StockQuote> iter = clients.iterator();
      while (iter.hasNext()){
        StockQuote client =  iter.next();
        try{
          client.quote(symbols[sym], values[sym]);
        }catch (Exception exc){
           System.out.println("删除一个无效的客户");
           iter.remove();
        }
      }

      try { Thread.sleep(1000); } catch (Exception ignore) {}
    }
  }

  public void registerClient(StockQuote client)throws RemoteException{
    System.out.println("加入一个客户");
    clients.add(client);
  }

  public void unregisterClient(StockQuote client)throws RemoteException{
    System.out.println("删除一个客户");
    clients.remove(client);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
