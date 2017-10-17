package linger;
public class SimpleClient {
  public static void main(String args[])throws Exception {
    Socket s = new Socket("localhost",8000);
    //s.setSoLinger(true,0);  //Socket�رպ󣬵ײ�Socket�����ر�
    //s.setSoLinger(true,3600);  //Socket�رպ󣬵ײ�Socket�ӳ�3600���ٹر�
    OutputStream out=s.getOutputStream();
    StringBuffer sb=new StringBuffer();
    for(int i=0;i<10000;i++)sb.append(i);
    out.write(sb.toString().getBytes());  //����һ����ַ�
    System.out.println("��ʼ�ر�Socket");
    long begin=System.currentTimeMillis();
    s.close();
    long end=System.currentTimeMillis();
    System.out.println("�ر�Socket���õ�ʱ��Ϊ:"+(end-begin)+"ms");    
  }
}


/****************************************************
 * ���ߣ�������                                     *
 * ��Դ��<<Java�����̾���>>                       *
 * ����֧����ַ��www.javathinker.org                *
 ***************************************************/
