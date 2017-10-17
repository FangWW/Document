public class Receiver {
  private int port=8000;
  private ServerSocket serverSocket;
  private static int stopWay=1;  //����ͨ�ŵķ�ʽ
  private final int NATURAL_STOP=1; //��Ȼ����
  private final int SUDDEN_STOP=2;  //ͻȻ��ֹ����
  private final int SOCKET_STOP=3;  //�ر�Socket���ٽ�������
  private final int INPUT_STOP=4;  //�ر����������ٽ�������
  private final int SERVERSOCKET_STOP=5;  //�ر�ServerSocket���ٽ�������

  public Receiver() throws IOException {
    serverSocket = new ServerSocket(port);
    System.out.println("�������Ѿ�����");
  }

  private BufferedReader getReader(Socket socket)throws IOException{
    InputStream socketIn = socket.getInputStream();
    return new BufferedReader(new InputStreamReader(socketIn));
  }

  public void receive() throws Exception{
    Socket socket=null;
    socket = serverSocket.accept();
    BufferedReader br =getReader(socket);
    
    for(int i=0;i<20;i++) {
       String msg=br.readLine();
       System.out.println("receive:"+msg);
       Thread.sleep(1000);
       if(i==2){  //��ֹ���򣬽���ͨ��
        if(stopWay==SUDDEN_STOP){
          System.out.println("ͻȻ��ֹ����");
          System.exit(0);
        }else if(stopWay==SOCKET_STOP){
          System.out.println("�ر�Socket����ֹ����");
          socket.close();
          break;
        }else if(stopWay==INPUT_STOP){
          System.out.println("�ر�����������ֹ����");
          socket.shutdownInput();
          break;
        }else if(stopWay==SERVERSOCKET_STOP){
          System.out.println("�ر�ServerSocket����ֹ����");
          serverSocket.close();
          break;
        } 
      }  
    }
    
    if(stopWay==NATURAL_STOP){
      socket.close();
      serverSocket.close(); 
    }
  }  
  
  public static void main(String args[])throws Exception {
    if(args.length>0)stopWay=Integer.parseInt(args[0]);
    new Receiver().receive();
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
