package proxy1;
public class SimpleServer {
  private Map remoteObjects=new HashMap();
  
  public void register(String className,Object remoteObject){
    remoteObjects.put( className,remoteObject);
  } 
  public void service()throws Exception{
    ServerSocket serverSocket = new ServerSocket(8000);
    System.out.println("����������.");
    while(true){
      Socket socket=serverSocket.accept();
      InputStream in=socket.getInputStream();
      ObjectInputStream ois=new ObjectInputStream(in);
      OutputStream out=socket.getOutputStream();
      ObjectOutputStream oos=new ObjectOutputStream(out);

      Call call=(Call)ois.readObject();
      System.out.println(call);
      call=invoke(call);
      oos.writeObject(call);
      
      ois.close();
      oos.close();
      socket.close();
    }
  }

  public Call invoke(Call call){
    Object result=null;
    try{
      String className=call.getClassName();
      String methodName=call.getMethodName();
      Object[] params=call.getParams();
      Class classType=Class.forName(className); 
      Class[] paramTypes=call.getParamTypes();
      Method method=classType.getMethod(methodName,paramTypes);  
      Object remoteObject=remoteObjects.get(className);
      if(remoteObject==null){
        throw new Exception(className+"��Զ�̶��󲻴���");
      }else{
        result=method.invoke(remoteObject,params);
      }
    }catch(Exception e){result=e;}

    call.setResult(result);
    return call;
  }

  public static void main(String args[])throws Exception {
    SimpleServer server=new SimpleServer();
    server.register("proxy1.HelloService",new HelloServiceImpl());
    server.service();
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
