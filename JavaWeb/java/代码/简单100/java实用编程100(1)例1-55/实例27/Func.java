 public class Func extends JFrame{
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] args){
    Func db = new Func();
    db.update();
  }
/**
 *<br>����˵��������������ʾ����
 *<br>���������
 *<br>�������ͣ�
 */ 
  Func(){
    super("Function");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(310,310);
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
 *<br>����˵����ת�������
 *<br>���������
 *<br>�������ͣ�
 */
  double f(double x) {  
       return (Math.sin(2*x)+Math.cos(3*x)); 
  }
/**
 *<br>����˵������������ͼ
 *<br>���������
 *<br>�������ͣ�
 */  
  public void paint(Graphics g) { 
       double x0,x1,y0,y1,xd,yd; 
       double max=5.0; 
       int w,h,hw,hh; 
       //��ȡ��Ļ�ĳߴ�
       w=getSize().width; 
       h=getSize().height;
       hw=w/2; hh=h/2;
       //����Ļ������ַ�
       g.drawString("Sin[2x]+Cos[3x]",10,40);
       g.setColor(Color.red);  
       g.drawString("0",hw+5,hh+12); 
       g.drawString(""+max,hw-20,40);
       g.drawString(""+max,w-20,hh+12);
       //����X������
       g.drawLine(0,hh,w,hh);
       //����Y������
       g.drawLine(hw,0,hw,h);
       xd=2*max/w;//����X����
       yd=hh/max; //����y����
       g.setColor(Color.blue);
       //ʹ����������һС���ߣ������������ͼ�Ρ� 
       for (int x=0 ; x<w-1; x++) { 
          x0=-max+x*xd; y0=f(x0)*yd; 
          x1=x0+xd;     y1=f(x1)*yd; 
          g.drawLine(x,(int)(hh-y0),x+1,(int)(hh-y1));
       } 
  }
 } //end
