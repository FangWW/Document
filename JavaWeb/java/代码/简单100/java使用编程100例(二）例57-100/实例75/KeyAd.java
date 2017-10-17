 import java.applet.Applet;
/**
 * <p>Title: ���̼��</p>
 * <p>Description: ��appletʹ�ü����¼��������̵Ķ�����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: KeyAd.java</p>
 * @author �Ž�
 * @version 1.0
 */
 public class KeyAd extends Applet{ 
   String text = "";
   int width,height;
/**
 *<br>����˵����Applet��ʼ���������һ�����̼�������
 *<br>���������
 *<br>�������ͣ�
 */
   public void init() {
      addKeyListener(new KeyAdapter() {
      public void keyPressed(KeyEvent e) {
        this_keyPressed(e);
      }
    }); 
   } //end init 
/**
 *<br>����˵������ȡ�����¼�
 *<br>���������
 *<br>�������ͣ�
 */
   void this_keyPressed(KeyEvent e) {
    String s = "";
    //ǿ��ת�����ַ���
    text = s+=e.getKeyChar();
    System.out.println("text="+text);
    width = size().width;
    height = size().height;
    repaint();
   }
/**
 *<br>����˵��������Applet��Ļ
 *<br>���������
 *<br>�������ͣ�
 */
   public void paint(Graphics g) {
   	  setFont(new Font("Dialog", 1, 80)); 
      g.drawString(text,height/2,width/2);
   } //end paint 
 } // end KeyAd 
