package channel;
public class EchoServer {
  private int port=8000;
  private DatagramChannel channel;
  private final int MAX_SIZE=1024;

  public EchoServer() throws IOException {
    channel= DatagramChannel.open();
    DatagramSocket socket=channel.socket();
    SocketAddress localAddr=new InetSocketAddress(8000);
    socket.bind(localAddr);
    System.out.println("����������");
  }

  public String echo(String msg) {
    return "echo:" + msg;
  }

  public void service() {
    ByteBuffer receiveBuffer=ByteBuffer.allocate(MAX_SIZE);
    while (true) {
      try {
        receiveBuffer.clear(); 
        InetSocketAddress client=(InetSocketAddress)channel.receive(receiveBuffer);  //������������һ��EchoClient�����ݱ�
        receiveBuffer.flip();  
        String msg=Charset.forName("GBK").decode(receiveBuffer).toString();     
        System.out.println(client.getAddress() + ":" +client.getPort()
                            +">"+msg);
        
        channel.send(ByteBuffer.wrap(echo(msg).getBytes()),client);  //��EchoClient�ظ�һ�����ݱ�
      }catch (IOException e) {
         e.printStackTrace();
      }
    }
  }

  public static void main(String args[])throws IOException {
    new EchoServer().service();
  }
}



/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
