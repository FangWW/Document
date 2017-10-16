package remotecall;
import java.io.*;
import java.net.*;
import java.util.*;
public class SimpleClient {
  public void invoke()throws Exception{
    Socket socket = new Socket("localhost",8000);
    OutputStream out=socket.getOutputStream();
    ObjectOutputStream oos=new ObjectOutputStream(out);
    InputStream in=socket.getInputStream();
    ObjectInputStream ois=new ObjectInputStream(in);
 
    //Call call=new Call("remotecall.HelloService","getTime",new Class[]{},new Object[]{});
    Call call=new Call("remotecall.HelloService","echo",new Class[]{String.class},new Object[]{"Hello"}); 
    oos.writeObject(call);
    call=(Call)ois.readObject(); 
    System.out.println(call.getResult());

    ois.close();
    oos.close();
    socket.close();
  }
  public static void main(String args[])throws Exception {
    new SimpleClient().invoke(); 
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
