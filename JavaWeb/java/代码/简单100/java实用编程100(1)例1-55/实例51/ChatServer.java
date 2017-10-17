/**
 * <p>Title: ���������</p>
 * <p>Description: ʹ�����ݱ����������������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ChatServer.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class ChatServer{
 static final int PORT = 4000;//���÷���˿�
 private byte[] buf = new byte[1000];
 private DatagramPacket dgp =new  DatagramPacket(buf,buf.length);
 private DatagramSocket sk;
/**
 *<br>����˵��������˹�������ʵ�ֶ�ȡ�û������ͨѶ
 *<br>���������
 *<br>�������ͣ�
 */
 public ChatServer(){
   try{
     //ʵ�������ݱ�
     sk = new DatagramSocket(PORT);
     System.out.println("Server start.................");
     while(true){
       //�ȴ�����
       sk.receive(dgp);
       //��ȡ������Ϣ
       String rcvd = new String(dgp.getData(),0,dgp.getLength())+ 
          ", from address: "+ dgp.getAddress()+
          ", port: "+ dgp.getPort();
       System.out.println(rcvd);
       String outMessage ="";  
        //��ȡ����
         BufferedReader stdin  = new BufferedReader(new InputStreamReader(System.in));
         try{
           outMessage = stdin.readLine();
         }catch(IOException ie){
           System.err.println("IO error!");
         }
       String outString = "Server say: "+ outMessage;
       //�����ַ�������
       byte[] buf = outString.getBytes();
       //������ݣ����ͻ���Ϣ��
       DatagramPacket out = new DatagramPacket(buf,buf.length,dgp.getAddress(),dgp.getPort());
       sk.send(out);
     }
    }catch(SocketException e){
      System.err.println("Can't open socket");
      System.exit(1);
    }catch(IOException e){
     System.err.println("Communication error");
     e.printStackTrace();
     System.exit(1);
    }
 }
 public static void main(String[] args){
   new ChatServer();
 }
}
