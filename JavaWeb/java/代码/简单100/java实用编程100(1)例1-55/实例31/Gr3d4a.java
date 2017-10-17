 public class Gr3d4a extends Gr3d1m {
 
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
  public static void main(String[] args){
     Gr3d4a G3 = new Gr3d4a();
  }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
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
 *<br>����˵����������������ӣ�����Gr3d1m�еķ���
 *<br>���������
 *<br>�������ͣ�
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
        g.fillPolygon(xx,yy,len);//���Ķ����
     } 
  }
/**
 *<br>����˵������Ӱ�������ƽ�汻�ڱν���������
 *<br>���������
 *<br>�������ͣ�
 */
  double Hvec(double []x,double []y) { 
    return(x[0]*(y[1]-y[2])+x[1]*(y[2]-y[0])+x[2]*(y[0]-y[1])); 
  }
 }
