import java.net.*;
import java.io.*;
import java.util.*;
/**
 * <p>Title: 网络聊天吧</p>
 * <p>Description: 使用数据报创建的聊天服务器</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ChatServer.java</p>
 * @author 杜江
 * @version 1.0
 */
public class ChatServer{
 static final int PORT = 4000;//设置服务端口
 private byte[] buf = new byte[1000];
 private DatagramPacket dgp =new  DatagramPacket(buf,buf.length);
 private DatagramSocket sk;
/**
 *<br>方法说明：服务端构造器，实现读取用户输入和通讯
 *<br>输入参数：
 *<br>返回类型：
 */
 public ChatServer(){
   try{
     //实例化数据报
     sk = new DatagramSocket(PORT);
     System.out.println("Server start.................");
     while(true){
       //等待接收
       sk.receive(dgp);
       //获取接收信息
       String rcvd = new String(dgp.getData(),0,dgp.getLength())+ 
          ", from address: "+ dgp.getAddress()+
          ", port: "+ dgp.getPort();
       System.out.println(rcvd);
       String outMessage ="";  
        //读取输入
         BufferedReader stdin  = new BufferedReader(new InputStreamReader(System.in));
         try{
           outMessage = stdin.readLine();
         }catch(IOException ie){
           System.err.println("IO error!");
         }
       String outString = "Server say: "+ outMessage;
       //拷贝字符到缓存
       byte[] buf = outString.getBytes();
       //打包数据，发送回信息。
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
