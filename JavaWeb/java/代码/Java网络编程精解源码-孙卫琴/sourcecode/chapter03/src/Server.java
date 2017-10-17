public class Server {
  private int port=8000;
  private ServerSocket serverSocket;

  public Server() throws IOException {
    serverSocket = new ServerSocket(port,3);  //����������еĳ���Ϊ3
    System.out.println("����������");
  }

  public void service() {
    while (true) {
      Socket socket=null;
      try {
        socket = serverSocket.accept();  //���������������ȡ��һ������
        System.out.println("New connection accepted " +
        socket.getInetAddress() + ":" +socket.getPort());
      }catch (IOException e) {
         e.printStackTrace();
      }finally {
         try{
           if(socket!=null)socket.close();
         }catch (IOException e) {e.printStackTrace();}
      }
    }
  }

  public static void main(String args[])throws Exception {
    Server server=new Server();
    Thread.sleep(60000*10);  //˯��ʮ����
    //server.service();
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
