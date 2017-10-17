package chat;

/**
 * CaptureScreen.java
 */
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;

public class CaptureScreen extends JFrame implements ActionListener {
    private JButton start,cancel;
    private JPanel c;
    private BufferedImage get;
    private JTabbedPane jtp;//һ�����úܶ��ͼƬ
    private int index;//һ��һֱ�����������,���ڱ���ͼƬ
    private JRadioButton java,system;//JAVA����,ϵͳ����
    /** Creates a new instance of CaptureScreen */
    public CaptureScreen() {
        super("��Ļ��ȡ");
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception exe){
            exe.printStackTrace();
        }
        initWindow();
        initOther();
    }
    private void initOther(){
        jtp=new JTabbedPane(JTabbedPane.TOP,JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    private void initWindow(){
        start=new JButton("��ʼ��ȡ");
        cancel=new JButton("�˳�");
        start.addActionListener(this);
        cancel.addActionListener(this);
        JPanel buttonJP=new JPanel();
        c=new JPanel(new BorderLayout());
        JLabel jl=new JLabel("��Ļ��ȡ", JLabel.CENTER);
        JLabel jl1=new JLabel("��ʾ��˫��ѡ��������б���Ȳ���",JLabel.CENTER);
        jl.setFont(new Font("����",Font.BOLD,40));
        jl1.setFont(new Font("����",Font.BOLD,20));
        jl.setForeground(Color.RED);
        jl1.setForeground(Color.BLUE);
        c.add(jl,BorderLayout.CENTER);
        c.add(jl1,BorderLayout.SOUTH);
        buttonJP.add(start);
        buttonJP.add(cancel);
        buttonJP.setBorder(BorderFactory.createTitledBorder("����������"));
        JPanel jp=new JPanel();//����������ѡ��ť�����
        jp.add(java=new JRadioButton("java����"));
        jp.add(system=new JRadioButton("ϵͳ����",true));
        java.addActionListener(this);
        system.addActionListener(this);
        jp.setBorder(BorderFactory.createTitledBorder("������"));
        ButtonGroup bg=new ButtonGroup();
        bg.add(java);
        bg.add(system);
        JPanel all=new JPanel();
        all.add(jp);
        all.add(buttonJP);
        this.getContentPane().add(c,BorderLayout.CENTER);
        this.getContentPane().add(all,BorderLayout.SOUTH);
        this.setSize(500,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    private void updates(){
        this.setVisible(true);
        if(get!=null){
            //���������0,���ʾһ��ͼƬ��û�б������,
            //��Ҫ�����ǰ�Ķ���,���°�tabpane�Ž���
            if(index==0){
                c.removeAll();
                c.add(jtp,BorderLayout.CENTER);
            }else{//����Ļ�,ֱ�Ӷ�tabpane������Ϳ�����
                //��ʲô����������
            }
            PicPanel pic=new PicPanel(get);
            jtp.addTab("ͼƬ"+(++index),pic);
            jtp.setSelectedComponent(pic);
            SwingUtilities.updateComponentTreeUI(c); // ����LookAndFeel��javax.swing��
        }
    }
    
    private void doStart(){
        try{
            this.setVisible(false);
            Thread.sleep(500);//˯500������Ϊ����������ȫ����
            Robot ro=new Robot(); // ��ͨ�����ز�����������ꡢ���̵�ʵ������Դ��java.awt��
            Toolkit tk=Toolkit.getDefaultToolkit(); // AWT����ĳ����ࣨjava.awt��
            Dimension di=tk.getScreenSize();
            Rectangle rec=new Rectangle(0,0,di.width,di.height);
            BufferedImage bi=ro.createScreenCapture(rec);
            JFrame jf=new JFrame();
            Temp temp=new Temp(jf,bi,di.width,di.height); // �Զ����Temp��Ķ���
            jf.getContentPane().add(temp,BorderLayout.CENTER);
            jf.setUndecorated(true);
            jf.setSize(di);
            jf.setVisible(true);
            jf.setAlwaysOnTop(true);
        } catch(Exception exe){
            exe.printStackTrace();
        }
    }
    
    /**
    *���õĴ�����ͼƬ�ķ���
    */
    public  void doSave(BufferedImage get){
        try{
            if(get==null){
                JOptionPane.showMessageDialog(this
                  , "ͼƬ����Ϊ��!!", "����", JOptionPane.ERROR_MESSAGE);
                return;
            }
            JFileChooser jfc=new JFileChooser(".");
            jfc.addChoosableFileFilter(new GIFfilter());
            jfc.addChoosableFileFilter(new BMPfilter());
            jfc.addChoosableFileFilter(new JPGfilter());
            jfc.addChoosableFileFilter(new PNGfilter());
            int i=jfc.showSaveDialog(this);
            if(i==JFileChooser.APPROVE_OPTION){
                File file=jfc.getSelectedFile();
                String about="PNG";
                String ext=file.toString().toLowerCase();
                javax.swing.filechooser.FileFilter ff=jfc.getFileFilter();
                if(ff instanceof JPGfilter){
                    if(!ext.endsWith(".jpg")){
                        String ns=ext+".jpg";
                        file=new File(ns);
                        about="JPG";
                    }
                } else if(ff instanceof PNGfilter){
                    if(!ext.endsWith(".png")){
                        String ns=ext+".png";
                        file=new File(ns);
                        about="PNG";
                    }
                }else if(ff instanceof BMPfilter){
                    if(!ext.endsWith(".bmp")){
                        String ns=ext+".bmp";
                        file=new File(ns);
                        about="BMP";
                    }
                }else if(ff instanceof GIFfilter){
                    if(!ext.endsWith(".gif")){
                        String ns=ext+".gif";
                        file=new File(ns);
                        about="GIF";
                    }
                }
                if(ImageIO.write(get,about,file)){
                    JOptionPane.showMessageDialog(this,"����ɹ���");
                } else
                    JOptionPane.showMessageDialog(this,"����ʧ�ܣ�");
            }
        } catch(Exception exe){
            exe.printStackTrace();
        }
    }
    
    /** 
     *�����Ĵ���ѵ�ǰ��ͼƬ���������ķ���
     */
    public void doCopy(final BufferedImage image){
        try{
            if(get==null){
                JOptionPane.showMessageDialog(this
                  ,"ͼƬ����Ϊ��!!","����",JOptionPane.ERROR_MESSAGE);
                return;
            } // java.awt.datatransfer���ӿڣ�
            Transferable trans = new Transferable(){ // �ڲ���
                public DataFlavor[] getTransferDataFlavors() {
                    return new DataFlavor[] { DataFlavor.imageFlavor };
                }
                public boolean isDataFlavorSupported(DataFlavor flavor) {
                    return DataFlavor.imageFlavor.equals(flavor);
                }
                public Object getTransferData(DataFlavor flavor)
                  throws UnsupportedFlavorException, IOException {
                    if(isDataFlavorSupported(flavor))
                        return image;
                    throw new UnsupportedFlavorException(flavor);
                }
            };
            
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans, null);
            JOptionPane.showMessageDialog(this,"�Ѹ��Ƶ�ϵͳճ����!!");
        }catch(Exception exe){
            exe.printStackTrace();
            JOptionPane.showMessageDialog(this
              ,"���Ƶ�ϵͳճ�������!!","����",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //����ر��¼�
    private void doClose(Component c){
        jtp.remove(c);
        c=null;
        System.gc();
    }
    
    public void actionPerformed(ActionEvent ae){
        Object source=ae.getSource();
        if(source==start){
            doStart();
        } else if(source==cancel){
            this.dispose();
        	//System.exit(0);
        }else if(source==java){ // �������
            try{
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(this);
            }catch(Exception exe){
                exe.printStackTrace();
            }
        }else if(source==system){ // �������
            try{
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                SwingUtilities.updateComponentTreeUI(this);
            }catch(Exception exe){
                exe.printStackTrace();
            }
        }
    }
    
    //һ���ڲ���,����ʾһ�����,һ�����Ա��Ž�tabpane�����
    //Ҳ���Լ���һ�״�����͸��Ƶķ���
    private class PicPanel extends JPanel implements ActionListener{
        JButton save,copy,close;//��ʾ����,����,�رյİ�ť
        BufferedImage get;//�õ���ͼƬ
        public PicPanel(BufferedImage get){
            super(new BorderLayout());
            this.get=get;
            initPanel();
        }
        private void initPanel(){
            save=new JButton("����(S)");
            copy=new JButton("���Ƶ�������(C)");
            close=new JButton("�ر�(X)");
            save.setMnemonic('S');
            copy.setMnemonic('C');
            close.setMnemonic('X');
            JPanel buttonPanel=new JPanel();
            buttonPanel.add(copy);
            buttonPanel.add(save);
            buttonPanel.add(close);
            JLabel icon=new JLabel(new ImageIcon(get));
            this.add(new JScrollPane(icon),BorderLayout.CENTER);
            this.add(buttonPanel,BorderLayout.SOUTH);
            save.addActionListener(this);
            copy.addActionListener(this);
            close.addActionListener(this);
        }
        public void actionPerformed(ActionEvent e) {
            Object source=e.getSource();
            if(source==save){
                doSave(get);
            }else if(source==copy){
                doCopy(get);
            }else if(source==close){
                get=null;
                doClose(this);
            }
        }
    }
    
    //����BMP��ʽ�Ĺ�����
    private class BMPfilter extends javax.swing.filechooser.FileFilter{
        public BMPfilter(){
        }
        public boolean accept(File file){
            if(file.toString().toLowerCase().endsWith(".bmp")||
                    file.isDirectory()){
                return true;
            } else
                return false;
        }
        public String getDescription(){
            return "*.BMP(BMPͼ��)";
        }
    }
    
    //����JPG��ʽ�Ĺ�����
    private class JPGfilter extends javax.swing.filechooser.FileFilter{
        public JPGfilter(){
        }
        public boolean accept(File file){
            if(file.toString().toLowerCase().endsWith(".jpg")||
                    file.isDirectory()){
                return true;
            } else
                return false;
        }
        public String getDescription(){
            return "*.JPG(JPGͼ��)";
        }
    }
    
    //����GIF��ʽ�Ĺ�����
    private class GIFfilter extends javax.swing.filechooser.FileFilter{
        public GIFfilter(){
        }
        public boolean accept(File file){
            if(file.toString().toLowerCase().endsWith(".gif")||
                    file.isDirectory()){
                return true;
            } else
                return false;
        }
        public String getDescription(){
            return "*.GIF(GIFͼ��)";
        }
    }
    
    //����PNG��ʽ�Ĺ�����
    private class PNGfilter extends javax.swing.filechooser.FileFilter{
        public boolean accept(File file){
            if(file.toString().toLowerCase().endsWith(".png")||
                    file.isDirectory()){
                return true;
            } else
                return false;
        }
        public String getDescription(){
            return "*.PNG(PNGͼ��)";
        }
    }
    
    //һ����ʱ�࣬������ʾ��ǰ����Ļͼ��
    private class Temp extends JPanel implements MouseListener,MouseMotionListener{
        private BufferedImage bi;
        private int width,height;
        private int startX,startY,endX,endY,tempX,tempY;
        private JFrame jf;
        private Rectangle select=new Rectangle(0,0,0,0);//��ʾѡ�е�����
        private Cursor cs=new Cursor(Cursor.CROSSHAIR_CURSOR);//��ʾһ������µ����״̬��ʮ���ߣ�
        private States current=States.DEFAULT;// ��ʾ��ǰ�ı༭״̬
        private Rectangle[] rec;//��ʾ�˸��༭�������
        //�����ĸ�����,�ֱ��ʾ˭�Ǳ�ѡ�е��������ϵĶ˵�
        public static final int START_X=1;
        public static final int START_Y=2;
        public static final int END_X=3;
        public static final int END_Y=4;
        private int currentX,currentY;//��ǰ��ѡ�е�X��Y,ֻ����������Ҫ�ı�
        private Point p=new Point();//��ǰ����Ƶĵص�
        private boolean showTip=true;//�Ƿ���ʾ��ʾ.���������һ��,����ʾ�Ͳ�����ʾ��
        
        public Temp(JFrame jf,BufferedImage bi,int width,int height){
            this.jf=jf;
            this.bi=bi;
            this.width=width;
            this.height=height;
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            initRecs();
        }
        
        private void initRecs(){
            rec=new Rectangle[8];
            for(int i=0;i<rec.length;i++){
                rec[i]=new Rectangle();
            }
        }
        
        public void paintComponent(Graphics g){
            g.drawImage(bi,0,0,width,height,this);
            g.setColor(Color.RED);
            g.drawLine(startX,startY,endX,startY);
            g.drawLine(startX,endY,endX,endY);
            g.drawLine(startX,startY,startX,endY);
            g.drawLine(endX,startY,endX,endY);
            int x=startX<endX?startX:endX;
            int y=startY<endY?startY:endY;
            select=new Rectangle(x,y,Math.abs(endX-startX),Math.abs(endY-startY));
            int x1=(startX+endX)/2;
            int y1=(startY+endY)/2;
            g.fillRect(x1-2,startY-2,5,5);
            g.fillRect(x1-2,endY-2,5,5);
            g.fillRect(startX-2,y1-2,5,5);
            g.fillRect(endX-2,y1-2,5,5);
            g.fillRect(startX-2,startY-2,5,5);
            g.fillRect(startX-2,endY-2,5,5);
            g.fillRect(endX-2,startY-2,5,5);
            g.fillRect(endX-2,endY-2,5,5);
            rec[0]=new Rectangle(x-5,y-5,10,10);
            rec[1]=new Rectangle(x1-5,y-5,10,10);
            rec[2]=new Rectangle((startX>endX?startX:endX)-5,y-5,10,10);
            rec[3]=new Rectangle((startX>endX?startX:endX)-5,y1-5,10,10);
            rec[4]=new Rectangle((startX>endX?startX:endX)-5,(startY>endY?startY:endY)-5,10,10);
            rec[5]=new Rectangle(x1-5,(startY>endY?startY:endY)-5,10,10);
            rec[6]=new Rectangle(x-5,(startY>endY?startY:endY)-5,10,10);
            rec[7]=new Rectangle(x-5,y1-5,10,10);
            if(showTip){
                g.setColor(Color.CYAN);
                g.fillRect(p.x,p.y,170,20);
                g.setColor(Color.RED);
                g.drawRect(p.x,p.y,170,20);
                g.setColor(Color.BLACK);
                g.drawString("�밴ס����������ѡ���ͼ��",p.x,p.y+15);
            }
        }
        
        //���ݶ��������Ȱ˸��������ѡ�е�Ҫ�޸ĵ�X��Y������
        private void initSelect(States state){
            switch(state){
                case DEFAULT:
                    currentX=0;
                    currentY=0;
                    break;
                case EAST:
                    currentX=(endX>startX?END_X:START_X);
                    currentY=0;
                    break;
                case WEST:
                    currentX=(endX>startX?START_X:END_X);
                    currentY=0;
                    break;
                case NORTH:
                    currentX=0;
                    currentY=(startY>endY?END_Y:START_Y);
                    break;
                case SOUTH:
                    currentX=0;
                    currentY=(startY>endY?START_Y:END_Y);
                    break;
                case NORTH_EAST:
                    currentY=(startY>endY?END_Y:START_Y);
                    currentX=(endX>startX?END_X:START_X);
                    break;
                case NORTH_WEST:
                    currentY=(startY>endY?END_Y:START_Y);
                    currentX=(endX>startX?START_X:END_X);
                    break;
                case SOUTH_EAST:
                    currentY=(startY>endY?START_Y:END_Y);
                    currentX=(endX>startX?END_X:START_X);
                    break;
                case SOUTH_WEST:
                    currentY=(startY>endY?START_Y:END_Y);
                    currentX=(endX>startX?START_X:END_X);
                    break;
                default:
                    currentX=0;
                    currentY=0;
                    break;
            }
        }
        
        public void mouseMoved(MouseEvent me){
            doMouseMoved(me);
            initSelect(current); // current����ǰ״̬��state��
            if(showTip){
                p=me.getPoint();
                repaint();
            }
        }
        
        //���ⶨ��һ��������������ƶ�,��Ϊ��ÿ�ζ��ܳ�ʼ��һ����Ҫѡ�������
        private void doMouseMoved(MouseEvent me){
            if(select.contains(me.getPoint())){
                this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
                current=States.MOVE;
            } else{
                States[] st=States.values();
                for(int i=0;i<rec.length;i++){
                    if(rec[i].contains(me.getPoint())){
                        current=st[i];
                        this.setCursor(st[i].getCursor());
                        return;
                    }
                }
                this.setCursor(cs);
                current=States.DEFAULT;
            }
        }
        
        public void mouseExited(MouseEvent me){
        }
        
        public void mouseEntered(MouseEvent me){
        }
        
        public void mouseDragged(MouseEvent me){
            int x=me.getX();
            int y=me.getY();
            // �ֱ���һϵ�еģ���꣩״̬��ö��ֵ��
            if(current==States.MOVE){
                startX+=(x-tempX);
                startY+=(y-tempY);
                endX+=(x-tempX);
                endY+=(y-tempY);
                tempX=x;
                tempY=y;
            }else if(current==States.EAST||current==States.WEST){
                if(currentX==START_X){
                    startX+=(x-tempX);
                    tempX=x;
                }else{
                    endX+=(x-tempX);
                    tempX=x;
                }
            }else if(current==States.NORTH||current==States.SOUTH){
                if(currentY==START_Y){
                    startY+=(y-tempY);
                    tempY=y;
                }else{
                    endY+=(y-tempY);
                    tempY=y;
                }
            }else if(current==States.NORTH_EAST||current==States.NORTH_EAST||
                    current==States.SOUTH_EAST||current==States.SOUTH_WEST){
                if(currentY==START_Y){
                    startY+=(y-tempY);
                    tempY=y;
                }else{
                    endY+=(y-tempY);
                    tempY=y;
                }
                if(currentX==START_X){
                    startX+=(x-tempX);
                    tempX=x;
                }else{
                    endX+=(x-tempX);
                    tempX=x;
                }                
            }else{
                startX=tempX;
                startY=tempY;
                endX=me.getX();
                endY=me.getY();
            }
            this.repaint();
        }
        
        public void mousePressed(MouseEvent me){
            showTip=false;
            tempX=me.getX();
            tempY=me.getY();
        }
        
        public void mouseReleased(MouseEvent me){
            if(me.isPopupTrigger()){ // �Ҽ�
                if(current==States.MOVE){
                    showTip=true;
                    p=me.getPoint();
                    startX=0;
                    startY=0;
                    endX=0;
                    endY=0;
                    repaint();
                } else{ // ��ͨ���
                    jf.dispose();
                    updates();
                }
            }
        }
        
        public void mouseClicked(MouseEvent me){
            if(me.getClickCount()==2){
                //Rectangle rec=new Rectangle(startX,startY,Math.abs(endX-startX),Math.abs(endY-startY));
                Point p=me.getPoint();
                if(select.contains(p)){
                    if(select.x+select.width<this.getWidth()&&select.y+select.height<this.getHeight()){
                        get=bi.getSubimage(select.x,select.y,select.width,select.height);
                        jf.dispose();
                        updates();
                    }else{
                        int wid=select.width,het=select.height;
                        if(select.x+select.width>=this.getWidth()){
                            wid=this.getWidth()-select.x;
                        }
                        if(select.y+select.height>=this.getHeight()){
                            het=this.getHeight()-select.y;
                        }
                        get=bi.getSubimage(select.x,select.y,wid,het);
                        jf.dispose();
                        updates();
                    }
                }
            }
        }
    }
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new CaptureScreen();
            }
        });
    }
}

//һЩ��ʾ״̬��ö��
enum States{
    NORTH_WEST(new Cursor(Cursor.NW_RESIZE_CURSOR)),//��ʾ������
    NORTH(new Cursor(Cursor.N_RESIZE_CURSOR)),
    NORTH_EAST(new Cursor(Cursor.NE_RESIZE_CURSOR)),
    EAST(new Cursor(Cursor.E_RESIZE_CURSOR)),
    SOUTH_EAST(new Cursor(Cursor.SE_RESIZE_CURSOR)),
    SOUTH(new Cursor(Cursor.S_RESIZE_CURSOR)),
    SOUTH_WEST(new Cursor(Cursor.SW_RESIZE_CURSOR)),
    WEST(new Cursor(Cursor.W_RESIZE_CURSOR)),
    MOVE(new Cursor(Cursor.MOVE_CURSOR)),
    DEFAULT(new Cursor(Cursor.DEFAULT_CURSOR));
    
    private Cursor cs;
    
    States(Cursor cs){
        this.cs=cs;
    }
    
    public Cursor getCursor(){
        return cs;
    }
}
