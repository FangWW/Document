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
    long[] result=new long[data.length/8];  //һ��long����ռ8���ֽ�
    ByteArrayInputStream bai=new ByteArrayInputStream(data);
    DataInputStream dis=new DataInputStream(bai);
    for(int i=0;i<data.length/8;i++){
      result[i]=dis.readLong();
    }
    return result; 
  }

  public void send(byte[] bigData)throws IOException{
    DatagramPacket packet=new DatagramPacket(bigData,0,512,InetAddress.getByName("localhost"),port);
    int bytesSent=0;  //��ʾ�Ѿ����͵��ֽ���
    int count=0;  //��ʾ���͵Ĵ���
    while(bytesSent<bigData.length){
      sendSocket.send(packet);
      System.out.println("SendSocket>��"+(++count)+"�η�����"+packet.getLength()+"���ֽ�");
      bytesSent+=packet.getLength(); //getLength()��������ʵ�ʷ��͵��ֽ���
      int remain=bigData.length-bytesSent; //����ʣ���δ���͵��ֽ���
      int length=(remain>512) ? 512: remain;  //�����´η��͵����ݵĳ���
      packet.setData(bigData, bytesSent,length);  //�ı�DatagramPacket��offset��length����
    }
  }
  public byte[] receive()throws IOException{
    byte[] bigData=new byte[MAX_LENGTH];
    DatagramPacket packet=new DatagramPacket(bigData,0,MAX_LENGTH);
    int bytesReceived=0;  //��ʾ�Ѿ����յ��ֽ���
    int count=0;  //��ʾ���յĴ���
    long beginTime=System.currentTimeMillis();
    while((bytesReceived<bigData.length)
      && (System.currentTimeMillis()-beginTime<60000*5)){
      receiveSocket.receive(packet);
      System.out.println("ReceiveSocket>��"+(++count)+"�ν��յ�"+packet.getLength()+"���ֽ�");
      bytesReceived+=packet.getLength(); //getLength()��������ʵ�ʷ��͵��ֽ���
      packet.setData(bigData, bytesReceived,MAX_LENGTH-bytesReceived);  //�ı�DatagramPacket��offset��length����
    }
    return packet.getData();  
  }

 
  public Thread sender=new Thread(){ //�������߳�
    public void run(){
      long[] longArray=new long[MAX_LENGTH/8];
      for(int i=0;i<longArray.length;i++)
        longArray[i]=i+1;
      try{
        send(longToByte(longArray)); //����һ��long�������е�����
     }catch(IOException e){e.printStackTrace();}
   }

 };

  public Thread receiver=new Thread(){ //�������߳�
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
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
