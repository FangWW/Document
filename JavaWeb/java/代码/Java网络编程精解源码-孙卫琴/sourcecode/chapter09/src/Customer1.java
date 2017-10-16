import java.io.*;
public class Customer1 implements Serializable {
  private static int count; //用于计算Customer对象的数目
  private static final int MAX_COUNT=1000;
  private String name;
  private transient String password;
  
  static{
     System.out.println("调用Customer1类的静态代码块");
  }
  public Customer1(){
    System.out.println("调用Customer1类的不带参数的构造方法");
    count++;
  }
  public Customer1(String name, String password) {
    System.out.println("调用Customer1类的带参数的构造方法");
    this.name=name;
    this.password=password;
    count++;
  }
  public String toString() {
    return "count="+count
           +" MAX_COUNT="+MAX_COUNT
           +" name="+name
           +" password="+ password;
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
