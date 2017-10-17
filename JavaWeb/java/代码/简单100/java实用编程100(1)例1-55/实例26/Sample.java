/**
 * <p>Title: ����ͼ�εĻ���</p>
 * <p>Description: ��ʵ����ʾ�����ı��͡�Բ�Ǿ��Ρ���Բ�Ȼ�����ͼ�Ρ� </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Sample.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class Sample extends JFrame { 
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */  
  public static void main(String[] args){
    Sample sl = new Sample();
    sl.update();
  }
/**
 *<br>����˵��������������ʾ����
 *<br>���������
 *<br>�������ͣ�
 */  
  Sample(){
    super("Sample");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(310,160);
    show();
  }
/**
 *<br>����˵�������»���
 *<br>���������
 *<br>�������ͣ�
 */  
  public void update(){
    repaint();
  }
/**
 *<br>����˵�������ƻ���
 *<br>���������
 *<br>�������ͣ�
 */  
  public void paint(Graphics g) { 
     int [] x={10,30,80,50,10};
     int [] y={90,140,120,100,90};
     g.setColor(Color.cyan);      
     //����������
     for (int i=0; i<=300; i+=10) { 
        g.drawLine(i,0,i,150);    
     }
     //���ƺ���� 
     for (int i=0; i<=150; i+=10) { 
        g.drawLine(0,i,300,i);    
     }
     g.setColor(Color.black);     
     //�нǾ��Σ���ʼ��(10,30)����80����50
     g.drawRect(10,30,80,50);     
     //Բ�Ǿ��Σ���ʼ��(110,30)����80����50���ǣ�a=20,b=10��
     g.drawRoundRect(110,30,80,50,20,10);
     //��Ǳ�
     g.drawPolygon(x,y,5);
     //��Բ��Բ�ģ�110,90����a=80,b=50       
     g.drawOval(110,90,80,50); 
     //һ������Բ�ģ�219,30����a=80,b=50 �Ƕ���0-90֮��
     g.drawArc(210,30,80,50,0,90);
     //���棬Բ�ģ�219,90����a=80,b=50 �Ƕ���0-90֮��
     g.fillArc(210,90,80,50,0,90);
  }
} 
