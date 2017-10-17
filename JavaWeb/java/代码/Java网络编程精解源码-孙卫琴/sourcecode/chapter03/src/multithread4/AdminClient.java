package multithread4;
public class AdminClient{
  public static void main(String args[]){
    Socket socket=null;
    try{
      socket=new Socket("localhost",8001);
      //���͹ر�����
      OutputStream socketOut=socket.getOutputStream();
      socketOut.write("shutdown\r\n".getBytes());
 
      //���շ������ķ���
      BufferedReader br = new BufferedReader(
                                  new InputStreamReader(socket.getInputStream()));
      String msg=null;
      while((msg=br.readLine())!=null)
        System.out.println(msg);
    }catch(IOException e){
      e.printStackTrace();
    }finally{
      try{
        if(socket!=null)socket.close();
      }catch(IOException e){e.printStackTrace();}
    }
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
