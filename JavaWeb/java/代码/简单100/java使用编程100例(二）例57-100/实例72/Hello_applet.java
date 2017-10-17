import java.applet.Applet;
/**
 * <p>Title: �򵥵�Applet</p>
 * <p>Description: ����Applet�࣬ʵ��Applet����������Ļ˵���һ���ʺ��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Hello_applet.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class Hello_applet extends Applet{
 String s;
/**
 *<br>����˵������ʼ��
 *<br>���������
 *<br>�������ͣ�
 */
 public void init()
 {
  s = "Hello! welcome into Applet world!";
 }
/**
 *<br>����˵�����������
 *<br>���������
 *<br>�������ͣ�
 */
 public void paint(Graphics g)
 {
  setFont(new Font(s,Font.BOLD,16)); 
  g.drawString(s,(getSize().width/2)/2,getSize().height/2);
 }
}
