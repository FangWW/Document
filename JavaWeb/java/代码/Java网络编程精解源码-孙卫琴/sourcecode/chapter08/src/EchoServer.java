public class EchoServer {
  private int port=8000;
  private DatagramSocket socket;

  public EchoServer() throws IOException {
    socket=new DatagramSocket(port); //�뱾�ص�һ���̶��˿ڰ�
    System.out.println("����������");
  }

  public String echo(String msg) {
    return "echo:" + msg;
  }

  public void service() {
    while (true) {
      try {
        DatagramPacket packet=new DatagramPacket(new byte[512],512);
        socket.receive(packet);  //������������һ��EchoClient�����ݱ�
        String msg=new String(packet.getData(),0,packet.getLength());         
        System.out.println(packet.getAddress() + ":" +packet.getPort()
                            +">"+msg);
        
        packet.setData(echo(msg).getBytes());
        socket.send(packet);  //��EchoClient�ظ�һ�����ݱ�
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
