
 import java.awt.*; 
 import java.awt.event.*; 
 import javax.swing.*;
 /**
 * <p>Title: 正方体框图</p>
 * <p>Description: 绘制一个边框的正方体，获取鼠标事件根据鼠标的位置旋转方体。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Gr3d1m.java</p>
 * @author 杜江
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
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] args){
     Gr3d1m G3 = new Gr3d1m();
  }
/**
 *<br>方法说明：构造器
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：鼠标按下事件，获取鼠标位置
 *<br>输入参数：
 *<br>返回类型：
 */
  public void mousePressed(MouseEvent e) {
     doX=e.getX();
     doY=e.getY();
  }
/**
 *<br>方法说明：鼠标托拽事件，改变鼠标位置，重画屏幕
 *<br>输入参数：
 *<br>返回类型：
 */  
  public void mouseDragged(MouseEvent e)  {
     angX=angX+e.getY()-doY; 
     angY=angY+e.getX()-doX;
     doX=e.getX(); doY=e.getY();
     repaint(); 
  }
/* 以下是实现MouseListener和MouseMotionListener必须重载的方法*/
  public void mouseReleased(MouseEvent e) { }
  public void mouseClicked(MouseEvent e)  { }
  public void mouseEntered(MouseEvent e)  { }
  public void mouseExited(MouseEvent e)   { }
  public void mouseMoved(MouseEvent e)    { } 
/**
 *<br>方法说明：绘制图形
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：擦拭屏幕，使用背景色覆盖原来的图形
 *<br>输入参数：
 *<br>返回类型：
 */
  public void delDeaw(Graphics g){
  	 Insets insets = getInsets();
     int L0 = insets.left, T0 = insets.top;
  	g.setColor(new Color(128,128,255)); 
    g.fillRect(L0,T0,L0+350,T0+350);
  }
/**
 *<br>方法说明：绘制方体
 *<br>输入参数：
 *<br>返回类型：
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
     g.drawPolygon(xx,yy,len);//绘制多边形
  }
 }
