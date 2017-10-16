import java.io.*;
import java.util.*;

public class Customer4 implements Externalizable {
  private String name;
  private Set<Order4> orders=new HashSet<Order4>();
  static{
     System.out.println("调用Customer4类的静态代码块");
  }
  public Customer4(){
    System.out.println("调用Customer4类的不带参数的构造方法");
  }
  public Customer4(String name) {
    System.out.println("调用Customer4类的带参数的构造方法");
    this.name=name;
  }
  
  public void addOrder(Order4 order){
    orders.add(order);
  }
  
  public void writeExternal(ObjectOutput out)throws IOException{
    out.writeObject(name);
    out.writeObject(orders);
  }
  public void readExternal(ObjectInput in)throws IOException,ClassNotFoundException{
    name=(String)in.readObject();
    orders=(Set<Order4>)in.readObject();
  }
  public String toString() {
    String result=super.toString()+"\r\n"
           +orders+"\r\n";
    return result;
  }
}

class Order4 implements Externalizable {
  private String number;
  private Customer4 customer;
  public Order4(){
    System.out.println("调用Order4类的不带参数的构造方法");
  }
  public Order4(String number,Customer4 customer){
    System.out.println("调用Order4类的带参数的构造方法");
    this.number=number;
    this.customer=customer; 
  }

    public void writeExternal(ObjectOutput out)throws IOException{
    out.writeObject(number);
    out.writeObject(customer);
  }
  public void readExternal(ObjectInput in)throws IOException,ClassNotFoundException{
    number=(String)in.readObject();
    customer=(Customer4)in.readObject();
  }

}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
