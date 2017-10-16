package proxy1;
import java.util.Date;
public class HelloServiceProxy implements HelloService{
  private String host;
  private int port;
  
  public HelloServiceProxy(String host,int port){
     this.host=host;
     this.port=port;
  }
  public String echo(String msg)throws RemoteException{
    Connector connector=null;
    try{
      connector=new Connector(host,port);
      Call call=new Call("proxy1.HelloService","echo",new Class[]{String.class},new Object[]{msg}); 
      connector.send(call);
      call=(Call)connector.receive();
      Object result=call.getResult();
      if(result instanceof Throwable)
        throw new RemoteException((Throwable)result);
      else
        return (String)result;
    }catch(Exception e){
       throw new RemoteException(e);
    }finally{if(connector!=null)connector.close();}
  }

  public Date getTime()throws RemoteException{
    Connector connector=null;
    try{
      connector=new Connector(host,port);
      Call call=new Call("proxy1.HelloService","getTime",new Class[]{},new Object[]{}); 
      connector.send(call);
      call=(Call)connector.receive();
      Object result=call.getResult();
      if(result instanceof Throwable)
        throw new RemoteException((Throwable)result);
      else
        return (Date)result;
     }catch(Exception e){
       throw new RemoteException(e);
     }finally{if(connector!=null)connector.close();}
  }
} 


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
