
import java.io.File;
      
public class JProgressbard extends JFrame 
{
	private JProgressBar progressBar; 
	JLabel  lacomplement; 
	JPanel  Progressbar;
	JButton bucancel;
	//�����ڶ�λ
	Dimension scrnsize;
	Toolkit toolkit=Toolkit.getDefaultToolkit();
	
	public JProgressbard() 
    {
       super("���ڴ����ļ���  ��.....");
       setSize(400, 200);
       
       Progressbar=new JPanel();
       this.getContentPane().add(Progressbar);
       Progressbar.setLayout(null);
       
	  
       //����������
       progressBar = new JProgressBar();
       progressBar.setMinimum(0);         //������Сֵ,���ֵ,��ֵ
       progressBar.setMaximum(100);
       progressBar.setValue(0);           //���ý������ĵ�ǰֵΪ0��
       progressBar.setStringPainted(true);//��ʾ�����������ı�
       progressBar.setBorderPainted(true);//��ʾ�������߿�
       //progressBar.setPreferredSize(new Dimension(200,30));//���ý�������С,����ɫ,ǰ��ɫ
       progressBar.setBackground(Color.WHITE);
       progressBar.setForeground(Color.GREEN);
       
       
       
       lacomplement=new JLabel("�ļ����������.....");
        bucancel=new JButton("ȡ��");
       
       lacomplement.setBounds(10, 10, 150, 30);
       progressBar.setBounds(50,50,300,30);
       bucancel.setBounds(300,100,60,30);
      
       Progressbar.add(lacomplement);
       Progressbar.add(progressBar);
       Progressbar.add(bucancel);
     
       
     
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       // �����ڶ�λ����Ļ������
       scrnsize=toolkit.getScreenSize();
       this.setLocation(scrnsize.width/2-this.getWidth()/2,
    	                 scrnsize.height/2-this.getHeight()/2);
       }
	
    public JProgressbard(File f) 
    {
       super("���ڴ����ļ���  ��.....");
       setSize(400, 200);
       
       Progressbar=new JPanel();
       this.getContentPane().add(Progressbar);
       Progressbar.setLayout(null);
       
	  
       //����������
       progressBar = new JProgressBar();
       progressBar.setMinimum(0);         //������Сֵ,���ֵ,��ֵ
       progressBar.setMaximum(100);
       progressBar.setValue(0);           //���ý������ĵ�ǰֵΪ0��
       progressBar.setStringPainted(true);//��ʾ�����������ı�
       progressBar.setBorderPainted(true);//��ʾ�������߿�
       //progressBar.setPreferredSize(new Dimension(200,30));//���ý�������С,����ɫ,ǰ��ɫ
       progressBar.setBackground(Color.WHITE);
       progressBar.setForeground(Color.GREEN);
       
       
       
       lacomplement=new JLabel("�ļ����������.....");
        bucancel=new JButton("ȡ��");
       
       lacomplement.setBounds(10, 10, 150, 30);
       progressBar.setBounds(50,50,300,30);
       bucancel.setBounds(300,100,60,30);
      
       Progressbar.add(lacomplement);
       Progressbar.add(progressBar);
       Progressbar.add(bucancel);
     
       
     
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       scrnsize=toolkit.getScreenSize();
      this.setLocation(scrnsize.width/2-this.getWidth()/2,
   	                 scrnsize.height/2-this.getHeight()/2);
    }

    public static void main(String args[]) 
    {
       new JProgressbard();
    }
}
   