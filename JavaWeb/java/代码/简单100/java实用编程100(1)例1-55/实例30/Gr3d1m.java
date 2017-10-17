
 /**
 * <p>Title: �������ͼ</p>
 * <p>Description: ����һ���߿�������壬��ȡ����¼���������λ����ת���塣</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Gr3d1m.java</p>
 * @author �Ž�
 * @version 1.0
 */
 public class Gr3d1m extends JFrame 
     implements MouseListener,MouseMotionListener { 
     int doX,doY;
     int angX=30,angY=30;
     Cube data = new Cube();
     Color [] Col={Color.gray,Color.cyan,Color.green, 
                   Color.red,Color.white,Color.orange, 
                   Color.magenta,Color.pink}; 
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] args){
     Gr3d1m G3 = new Gr3d1m();
  }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public  Gr3d1m() {
  	 setTitle("3D cube Frame");
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     addMouseListener(this);
     addMouseMotionListener(this);
     setBackground(new Color(128,128,255));
     setSize(350,350);
     show();
  }
/**
 *<br>����˵������갴���¼�����ȡ���λ��
 *<br>���������
 *<br>�������ͣ�
 */
  public void mousePressed(MouseEvent e) {
     doX=e.getX();
     doY=e.getY();
  }
/**
 *<br>����˵���������ק�¼����ı����λ�ã��ػ���Ļ
 *<br>���������
 *<br>�������ͣ�
 */  
  public void mouseDragged(MouseEvent e)  {
     angX=angX+e.getY()-doY; 
     angY=angY+e.getX()-doX;
     doX=e.getX(); doY=e.getY();
     repaint(); 
  }
/* ������ʵ��MouseListener��MouseMotionListener�������صķ���*/
  public void mouseReleased(MouseEvent e) { }
  public void mouseClicked(MouseEvent e)  { }
  public void mouseEntered(MouseEvent e)  { }
  public void mouseExited(MouseEvent e)   { }
  public void mouseMoved(MouseEvent e)    { } 
/**
 *<br>����˵��������ͼ��
 *<br>���������
 *<br>�������ͣ�
 */
  public void paint( Graphics g ) {
  	 delDeaw(g);
     for (int i=0; i<data.x.length; i++) { 
        drawPG(g, data.x[i], data.y[i], data.z[i], 
               150,150,Col[i]); 
     } 
     g.setColor(Color.yellow); 
     g.drawString("X="+angX%360+" Y="+angY%360, 
                  10,getSize().height-30); 
  }
/**
 *<br>����˵����������Ļ��ʹ�ñ���ɫ����ԭ����ͼ��
 *<br>���������
 *<br>�������ͣ�
 */
  public void delDeaw(Graphics g){
  	 Insets insets = getInsets();
     int L0 = insets.left, T0 = insets.top;
  	g.setColor(new Color(128,128,255)); 
    g.fillRect(L0,T0,L0+350,T0+350);
  }
/**
 *<br>����˵�������Ʒ���
 *<br>���������
 *<br>�������ͣ�
 */
  public void drawPG(Graphics g,double []x,double []y, 
                     double []z,int xp,int yp,Color co) { 
     double x1,y1,z0; 
     int len=x.length; 
     int [] xx=new int [len]; 
     int [] yy=new int [len]; 
     final double RAD=Math.PI/180.0;
     double a=angX*RAD;
     double b=angY*RAD; 
     double sinA=Math.sin(a),sinB=Math.sin(b);
     double cosA=Math.cos(a),cosB=Math.cos(b);
     for (int i=0; i<len; i++) { 
        x1= x[i]*cosB+z[i]*sinB;
        z0=-x[i]*sinB+z[i]*cosB; 
        y1= y[i]*cosA-  z0*sinA;
        xx[i]=xp+(int)Math.rint(x1); 
        yy[i]=yp-(int)Math.rint(y1); 
     } 
     g.setColor(co); 
     g.drawPolygon(xx,yy,len);//���ƶ����
  }
 }
