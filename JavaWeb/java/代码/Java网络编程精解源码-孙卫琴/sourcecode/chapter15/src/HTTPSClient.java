import java.net.*;
import java.io.*;
import javax.net.ssl.*;
import java.security.*;

public class HTTPSClient {
  String host="www.usps.com";
  int port=443;
  SSLSocketFactory factory;
  SSLSocket socket;

  public void createSocket()throws Exception{
    factory=(SSLSocketFactory)SSLSocketFactory.getDefault();
    socket=(SSLSocket)factory.createSocket(host,port);
    String[] supported=socket.getSupportedCipherSuites();
    socket.setEnabledCipherSuites(supported);
  }
  
  public void communicate()throws Exception{
    StringBuffer sb=new StringBuffer("GET http://"+host+"/ HTTP/1.1\r\n");
    sb.append("Host:"+host+"\r\n");
    sb.append("Accept: */*\r\n");
    sb.append("\r\n");

    //发出HTTP请求
    OutputStream socketOut=socket.getOutputStream();
    socketOut.write(sb.toString().getBytes());
        
    //接收响应结果
    InputStream socketIn=socket.getInputStream();
    ByteArrayOutputStream buffer=new ByteArrayOutputStream();
    byte[] buff=new byte[1024];  
    int len=-1;
    while((len=socketIn.read(buff))!=-1){
        buffer.write(buff,0,len);
    }
    //把字节数组转换为字符串，并且只显示部分内容
    System.out.println(new String(buffer.toByteArray()).substring(1,1000));  
    
    socket.close();
  } 

  public static void main(String args[])throws Exception{
    HTTPSClient client=new HTTPSClient();
    client.createSocket();
    client.communicate();
  } 
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
