 import java.awt.*;
 import javax.swing.*; 
 
 public class Func extends JFrame{ 
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
  public static void main(String[] args){
    Func db = new Func();
    db.update();
  }
/**
 *<br>方法说明：构造器，显示窗体
 *<br>输入参数：
 *<br>返回类型：
 */ 
  Func(){
    super("Function");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(310,310);
    show();
  }
/**
 *<br>方法说明：更新画面
 *<br>输入参数：
 *<br>返回类型：
 */ 
  public void update(){
     repaint();
  }
/**
 *<br>方法说明：转换坐标点
 *<br>输入参数：
 *<br>返回类型：
 */
  double f(double x) {  
       return (Math.sin(2*x)+Math.cos(3*x)); 
  }
/**
 *<br>方法说明：绘制坐标图
 *<br>输入参数：
 *<br>返回类型：
 */  
  public void paint(Graphics g) { 
       double x0,x1,y0,y1,xd,yd; 
       double max=5.0; 
       int w,h,hw,hh; 
       //获取屏幕的尺寸
       w=getSize().width; 
       h=getSize().height;
       hw=w/2; hh=h/2;
       //在屏幕上输出字符
       g.drawString("Sin[2x]+Cos[3x]",10,40);
       g.setColor(Color.red);  
       g.drawString("0",hw+5,hh+12); 
       g.drawString(""+max,hw-20,40);
       g.drawString(""+max,w-20,hh+12);
       //绘制X轴坐标
       g.drawLine(0,hh,w,hh);
       //绘制Y轴坐标
       g.drawLine(hw,0,hw,h);
       xd=2*max/w;//计算X增量
       yd=hh/max; //计算y增量
       g.setColor(Color.blue);
       //使用增量绘制一小段线，最终组成曲线图形。 
       for (int x=0 ; x<w-1; x++) { 
          x0=-max+x*xd; y0=f(x0)*yd; 
          x1=x0+xd;     y1=f(x1)*yd; 
          g.drawLine(x,(int)(hh-y0),x+1,(int)(hh-y1));
       } 
  }
 } //end
