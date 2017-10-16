import java.io.*;
import java.net.*;
public class DatagramTester {
  private int port=8000;
  private DatagramSocket sendSocket;
  private DatagramSocket receiveSocket;
  private static final int MAX_LENGTH=3584;

  public DatagramTester() throws IOException {
    sendSocket=new DatagramSocket(); 
    receiveSocket=new DatagramSocket(port); 
    receiver.start();
    sender.start();
  }

  public static byte[] longToByte(long[] data)throws IOException{
     ByteArrayOutputStream bao=new ByteArrayOutputStream();
     DataOutputStream dos=new DataOutputStream(bao);
     for(int i=0;i<data.length;i++){
       dos.writeLong(data[i]); 
     }
     dos.close();
     return bao.toByteArray();
  }

  public static long[] byteToLong(byte[] data)throws IOException{
    long[] result=new long[data.length/8];  //一个long数据占8个字节
    ByteArrayInputStream bai=new ByteArrayInputStream(data);
    DataInputStream dis=new DataInputStream(bai);
    for(int i=0;i<data.length/8;i++){
      result[i]=dis.readLong();
    }
    return result; 
  }

  public void send(byte[] bigData)throws IOException{
    DatagramPacket packet=new DatagramPacket(bigData,0,512,InetAddress.getByName("localhost"),port);
    int bytesSent=0;  //表示已经发送的字节数
    int count=0;  //表示发送的次数
    while(bytesSent<bigData.length){
      sendSocket.send(packet);
      System.out.println("SendSocket>第"+(++count)+"次发送了"+packet.getLength()+"个字节");
      bytesSent+=packet.getLength(); //getLength()方法返回实际发送的字节数
      int remain=bigData.length-bytesSent; //计算剩余的未发送的字节数
      int length=(remain>512) ? 512: remain;  //计算下次发送的数据的长度
      packet.setData(bigData, bytesSent,length);  //改变DatagramPacket的offset和length属性
    }
  }
  public byte[] receive()throws IOException{
    byte[] bigData=new byte[MAX_LENGTH];
    DatagramPacket packet=new DatagramPacket(bigData,0,MAX_LENGTH);
    int bytesReceived=0;  //表示已经接收的字节数
    int count=0;  //表示接收的次数
    long beginTime=System.currentTimeMillis();
    while((bytesReceived<bigData.length)
      && (System.currentTimeMillis()-beginTime<60000*5)){
      receiveSocket.receive(packet);
      System.out.println("ReceiveSocket>第"+(++count)+"次接收到"+packet.getLength()+"个字节");
      bytesReceived+=packet.getLength(); //getLength()方法返回实际发送的字节数
      packet.setData(bigData, bytesReceived,MAX_LENGTH-bytesReceived);  //改变DatagramPacket的offset和length属性
    }
    return packet.getData();  
  }

 
  public Thread sender=new Thread(){ //发送者线程
    public void run(){
      long[] longArray=new long[MAX_LENGTH/8];
      for(int i=0;i<longArray.length;i++)
        longArray[i]=i+1;
      try{
        send(longToByte(longArray)); //发送一个long型数组中的数据
     }catch(IOException e){e.printStackTrace();}
   }

 };

  public Thread receiver=new Thread(){ //接收者线程
    public void run(){
      try{
        long[] longArray=byteToLong(receive());
        for(int i=0;i<longArray.length;i++){
          if(i%100==0)System.out.println();
          System.out.print(longArray[i]+" ");    
        }
      }catch(IOException e){e.printStackTrace();}
    }
  }; 

  public static void main(String args[])throws IOException {
    DatagramTester tester=new DatagramTester();
  }
}



/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
