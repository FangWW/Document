 import javax.swing.Timer;
/**
 * <p>Title: ʱ��</p>
 * <p>Description: ��ʵ����ʾʹ��ͼ�λ���һ��ͼ��ʱ��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: Clock.java</p>
 * @author �Ž�
 * @version 1.0
 */
 public class Clock extends JFrame implements ActionListener{ 
    Timer timer;
    int x,y,old_X,old_Y, r,x0,y0,w,h,ang; 
    int sdo,mdo,hdo,old_M,old_H; 
    TimeZone tz =TimeZone.getTimeZone("JST");
    final double RAD=Math.PI/180.0; 

  public static void main(String[] args){
    Clock cl = new Clock();
  }
/**
 *<br>����˵����ʵ��ActionListener�������صķ���
 *<br>���������
 *<br>�������ͣ�
 */ 
  public void actionPerformed(ActionEvent e) {
            timer.restart();
    }
/**
 *<br>����˵��������������ʾ���壬�������һ�����
 *<br>���������
 *<br>�������ͣ�
 */ 
  Clock(){
    super("Clock");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBackground(new Color(0,0,192));
    setSize(300,300);
    show();
    int delay = 1000;
    //��������¼��������������Ĵ���
    ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
         repaint();
      }
    };
    new Timer(delay, taskPerformer).start();
  }
/**
 *<br>����˵��������ͼ��
 *<br>���������
 *<br>�������ͣ�
 */
  public void paint( Graphics g ) { 
  	 Insets insets = getInsets();
     int L0 = (insets.left)/2, T0 = (insets.top)/2;
     int hh,mm,ss; 
     String st; 
     h=getSize().height;
     //����Բ��
     g.setColor(Color.white); 
     g.drawOval(L0+30,T0+30,h-60,h-60);
     g.drawOval(L0+32,T0+32,h-64,h-64);
     r=h/2-30;
     x0=30+r-5+L0;
     y0=30+r-5-T0;
     ang=60;
     for (int i=1; i<=12; i++) { 
        x=(int)((r+10)*Math.cos(RAD*ang)+x0);
        y=(int)((r+10)*Math.sin(RAD*ang)+y0);
        g.drawString(""+i,x,h-y);
        ang-=30;
     } 
     x0=30+r+L0; y0=30+r+T0;
     //��ȡʱ��
     Calendar now=Calendar.getInstance();
     hh=now.get(Calendar.HOUR_OF_DAY);//Сʱ
     mm=now.get(Calendar.MINUTE);//����
     ss=now.get(Calendar.SECOND);// ��
     g.setColor(Color.pink); 
     g.fillRect(L0,T0,60,28);//���ľ���
     g.setColor(Color.blue); 
     if (hh < 10) st="0"+hh;     else st=""+hh; 
     if (mm < 10) st=st+":0"+mm; else st=st+":"+mm; 
     if (ss < 10) st=st+":0"+ss; else st=st+":"+ss; 
     g.drawString(st,L0,T0+25);
     //����ʱ���ͼ�εĹ�ϵ
     sdo=90-ss*6;
     mdo=90-mm*6;
     hdo=90-hh*30-mm/2;
     //��������
     if (old_X > 0) {
        g.setColor(getBackground());
        g.drawLine(x0,y0,old_X,(h-old_Y));
     } else { 
        old_M=mdo;
        old_H=hdo;
     } 
     //��������
     g.setColor(Color.yellow); 
     x=(int)((r-8)*Math.cos(RAD*sdo)+x0); 
     y=(int)((r-8)*Math.sin(RAD*sdo)+y0)-2*T0; 
     g.drawLine(x0,y0,x,(h-y));
     
     old_X=x; 
     old_Y=y; 
     //���������ʱ��
     if (mdo != old_M) {
       line(g,old_M,(int)(r*0.7),getBackground());
       old_M=mdo;
     } 
     if (hdo != old_H) {
       line(g,old_H,(int)(r*0.5),getBackground());
       old_H=hdo;
     } 
     //���Ʒ���
     line(g,mdo,(int)(r*0.7),Color.green);
     //����ʱ��
     line(g,hdo,(int)(r*0.5),Color.red);
  } // end paint 
/**
 *<br>����˵���������ߣ����ڻ���ʱ��ͷ���
 *<br>���������
 *<br>�������ͣ�
 */
   public void line(Graphics g, int t, int n, Color c) { 
      int [] xp = new int[4];
      int [] yp = new int[4]; 
      xp[0]=x0;
      yp[0]=y0;
      xp[1]=  (int)((n-10)*Math.cos(RAD*(t-4))+x0); 
      yp[1]=h-(int)((n-10)*Math.sin(RAD*(t-4))+y0); 
      xp[2]=  (int)( n    *Math.cos(RAD* t   )+x0); 
      yp[2]=h-(int)( n    *Math.sin(RAD* t   )+y0); 
      xp[3]=  (int)((n-10)*Math.cos(RAD*(t+4))+x0); 
      yp[3]=h-(int)((n-10)*Math.sin(RAD*(t+4))+y0); 
      g.setColor(c); 
      g.fillPolygon(xp,yp,4);
   }
 }
