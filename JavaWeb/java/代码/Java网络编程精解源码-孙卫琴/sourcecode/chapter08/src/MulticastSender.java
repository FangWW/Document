public class MulticastSender {
  public static void main(String[] args) throws Exception{
    InetAddress group=InetAddress.getByName("224.0.0.1");
    int port=4000;
    MulticastSocket ms = null;
  
    try {
      ms = new MulticastSocket(port);
      ms.joinGroup(group);
      while (true) {
        String message = "Hello " + new java.util.Date(); 
        byte[] buffer=message.getBytes(); 
        DatagramPacket dp = new DatagramPacket(buffer, buffer.length,group,port);
        ms.send(dp);  
        System.out.println("�������ݱ��� "+group+":"+port);
        Thread.sleep(1000);
      }
    }catch (IOException e) {
      e.printStackTrace(); 
    }finally {
      if (ms != null) {
        try {
          ms.leaveGroup(group);
          ms.close();
        }
        catch (IOException e) {} 
      }
    } 
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
