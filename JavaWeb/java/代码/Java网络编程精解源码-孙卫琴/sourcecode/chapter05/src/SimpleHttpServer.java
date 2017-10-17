public class SimpleHttpServer {
  private int port=80;
  private ServerSocketChannel serverSocketChannel = null;
  private ExecutorService executorService;
  private static final int POOL_MULTIPLE = 4;

  public SimpleHttpServer() throws IOException {
    executorService= Executors.newFixedThreadPool(
	    Runtime.getRuntime().availableProcessors() * POOL_MULTIPLE);
    serverSocketChannel= ServerSocketChannel.open();
    serverSocketChannel.socket().setReuseAddress(true);
    serverSocketChannel.socket().bind(new InetSocketAddress(port));
    System.out.println("����������");
  }

  public void service() {
    while (true) {
      SocketChannel socketChannel=null;
      try {
        socketChannel = serverSocketChannel.accept();
        executorService.execute(new Handler(socketChannel));
      }catch (IOException e) {
         e.printStackTrace();
      }
    }
  }

  public static void main(String args[])throws IOException {
    new SimpleHttpServer().service();
  }
  class Handler implements Runnable{
  private SocketChannel socketChannel;
  public Handler(SocketChannel socketChannel){
    this.socketChannel=socketChannel;
  }
  public void run(){
    handle(socketChannel);
  }

  public void handle(SocketChannel socketChannel){
    try {
        Socket socket=socketChannel.socket();
        System.out.println("���յ��ͻ����ӣ�����: " +
        socket.getInetAddress() + ":" +socket.getPort());

         ByteBuffer buffer=ByteBuffer.allocate(1024);
         socketChannel.read(buffer);
         buffer.flip();
         String request=decode(buffer);
         System.out.print(request);  //��ӡHTTP����

         //���HTTP��Ӧ���
         StringBuffer sb=new StringBuffer("HTTP/1.1 200 OK\r\n");
         sb.append("Content-Type:text/html\r\n\r\n");
         socketChannel.write(encode(sb.toString()));//�����Ӧͷ

         FileInputStream in;
         //���HTTP����ĵ�һ��
         String firstLineOfRequest=request.substring(0,request.indexOf("\r\n"));
         if(firstLineOfRequest.indexOf("login.htm")!=-1)
            in=new FileInputStream("root/login.htm");
         else
            in=new FileInputStream("root/hello.htm");

         FileChannel fileChannel=in.getChannel();
         fileChannel.transferTo(0,fileChannel.size(),socketChannel);
         fileChannel.close();
      }catch (Exception e) {
         e.printStackTrace();
      }finally {
         try{
           if(socketChannel!=null)socketChannel.close();
         }catch (IOException e) {e.printStackTrace();}
      }
  }
  private Charset charset=Charset.forName("GBK");
  public String decode(ByteBuffer buffer){  //����
    CharBuffer charBuffer= charset.decode(buffer);
    return charBuffer.toString();
  }
  public ByteBuffer encode(String str){  //����
    return charset.encode(str);
  }
 }

}







/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
