import java.io.*;
import java.net.*;
import java.util.*;
public class SimpleServer {
  public void send(Object object)throws IOException{
    ServerSocket serverSocket = new ServerSocket(8000);
    while(true){
      Socket socket=serverSocket.accept();
      OutputStream out=socket.getOutputStream();
      ObjectOutputStream oos=new ObjectOutputStream(out);
      oos.writeObject(object);
      oos.writeObject(object);
      oos.close();
      socket.close();
    }
  }
  public static void main(String args[])throws IOException {
    Object object=null;
    if(args.length>0 && args[0].equals("Date"))
      object=new Date();
    else if(args.length>0 && args[0].equals("Customer1"))
      object=new Customer1("Tom","1234");
    else if(args.length>0 && args[0].equals("Customer2")){
      Customer2 customer=new Customer2("Tom");
      Order2 order1=new Order2("number1",customer);
      Order2 order2=new Order2("number2",customer);
      customer.addOrder(order1);
      customer.addOrder(order2);
      object=customer;
    }else if(args.length>0 && args[0].equals("Customer3")){
      object=new Customer3("Tom","1234");
    }else if(args.length>0 && args[0].equals("Customer4")){
      Customer4 customer=new Customer4("Tom");
      Order4 order1=new Order4("number1",customer);
      Order4 order2=new Order4("number2",customer);
      customer.addOrder(order1);
      customer.addOrder(order2);
      object=customer;
    }else if(args.length>0 && args[0].equals("Customer5")){
      object=new Customer5("Tom",25);
    }else{
      object="Hello";
    }
    System.out.println("待发送的对象信息："+object);
    new SimpleServer().send(object);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
