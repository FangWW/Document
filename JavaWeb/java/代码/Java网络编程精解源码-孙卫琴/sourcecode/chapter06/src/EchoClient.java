import echo.*;
public class EchoClient{
  public static void main(String args[])throws IOException{
    //����URLStreamHandlerFactory
    URL.setURLStreamHandlerFactory(new EchoURLStreamHandlerFactory());
    //����ContentHandlerFactory
    URLConnection.setContentHandlerFactory(new EchoContentHandlerFactory());
    URL url=new URL("echo://localhost:8000");
    EchoURLConnection connection=(EchoURLConnection)url.openConnection();
    connection.setDoOutput(true);
    PrintWriter pw=new PrintWriter(connection.getOutputStream(),true);
    while(true){
       BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
       String msg=br.readLine();
       pw.println(msg);  //�������������Ϣ
       String echoMsg=(String)connection.getContent(); //��ȡ���������ص���Ϣ
       System.out.println(echoMsg);
       if(echoMsg.equals("echo:bye")){
         connection.disconnect(); //�Ͽ�����
         break;
       }
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
