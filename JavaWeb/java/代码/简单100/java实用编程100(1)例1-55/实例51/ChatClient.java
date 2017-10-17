//�ļ�����ChatClient.java
/**
 * <p>Title: ���������</p>
 * <p>Description: ����һ��ʹ�����ݱ�ͨѶ��ʽ���������Ŀͻ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ChatClient.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class ChatClient{
 private DatagramSocket s;
 private InetAddress hostAddress;
 private byte[] buf = new byte[1000];
 private DatagramPacket dp = new DatagramPacket(buf,buf.length);

/**
 *<br>����˵����������������ʵ�ֽ����û�������������ͨѶ
 *<br>���������
 *<br>�������ͣ�
 */
  public ChatClient(){
   try{
       //ʹ�ù�����������ʹ�ñ����κο��ö˿ڵ����ݰ�Socket
       s = new DatagramSocket();
       //��ȡ����IP
       hostAddress = InetAddress.getByName("localhost");
       System.out.println("Client start............");
       while(true){
         String outMessage ="";  
        //��ȡ����
         BufferedReader stdin  = new BufferedReader(new InputStreamReader(System.in));
         try{
           outMessage = stdin.readLine();
         }catch(IOException ie){
           System.err.println("IO error!");
         }
         //������롰bye�����ʾ�˳�����
         if(outMessage.equals("bye")) break;
         String outString = "Client say: "+ outMessage;
         byte[] buf = outString.getBytes();
         //������ݣ���������
         DatagramPacket out = new DatagramPacket(buf,buf.length,hostAddress,ChatServer.PORT);
         s.send(out);
         //�ȴ�����������
         s.receive(dp);
         String rcvd = "rcvd from "+ dp.getAddress() + ", " + dp.getPort() + 
         ": "+ new String(dp.getData(),0,dp.getLength());
         System.out.println(rcvd);
         
      }
     }catch(UnknownHostException e){
       System.out.println("Can;t open socket");
       System.exit(1);
     }catch(SocketException e){
       System.out.println("Can;t open socket");
       e.printStackTrace();
       System.exit(1);
     }catch(IOException e){
       System.err.println("Communication error");
       e.printStackTrace();
       System.exit(1);
     }catch(Exception e){
       System.err.println("Communication error");
       e.printStackTrace();
       System.exit(1);
     }
     System.out.println("ChatClient over");
 }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
 public static void main(String[] args){
    new ChatClient();
 }
}
