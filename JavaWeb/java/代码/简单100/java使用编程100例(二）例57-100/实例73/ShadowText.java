/**
 * <p>Title: ����Ӱ������</p>
 * <p>Description: ʹ��Applet��Graphics��ʵ��һ�����ֵ��ƶ���档</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ShadowText.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class ShadowText extends Applet implements Runnable
{
  private Image img;
  private Image offI;
  private Graphics offG;
  private Thread thread = null;
  
  
  private int height,width;
  private String text;
  private int FontSize;
  private Font font;
  private int textcolor, backcolor, shadowcolor;
/**
 *<br>����˵����Applet��ʼ�������������Applet�ǵ��á�
 *<br>���������
 *<br>�������ͣ�
 */  
  public void init()
  {    
    width = this.size().width;
    height = this.size().height;
    //��ȡ��ʾ��Ϣ
    String s = new String(getParameter("Text"));
    
    text = new String("Hello");
    if(s != null)
      text = s;
    //��ȡ�����С
    FontSize = 30;
    s = new String(getParameter("FontSize"));
    if(s != null)
      FontSize = Integer.parseInt(s);
    //���������ɫ
    s = getParameter("Fore");
    textcolor = (s==null) ? 0x000000 : Integer.parseInt(s, 16);
    //��ȡ������ɫ
    s = getParameter("Back");
    backcolor = (s==null) ? 0x000000 : Integer.parseInt(s, 16);
    //��ȡ��Ӱ��ɫ
    s = getParameter("shadow");
    shadowcolor = (s==null) ? 0x000000 : Integer.parseInt(s, 16);
    //���ñ�����ɫ
    this.setBackground(new Color(backcolor));
    //ʹ��Graphics����һ��ͼƬ
    img = createImage(width+300,height);
    Graphics temp = img.getGraphics();
    temp.setColor(new Color(backcolor));
    temp.fillRect(0,0,width,height);
    temp.setColor(new Color(shadowcolor));
    font = new Font("TimesRoman",Font.BOLD,FontSize);
    temp.setFont(font);
    temp.drawString(text,10,height*3/4);
    
    temp.setColor(new Color(textcolor));
    temp.drawString(text,10-3,height*3/4 - 3);
    
    //����ɿ��Ƶ�ͼƬ����          
    offI = createImage(width,height);
    offG = offI.getGraphics();
    
  }
/**
 *<br>����˵��������start()�����������߳�
 *<br>���������
 *<br>�������ͣ�
 */  
  public void start()
  {
    if(thread == null)
    {
      thread = new Thread(this);
      thread.start();
    }
  }
/**
 *<br>����˵�����߳��塣������Ļ
 *<br>���������
 *<br>�������ͣ�
 */  
  public void run()
  {
    int x=width;
    while(thread != null)
    {
      try
      {
        offG.drawImage(img,x,0,this);
        repaint();
        thread.sleep(20);
      }
      catch(InterruptedException e){}

      x-=3;
      if(x < -img.getWidth(this))
      {
        x = width;
      }

    }
  }
/**
 *<br>����˵����Appletg���»�����õķ���
 *<br>���������Graphics g ��ͼ����
 *<br>�������ͣ�
 */  
  public void update(Graphics g)
  {
    paint(g);
  }
/**
 *<br>����˵����Applet������Ļ�ķ���
 *<br>���������
 *<br>�������ͣ�
 */  
  public void paint(Graphics g)
  {
    g.drawImage(offI,0,0,this);
  }


}