import java.awt.*; 
import javax.swing.*;
/**
 * <p>Title: 基本图形的绘制</p>
 * <p>Description: 本实例演示绘制四边型、圆角矩形、椭圆等基本的图形。 </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Sample.java</p>
 * @author 杜江
 * @version 1.0
 */
public class Sample extends JFrame { 
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */  
  public static void main(String[] args){
    Sample sl = new Sample();
    sl.update();
  }
/**
 *<br>方法说明：构造器，显示窗体
 *<br>输入参数：
 *<br>返回类型：
 */  
  Sample(){
    super("Sample");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(310,160);
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
 *<br>方法说明：绘制画面
 *<br>输入参数：
 *<br>返回类型：
 */  
  public void paint(Graphics g) { 
     int [] x={10,30,80,50,10};
     int [] y={90,140,120,100,90};
     g.setColor(Color.cyan);      
     //绘制竖格线
     for (int i=0; i<=300; i+=10) { 
        g.drawLine(i,0,i,150);    
     }
     //绘制横格线 
     for (int i=0; i<=150; i+=10) { 
        g.drawLine(0,i,300,i);    
     }
     g.setColor(Color.black);     
     //有角矩形，起始点(10,30)，宽80，高50
     g.drawRect(10,30,80,50);     
     //圆角矩形，起始点(110,30)，宽80，高50，角（a=20,b=10）
     g.drawRoundRect(110,30,80,50,20,10);
     //多角边
     g.drawPolygon(x,y,5);
     //椭圆，圆心（110,90）、a=80,b=50       
     g.drawOval(110,90,80,50); 
     //一条弧，圆心（219,30）、a=80,b=50 角度在0-90之间
     g.drawArc(210,30,80,50,0,90);
     //扇面，圆心（219,90）、a=80,b=50 角度在0-90之间
     g.fillArc(210,90,80,50,0,90);
  }
} 
