// �ļ�����moreServer.java
/**
 * <p>Title: ���̷߳�����</p>
 * <p>Description: ��ʵ��ʹ�ö��߳�ʵ�ֶ�����ܡ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author �Ž�
 * @version 1.0
 */
class moreServer
{
 public static void main (String [] args) throws IOException
 {
   System.out.println ("Server starting...\n"); 
   //ʹ��8000�˿��ṩ����
   ServerSocket server = new ServerSocket (8000);
   while (true)
   {
    //������ֱ���пͻ�����
     Socket sk = server.accept ();
     System.out.println ("Accepting Connection...\n");
     //���������߳�
     new ServerThread (sk).start ();
   }
 }
}
//ʹ���̣߳�Ϊ����ͻ��˷���
class ServerThread extends Thread
{
 private Socket sk;
 
 ServerThread (Socket sk)
 {
  this.sk = sk;
 }
//�߳�����ʵ��
 public void run ()
 {
  BufferedReader in = null;
  PrintWriter out = null;
  try{
    InputStreamReader isr;
    isr = new InputStreamReader (sk.getInputStream ());
    in = new BufferedReader (isr);
    out = new PrintWriter (
           new BufferedWriter(
            new OutputStreamWriter(
              sk.getOutputStream ())), true);

    while(true){
      //�������Կͻ��˵����󣬸��ݲ�ͬ������ز�ͬ����Ϣ��
      String cmd = in.readLine ();
      System.out.println(cmd);
      if (cmd == null)
          break;
      cmd = cmd.toUpperCase ();
      if (cmd.startsWith ("BYE")){
      	 out.println ("BYE");
         break;
      }else{
        out.println ("��ã����Ƿ�������");
      }
    }
    }catch (IOException e)
    {
       System.out.println (e.toString ());
    }
    finally
    {
      System.out.println ("Closing Connection...\n");
      //����ͷ���Դ
      try{
       if (in != null)
         in.close ();
       if (out != null)
         out.close ();
        if (sk != null)
          sk.close ();
      }
      catch (IOException e)
      {
      	System.out.println("close err"+e);
      }
    }
 }
}