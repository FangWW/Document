package check;
import java.io.*;
public class Customer implements Serializable {
  private int age;
  public Customer(int age) {
    //合法性检查
    if(age<0)
      throw new IllegalArgumentException("年龄不能小于零");
    this.age=age;
  }
  public String toString() {
    return "age="+age;
  }
  
  private void readObject(ObjectInputStream stream)
          throws IOException, ClassNotFoundException {
    stream.defaultReadObject();  //先按默认方式反序列化
       //合法性检查
    if(age<0)
      throw new IllegalArgumentException("年龄不能小于零");
  }
  
  public static void main(String[] args) throws Exception{
    Customer customer = new Customer(25);
    System.out.println("Before Serialization:" + customer);
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
   
    //把Customer对象序列化到一个字节缓存中
    ObjectOutputStream o =new ObjectOutputStream(buf);
    o.writeObject(customer);
    
    
    byte[] byteArray=buf.toByteArray(); 
    for(int i=0;i<byteArray.length;i++){
      System.out.print(byteArray[i]+" ");
      if((i % 10==0 && i!=0) || i==byteArray.length-1) System.out.println();
    }
    
    
    //篡改序列化数据
    byteArray[byteArray.length-4]=-1;
    byteArray[byteArray.length-3]=-1;
    byteArray[byteArray.length-2]=-1;
    byteArray[byteArray.length-1]=-10;
   
    //从字节缓存中反序列化Customer对象
    ObjectInputStream in =new ObjectInputStream(
          new ByteArrayInputStream(byteArray));
    customer= (Customer)in.readObject();
    System.out.println("After Serialization:" + customer);
   }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
