//文件名：ChatClient.java
import java.net.*;
import java.io.*;
/**
 * <p>Title: 网络聊天吧</p>
 * <p>Description: 这是一个使用数据报通讯方式的聊天程序的客户端</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ChatClient.java</p>
 * @author 杜江
 * @version 1.0
 */
public class ChatClient{
 private DatagramSocket s;
 private InetAddress hostAddress;
 private byte[] buf = new byte[1000];
 private DatagramPacket dp = new DatagramPacket(buf,buf.length);

/**
 *<br>方法说明：构造器，这里实现接收用户输入和与服务器通讯
 *<br>输入参数：
 *<br>返回类型：
 */
  public ChatClient(){
   try{
       //使用构造器，创建使用本机任何可用端口的数据包Socket
       s = new DatagramSocket();
       //获取本地IP
       hostAddress = InetAddress.getByName("localhost");
       System.out.println("Client start............");
       while(true){
         String outMessage ="";  
        //读取输入
         BufferedReader stdin  = new BufferedReader(new InputStreamReader(System.in));
         try{
           outMessage = stdin.readLine();
         }catch(IOException ie){
           System.err.println("IO error!");
         }
         //如果输入“bye”则表示退出程序
         if(outMessage.equals("bye")) break;
         String outString = "Client say: "+ outMessage;
         byte[] buf = outString.getBytes();
         //打包数据，发送数据
         DatagramPacket out = new DatagramPacket(buf,buf.length,hostAddress,ChatServer.PORT);
         s.send(out);
         //等待服务器返回
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
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
 public static void main(String[] args){
    new ChatClient();
 }
}
