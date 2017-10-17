public class MailSender{
  private String smtpServer="smtp.mydomain.com";  //SMTP�ʼ���������������
  //private String smtpServer="localhost";
  private int port=25;

  public static void main(String[] args){
    Message msg=new Message("tom@abc.com",   //�����ߵ��ʼ���ַ
                            "linda@def.com",  //�����ߵ��ʼ���ַ
                           "hello",  //�ʼ�����
                           "hi,I miss you very much."); //�ʼ�����
    new MailSender().sendMail(msg);
  }

  public void sendMail(Message msg){
    Socket socket=null;
    try{
      socket = new Socket(smtpServer,port);  //���ӵ��ʼ�������
      BufferedReader br =getReader(socket);
      PrintWriter pw = getWriter(socket);
      String localhost= InetAddress.getLocalHost().getHostName();   //�ͻ�����������

      sendAndReceive(null,br,pw); //������Ϊ�˽��շ���������Ӧ����
      sendAndReceive("HELO " + localhost,br,pw);
      sendAndReceive("MAIL FROM: <" + msg.from+">",br,pw);
      sendAndReceive("RCPT TO: <" + msg.to+">",br,pw);
      sendAndReceive("DATA",br,pw);  //��������ʼ�����ʼ�����
      pw.println(msg.data);  //�����ʼ�����
      System.out.println("Client>"+msg.data);
      sendAndReceive(".",br,pw);  //�ʼ��������
      sendAndReceive("QUIT",br,pw);  //����ͨ��
    }catch (IOException e){
      e.printStackTrace();
    }finally{
      try{
        if(socket!=null)socket.close();
      }catch (IOException e) {e.printStackTrace();}
    }
  }

  /** ����һ���ַ�����������һ�з���������Ӧ����*/
  private void sendAndReceive(String str,BufferedReader br,PrintWriter pw) throws IOException{
    if (str != null){
      System.out.println("Client>"+str);
      pw.println(str);  //������str�ַ����󣬻��ᷢ�͡�\r\n����
    }
    String response;
    if ((response = br.readLine()) != null)
      System.out.println("Server>"+response);
  }

   private PrintWriter getWriter(Socket socket)throws IOException{
     OutputStream socketOut = socket.getOutputStream();
     return new PrintWriter(socketOut,true);
   }
   private BufferedReader getReader(Socket socket)throws IOException{
     InputStream socketIn = socket.getInputStream();
     return new BufferedReader(new InputStreamReader(socketIn));
  }
}

class Message{  //��ʾ�ʼ�
  String from;  //�����ߵ��ʼ���ַ
  String to;  //�����ߵ��ʼ���ַ
  String subject;  //�ʼ�����
  String content;  //�ʼ�����
  String data;  //�ʼ����ݣ������ʼ����������
  public Message(String from,String to, String subject, String content){
    this.from=from;
    this.to=to;
    this.subject=subject;
    this.content=content;
    data="Subject:"+subject+"\r\n"+content;
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
