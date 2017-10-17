package nonblock;
public class EchoClient{
  private SocketChannel socketChannel = null;
  private ByteBuffer sendBuffer=ByteBuffer.allocate(1024);
  private ByteBuffer receiveBuffer=ByteBuffer.allocate(1024);
  private Charset charset=Charset.forName("GBK");
  private Selector selector;

  public EchoClient()throws IOException{
    socketChannel = SocketChannel.open();
    InetAddress ia = InetAddress.getLocalHost();
    InetSocketAddress isa = new InetSocketAddress(ia,8000);
    socketChannel.connect(isa);
    socketChannel.configureBlocking(false);
    System.out.println("������������ӽ����ɹ�");
    selector=Selector.open();
  }
  public static void main(String args[])throws IOException{
    final EchoClient client=new EchoClient();
    Thread receiver=new Thread(){
      public void run(){
        client.receiveFromUser();
      }
    };

    receiver.start();
    client.talk();
  }

  public void receiveFromUser(){
    try{
      BufferedReader localReader=new BufferedReader(new InputStreamReader(System.in));
      String msg=null;
      while((msg=localReader.readLine())!=null){
        synchronized(sendBuffer){
            sendBuffer.put(encode(msg + "\r\n"));
         }
        if(msg.equals("bye"))
          break;
      }
    }catch(IOException e){
       e.printStackTrace();
    }
  }

  public void talk()throws IOException {
     socketChannel.register(selector,
                          SelectionKey.OP_READ |
                          SelectionKey.OP_WRITE);
     while (selector.select() > 0 ){
       Set readyKeys = selector.selectedKeys();
       Iterator it = readyKeys.iterator();
       while (it.hasNext()){
         SelectionKey key=null;
         try{
             key = (SelectionKey) it.next();
             it.remove();

             if (key.isReadable()) {
                 receive(key);
             }
             if (key.isWritable()) {
                 send(key);
             }
         }catch(IOException e){
            e.printStackTrace();
            try{
                if(key!=null){
                    key.cancel();
                    key.channel().close();
                }
            }catch(Exception ex){e.printStackTrace();}
         }
      }//#while
    }//#while
  }

  public void send(SelectionKey key)throws IOException{
    SocketChannel socketChannel=(SocketChannel)key.channel();
    synchronized(sendBuffer){
        sendBuffer.flip(); //�Ѽ�����Ϊλ��
        socketChannel.write(sendBuffer);
        sendBuffer.compact();
     }
  }
  public void receive(SelectionKey key)throws IOException{
    SocketChannel socketChannel=(SocketChannel)key.channel();
    socketChannel.read(receiveBuffer);
    receiveBuffer.flip();
    String receiveData=decode(receiveBuffer);

    if(receiveData.indexOf("\n")==-1)return;

    String outputData=receiveData.substring(0,receiveData.indexOf("\n")+1);
    System.out.print(outputData);
    if(outputData.equals("echo:bye\r\n")){
        key.cancel();
        socketChannel.close();
        System.out.println("�ر��������������");
        selector.close();
        System.exit(0);
    }

    ByteBuffer temp=encode(outputData);
    receiveBuffer.position(temp.limit());
    receiveBuffer.compact();
  }

  public String decode(ByteBuffer buffer){  //����
    CharBuffer charBuffer= charset.decode(buffer);
    return charBuffer.toString();
  }
  public ByteBuffer encode(String str){  //����
    return charset.encode(str);
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
