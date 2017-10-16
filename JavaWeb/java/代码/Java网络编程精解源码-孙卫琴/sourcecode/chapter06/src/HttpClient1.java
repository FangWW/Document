import java.net.*;
import java.io.*;
public class HttpClient1 {
  public static void main(String args[])throws IOException{
    URL url=new URL("http://www.javathinker.org/hello.htm");
       
    //接收响应结果
    InputStream in=url.openStream();
    ByteArrayOutputStream buffer=new ByteArrayOutputStream();
    byte[] buff=new byte[1024];  
    int len=-1;

    while((len=in.read(buff))!=-1){
      buffer.write(buff,0,len);
    }
     
    System.out.println(new String(buffer.toByteArray()));  //把字节数组转换为字符串
  } 
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
