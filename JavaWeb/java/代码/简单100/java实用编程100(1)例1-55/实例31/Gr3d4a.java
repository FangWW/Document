 import java.awt.*; 
 import javax.swing.*;
 public class Gr3d4a extends Gr3d1m {
 
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] args){
     Gr3d4a G3 = new Gr3d4a();
  }
/**
 *<br>方法说明：构造器
 *<br>输入参数：
 *<br>返回类型：
 */
  public  Gr3d4a() {
  	 setTitle("3D cube box");
     setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     addMouseListener(this);
     addMouseMotionListener(this);
     setBackground(new Color(128,128,255));
     setSize(350,350);
     show();
  }
/**
 *<br>方法说明：绘制正方体盒子，过载Gr3d1m中的方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public void drawPG(Graphics g,double []x,double []y, 
                     double []z,int xp,int yp,Color co) { 
     double x1,y1,z0; 
     int len=x.length;
     double [] xw=new double[len];
     double [] yw=new double[len];
     int    [] xx=new int   [len]; 
     int    [] yy=new int   [len]; 
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
        xw[i]=x1; yw[i]=y1; 
     } 
     if (Hvec(xw,yw) > 0) {
        g.setColor(co);
        g.fillPolygon(xx,yy,len);//填充的多边形
     } 
  }
/**
 *<br>方法说明：消影处理，如果平面被遮蔽将不被绘制
 *<br>输入参数：
 *<br>返回类型：
 */
  double Hvec(double []x,double []y) { 
    return(x[0]*(y[1]-y[2])+x[1]*(y[2]-y[0])+x[2]*(y[0]-y[1])); 
  }
 }
