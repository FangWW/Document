import java.io.*;
import java.util.*;

public class ObjectSaver{

  public static void main(String agrs[]) throws Exception {
    ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("D:\\objectFile.obj"));

    String obj1="hello";
    Date obj2=new Date();
    Customer obj3=new Customer("Tom",20);
    //序列化对象
    out.writeObject(obj1);
    out.writeObject(obj2);
    out.writeObject(obj3);
    out.writeInt(123);
    out.close();
    
    //反序列化对象 
    ObjectInputStream in=new ObjectInputStream(new FileInputStream("D:\\objectFile.obj"));
    String obj11 = (String)in.readObject();
    System.out.println("obj11:"+obj11);
    System.out.println("obj11==obj1:"+(obj11==obj1));

    Date obj22 = (Date)in.readObject();
    System.out.println("obj22:"+obj22);
    System.out.println("obj22==obj2:"+(obj22==obj2));

    Customer obj33 = (Customer)in.readObject();
    System.out.println("obj33:"+obj33);
    System.out.println("obj33==obj3:"+(obj33==obj3));
    
    int var= in.readInt();
    System.out.println("var:"+var);

    in.close();
  }
}

class Customer implements Serializable{  
  private String name;
  private int age;
  
  public Customer(String name,int age){
    this.name=name;
    this.age=age;
  }

  public String toString(){return "name="+name+",age="+age;}
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
