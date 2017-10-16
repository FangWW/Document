import java.io.*;
public class Customer3 implements Serializable {
  private static int count; //用于计算Customer3对象的数目
  private static final int MAX_COUNT=1000;
  private String name;
  private transient String password;
  
  static{
     System.out.println("调用Customer3类的静态代码块");
  }
  public Customer3(){
    System.out.println("调用Customer3类的不带参数的构造方法");
    count++;
  }
  public Customer3(String name, String password) {
    System.out.println("调用Customer3类的带参数的构造方法");
    this.name=name;
    this.password=password;
    count++;
  }

  /** 加密数组，将buff数组中的每个字节的每一位取反 
   *  例如13的二进制为00001101，取反后为11110010
   */
  private byte[] change(byte[] buff){
    for(int i=0;i<buff.length;i++){
      int b=0;
      for(int j=0;j<8;j++){
        int bit=(buff[i]>>j & 1)==0 ? 1:0;
        b+=(1<<j)*bit;
      }
      buff[i]=(byte)b;
    }
    return buff;
  }

  private void writeObject(ObjectOutputStream stream)throws IOException {
    stream.defaultWriteObject();  //先按默认方式序列化 
    stream.writeObject(change(password.getBytes()));
    stream.writeInt(count);
  }

  private void readObject(ObjectInputStream stream)
          throws IOException, ClassNotFoundException {
    stream.defaultReadObject();  //先按默认方式反序列化
    byte[] buff=(byte[])stream.readObject();
    password = new String(change(buff));
    count=stream.readInt();
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
