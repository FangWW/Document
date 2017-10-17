 import java.applet.Applet;
/**
 * <p>Title: �����</p>
 * <p>Description: ��appletʹ������¼���������Ķ�����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: MouseAd.java</p>
 * @author �Ž�
 * @version 1.0
 */
 public class MouseAd extends Applet{ 
   int x=0,y=0;
   int width,height;
/**
 *<br>����˵����Applet��ʼ���������һ������������
 *<br>���������
 *<br>�������ͣ�
 */
   public void init() {
      addMouseListener(new Mouse()); 
   } //end init 
/**
 *<br>��˵�����̳�MouseAdapter�࣬ʵ�������������
 *<br>������������ȡ�����Applet�ϵ����λ��
 */
   class Mouse extends MouseAdapter {
      public void mousePressed(MouseEvent e) {
         x=e.getX();
         y=e.getY(); 
         width = size().width;
         height = size().height;
         repaint(); 
      } //end mousePressed 
   } //end Mouse 
/**
 *<br>����˵��������Applet��Ļ���������λ�û�������
 *<br>���������
 *<br>�������ͣ�
 */
   public void paint(Graphics g) { 
      g.drawString("x="+x+" y="+y,10,20);
      g.drawLine(x,0,x,height);
      g.drawLine(0,y,width,y);
   } //end paint 
 } // end MouseAd 
