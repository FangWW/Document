public class Sender {
  private String host="localhost";
  private int port=8000;
  private Socket socket;
  private static int stopWay=1;  //����ͨ�ŵķ�ʽ
  private final int NATURAL_STOP=1; //��Ȼ����
  private final int SUDDEN_STOP=2;  //ͻȻ��ֹ����
  private final int SOCKET_STOP=3;  //�ر�Socket���ٽ�������
  private final int OUTPUT_STOP=4;  //�ر���������ٽ�������

  public Sender()throws IOException{
     socket=new Socket(host,port);
  }
  public static void main(String args[])throws Exception{
    if(args.length>0)stopWay=Integer.parseInt(args[0]);
    new Sender().send();
  }
  private PrintWriter getWriter(Socket socket)throws IOException{
    OutputStream socketOut = socket.getOutputStream();
    return new PrintWriter(socketOut,true);
  }

  
  public void send()throws Exception {
    PrintWriter pw=getWriter(socket);
    for(int i=0;i<20;i++){
      String msg="hello_"+i; 
      pw.println(msg);
      System.out.println("send:"+msg);
      Thread.sleep(500);  
      if(i==2){  //��ֹ���򣬽���ͨ��
        if(stopWay==SUDDEN_STOP){
          System.out.println("ͻȻ��ֹ����");
          System.exit(0);
        }else if(stopWay==SOCKET_STOP){
          System.out.println("�ر�Socket����ֹ����");
          socket.close();
          break;
        }else if(stopWay==OUTPUT_STOP){
          socket.shutdownOutput();
  
        System.out.println("�ر����������ֹ����");
          break;
        }
      }  
    }
    
    if(stopWay==NATURAL_STOP){
      socket.close();
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
