package multithread4;
public class EchoServer {
  private int port=8000;
  private ServerSocket serverSocket;
  private ExecutorService executorService; //�̳߳�
  private final int POOL_SIZE=4;  //����CPUʱ�̳߳��й����̵߳���Ŀ
  
  private int portForShutdown=8001;  //���ڼ����رշ���������Ķ˿�
  private ServerSocket serverSocketForShutdown;
  private boolean isShutdown=false; //�������Ƿ��Ѿ��ر�

  private Thread shutdownThread=new Thread(){   //����رշ��������߳�
    public void start(){
      this.setDaemon(true);  //����Ϊ�ػ��̣߳�Ҳ��Ϊ��̨�̣߳�
      super.start();
    }

    public void run(){
      while (!isShutdown) {
        Socket socketForShutdown=null;
        try {
          socketForShutdown= serverSocketForShutdown.accept();
          BufferedReader br = new BufferedReader(
                            new InputStreamReader(socketForShutdown.getInputStream()));
          String command=br.readLine();
         if(command.equals("shutdown")){
            long beginTime=System.currentTimeMillis(); 
            socketForShutdown.getOutputStream().write("���������ڹر�\r\n".getBytes());
            isShutdown=true;
            //����ر��̳߳�
//�̳߳ز��ٽ����µ����񣬵��ǻ����ִ���깤�����������е�����
            executorService.shutdown();  
            
            //�ȴ��ر��̳߳أ�ÿ�εȴ��ĳ�ʱʱ��Ϊ30��
            while(!executorService.isTerminated())
              executorService.awaitTermination(30,TimeUnit.SECONDS); 
            
            serverSocket.close(); //�ر���EchoClient�ͻ�ͨ�ŵ�ServerSocket 
            long endTime=System.currentTimeMillis(); 
            socketForShutdown.getOutputStream().write(("�������Ѿ��رգ�"+
                "�رշ���������"+(endTime-beginTime)+"����\r\n").getBytes());
            socketForShutdown.close();
            serverSocketForShutdown.close();
            
          }else{
            socketForShutdown.getOutputStream().write("���������\r\n".getBytes());
            socketForShutdown.close();
          }  
        }catch (Exception e) {
           e.printStackTrace();
        } 
      } 
    }
  };

  public EchoServer() throws IOException {
    serverSocket = new ServerSocket(port);
    serverSocket.setSoTimeout(60000); //�趨�ȴ��ͻ����ӵĳ���ʱ��Ϊ60��
    serverSocketForShutdown = new ServerSocket(portForShutdown);

    //�����̳߳�
    executorService= Executors.newFixedThreadPool( 
	    Runtime.getRuntime().availableProcessors() * POOL_SIZE);
    
    shutdownThread.start(); //��������رշ��������߳�
    System.out.println("����������");
  }
  
  public void service() {
    while (!isShutdown) {
      Socket socket=null;
      try {
        socket = serverSocket.accept();  //���ܻ��׳�SocketTimeoutException��SocketException
        socket.setSoTimeout(60000);  //�ѵȴ��ͻ��������ݵĳ�ʱʱ����Ϊ60��          
        executorService.execute(new Handler(socket));  //���ܻ��׳�RejectedExecutionException
      }catch(SocketTimeoutException e){
         //���ش���ȴ��ͻ�����ʱ���ֵĳ�ʱ�쳣
      }catch(RejectedExecutionException e){
         try{
           if(socket!=null)socket.close();
         }catch(IOException x){}
         return;
      }catch(SocketException e) {
         //�����������ִ��serverSocket.accept()����ʱ��
         //ServerSocket��ShutdownThread�̹߳رն����µ��쳣�����˳�service()����
         if(e.getMessage().indexOf("socket closed")!=-1)return;
       }catch(IOException e) {
         e.printStackTrace();
      }
    }
  }

  public static void main(String args[])throws IOException {
    new EchoServer().service();
  }
}
class Handler implements Runnable{
  private Socket socket;
  public Handler(Socket socket){
    this.socket=socket;
  }
  private PrintWriter getWriter(Socket socket)throws IOException{
    OutputStream socketOut = socket.getOutputStream();
    return new PrintWriter(socketOut,true);
  }
  private BufferedReader getReader(Socket socket)throws IOException{
    InputStream socketIn = socket.getInputStream();
    return new BufferedReader(new InputStreamReader(socketIn));
  }
  public String echo(String msg) {
    return "echo:" + msg;
  }
  public void run(){
    try {
      System.out.println("New connection accepted " +
      socket.getInetAddress() + ":" +socket.getPort());
      BufferedReader br =getReader(socket);
      PrintWriter pw = getWriter(socket);

      String msg = null;
      while ((msg = br.readLine()) != null) {
        System.out.println(msg);
        pw.println(echo(msg));
        if (msg.equals("bye"))
          break;
      }
    }catch (IOException e) {
       e.printStackTrace();
    }finally {
       try{
         if(socket!=null)socket.close();
       }catch (IOException e) {e.printStackTrace();}
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
