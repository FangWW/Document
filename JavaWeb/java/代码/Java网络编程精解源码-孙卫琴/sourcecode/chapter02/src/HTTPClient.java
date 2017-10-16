import java.net.*;
import java.io.*;
public class HTTPClient {
  String host="www.javathinker.org";
  int port=80;
  Socket socket;
  
  public void createSocket()throws Exception{
    socket=new Socket("www.javathinker.org",80);
  }
  

  public void communicate()throws Exception{
    StringBuffer sb=new StringBuffer("GET "+"/index.jsp"+" HTTP/1.1\r\n");
    sb.append("Host: www.javathinker.org\r\n");
    sb.append("Accept: */*\r\n");
    sb.append("Accept-Language: zh-cn\r\n");
    sb.append("Accept-Encoding: gzip, deflate\r\n");
    sb.append("User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)\r\n");
    sb.append("Connection: Keep-Alive\r\n\r\n");

    //发出HTTP请求
    OutputStream socketOut=socket.getOutputStream();
    socketOut.write(sb.toString().getBytes());
    socket.shutdownOutput();  //关闭输出流
       
    //接收响应结果
    InputStream socketIn=socket.getInputStream();
    ByteArrayOutputStream buffer=new ByteArrayOutputStream();
    byte[] buff=new byte[1024];  
    int len=-1;
    while((len=socketIn.read(buff))!=-1){
      buffer.write(buff,0,len);
    }
    
    System.out.println(new String(buffer.toByteArray()));  //把字节数组转换为字符串


/*
    InputStream socketIn=socket.getInputStream();
    BufferedReader br=new BufferedReader(new InputStreamReader(socketIn));
    String data;
    while((data=br.readLine())!=null){
      System.out.println(data);
    }
*/        
    socket.close();
  }
  
  public static void main(String args[])throws Exception{
    HTTPClient client=new HTTPClient();
    client.createSocket();
    client.communicate();
  } 
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
