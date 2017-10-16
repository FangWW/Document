import java.net.*;
import java.io.*;
import echo.*;
public class EchoClient{
  public static void main(String args[])throws IOException{
    //设置URLStreamHandlerFactory
    URL.setURLStreamHandlerFactory(new EchoURLStreamHandlerFactory());
    //设置ContentHandlerFactory
    URLConnection.setContentHandlerFactory(new EchoContentHandlerFactory());
    URL url=new URL("echo://localhost:8000");
    EchoURLConnection connection=(EchoURLConnection)url.openConnection();
    connection.setDoOutput(true);
    PrintWriter pw=new PrintWriter(connection.getOutputStream(),true);
    while(true){
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       String msg=br.readLine();
       pw.println(msg);  //向服务器发送消息
       String echoMsg=(String)connection.getContent(); //读取服务器返回的消息
       System.out.println(echoMsg);
       if(echoMsg.equals("echo:bye")){
         connection.disconnect(); //断开连接
         break;
       }
    }
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
