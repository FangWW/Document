/**
 * <p>Title: SMTPЭ������ʼ�</p>
 * <p>Description: ͨ��Socket����POP3��������ʹ��SMTPЭ������ʼ��������е��ʼ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author �Ž�
 * @version 1.0
 */
class POP3Demo
{
/**
 *<br>����˵�����������������û�����
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] args){
  	if(args.length!=3){
  	 System.out.println("USE: java POP3Demo mailhost user password");
  	}
  	new POP3Demo().receive(args[0],args[1],args[2]);
  }
/**
 *<br>����˵���������ʼ�
 *<br>���������String popServer ��������ַ
 *<br>���������String popUser �����û���
 *<br>���������String popPassword ��������
 *<br>�������ͣ�
 */
  public static void receive (String popServer, String popUser, String popPassword)
  {
   String POP3Server = popServer;
   int POP3Port = 110;
   Socket client = null;
   try
   {
     // ����һ�����ӵ�POP3���������׽��֡�
     client = new Socket (POP3Server, POP3Port);
     //����һ��BufferedReader�����Ա���׽��ֶ�ȡ�����
     InputStream is = client.getInputStream ();
     BufferedReader sockin;
     sockin = new BufferedReader (new InputStreamReader (is));
     //����һ��PrintWriter�����Ա����׽���д�����ݡ�
     OutputStream os = client.getOutputStream ();
     PrintWriter sockout;
     sockout = new PrintWriter (os, true); // true for auto-flush
     // ��ʾPOP3������Ϣ��
     System.out.println ("S:" + sockin.readLine ());
     
     /*--   ��POP3���������ֹ���   --*/     
      System.out.print ("C:");
      String cmd = "user "+popUser;
      // ���û������͵�POP3�������
      System.out.println (cmd);
      sockout.println (cmd);
      // ��ȡPOP3�������Ļ�Ӧ��Ϣ��
      String reply = sockin.readLine ();
      System.out.println ("S:" + reply);

      System.out.print ("C:");
      cmd = "pass ";
      // �����뷢�͵�POP3�������
      System.out.println(cmd+"*********");
      sockout.println (cmd+popPassword);
      // ��ȡPOP3�������Ļ�Ӧ��Ϣ��
      reply = sockin.readLine ();
      System.out.println ("S:" + reply);
      
           
      System.out.print ("C:");
      cmd = "stat";
      // ��ȡ�ʼ����ݡ�
      System.out.println(cmd);
      sockout.println (cmd);
      // ��ȡPOP3�������Ļ�Ӧ��Ϣ��
      reply = sockin.readLine ();
      System.out.println ("S:" + reply);
      if(reply==null) return;
      System.out.print ("C:");
      cmd = "retr 1";
      // �����յ�һ���ʼ�����͵�POP3�������
      System.out.println(cmd);
      sockout.println (cmd);
      
      // ������RETR����ҷ����˳ɹ��Ļ�Ӧ�룬�������׽��ֶ�ȡ����� 
      // ֱ������<CRLF>.<CRLF>����ʱ���׽��ֶ�������������ʼ������ݡ�
      if (cmd.toLowerCase ().startsWith ("retr") &&
        reply.charAt (0) == '+')
        do
        {
          reply = sockin.readLine ();
          System.out.println ("S:" + reply);
          if (reply != null && reply.length () > 0)
            if (reply.charAt (0) == '.')
              break;
        }
        while (true);
      cmd = "quit";
      // ������͵�POP3�������
      System.out.print (cmd);
      sockout.println (cmd);     
   }
   catch (IOException e)
   {
     System.out.println (e.toString ());
   }
   finally
   {
     try
     {  if (client != null)
          client.close ();
     }
     catch (IOException e)
     {
     }
   }
  }
}