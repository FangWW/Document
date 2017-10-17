/**
 * <p>Title: ����߳����ȼ�</p>
 * <p>Description: ͨ���޸��̵߳����ȼ������̻߳�����ȴ���</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: upPRIThread.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class upPRIThread {
 //������
 public static void main(String[] args) throws Exception {
  Thread1 t1 = new Thread1();
  t1.start();
  Thread2 t2 = new Thread2();
  t2.start();
  t1.setPriority(Thread.MIN_PRIORITY);
  t2.setPriority(Thread.MIN_PRIORITY);
  new Thread().sleep(105);
  t2.setPriority(Thread.MAX_PRIORITY);
  new Thread().sleep(10500);
 }
 //��˵�����߳�1�����������ȼ�
 static class Thread1 extends Thread {
  public void run(){
   while(true){
    try {
     Thread.sleep(100);
     } 
    catch (Exception e){
     e.printStackTrace();
     }
     System.out.println("�����߳�111");
    }
   }
  }
 //��˵�����߳�2��������ȼ�
 static class Thread2 extends Thread {

  public void run(){
   while(true){
    try {
     Thread.sleep(100);
     } 
    catch (Exception e){
     e.printStackTrace();
     }
     System.out.println("�����߳�222.........................");
    }
   }
 }
}
