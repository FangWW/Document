public class ReceiveChannel {
  public static void main(String args[])throws Exception {
    final int ENOUGH_SIZE=1024;
    final int SMALL_SIZE=4;
    
    boolean isBlocked=true;
    int size=ENOUGH_SIZE;
    
    if(args.length>0){
      int opt=Integer.parseInt(args[0]);
      switch(opt){
        case 1: isBlocked=true;size=ENOUGH_SIZE;break;
        case 2: isBlocked=true;size=SMALL_SIZE;break;
        case 3: isBlocked=false;size=ENOUGH_SIZE;break;
        case 4: isBlocked=false;size=SMALL_SIZE;break;
      }
    }   

    DatagramChannel channel= DatagramChannel.open();
    channel.configureBlocking(isBlocked);
    ByteBuffer buffer=ByteBuffer.allocate(size);
    DatagramSocket socket=channel.socket();
    SocketAddress localAddr=new InetSocketAddress(8000);
    socket.bind(localAddr);
    
    while(true){
      System.out.println("��ʼ�������ݱ�");
      SocketAddress remoteAddr=channel.receive(buffer);
      if(remoteAddr==null){
        System.out.println("û�н��յ����ݱ�");
      }else{
        buffer.flip();
        System.out.println("���յ������ݱ��Ĵ�СΪ"+buffer.remaining());
      } 
      Thread.sleep(500);
    }
  }
}



/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
