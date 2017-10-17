package stock;

public class StockQuoteRegistryImpl extends UnicastRemoteObject
           implements StockQuoteRegistry, Runnable{
  protected HashSet<StockQuote> clients;

  public StockQuoteRegistryImpl()throws RemoteException{
        clients = new HashSet<StockQuote>();
  }

  public void run(){
    //����һЩ��Ʊ����
    String[] symbols = new String[] {"SUNW", "MSFT", "DAL", "WUTK", "SAMY", "KATY"};
    Random rand = new Random();

    double values[] = new double[symbols.length];

    //Ϊÿ����Ʊ��������۸�
    for(int i=0; i < values.length; i++){
      values[i] = 25.0 + rand.nextInt(100);
    }

    for (;;){
      //���ȡ��һ����Ʊ
      int sym = rand.nextInt(symbols.length);
 
      // �޸Ĺ�Ʊ�ļ۸�
      int change = 100 - rand.nextInt(201);
      values[sym] = values[sym] + ((double) change) / 100.0;
      if (values[sym] < 0) values[sym] = 0.01;

      Iterator<StockQuote> iter = clients.iterator();
      while (iter.hasNext()){
        StockQuote client =  iter.next();
        try{
          client.quote(symbols[sym], values[sym]);
        }catch (Exception exc){
           System.out.println("ɾ��һ����Ч�Ŀͻ�");
           iter.remove();
        }
      }

      try { Thread.sleep(1000); } catch (Exception ignore) {}
    }
  }

  public void registerClient(StockQuote client)throws RemoteException{
    System.out.println("����һ���ͻ�");
    clients.add(client);
  }

  public void unregisterClient(StockQuote client)throws RemoteException{
    System.out.println("ɾ��һ���ͻ�");
    clients.remove(client);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
