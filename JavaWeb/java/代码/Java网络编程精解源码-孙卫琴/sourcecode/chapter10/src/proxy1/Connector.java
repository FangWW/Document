package proxy1;
public class Connector {
  private String host;
  private int port;
  private Socket skt;
  private InputStream is;
  private ObjectInputStream ois;
  private OutputStream os;
  private ObjectOutputStream oos;

  public Connector(String host,int port)throws Exception{
     this.host=host;
     this.port=port;
     connect(host,port);
  }

  public void send(Object obj)throws Exception{
    oos.writeObject(obj);
  }
  public Object receive() throws Exception{
    return ois.readObject();
  }
  public void connect()throws Exception{
    connect(host,port);
  }
  public void connect(String host,int port)throws Exception{
    skt=new Socket(host,port);
    os=skt.getOutputStream();
    oos=new ObjectOutputStream(os);
    is=skt.getInputStream();
    ois=new ObjectInputStream(is);
  }
  public void close(){
    try{
    }finally{
      try{
        ois.close();
        oos.close();
        skt.close();
      }catch(Exception e){
        System.out.println("Connector.close: "+e);
      }
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
