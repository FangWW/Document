
import java.awt.*;

import javax.swing.*;
import java.io.File;
      
public class JProgressbard extends JFrame 
{
	private JProgressBar progressBar; 
	JLabel  lacomplement; 
	JPanel  Progressbar;
	JButton bucancel;
	//将窗口定位
	Dimension scrnsize;
	Toolkit toolkit=Toolkit.getDefaultToolkit();
	
	public JProgressbard() 
    {
       super("正在传送文件【  】.....");
       setSize(400, 200);
       
       Progressbar=new JPanel();
       this.getContentPane().add(Progressbar);
       Progressbar.setLayout(null);
       
	  
       //创建进度条
       progressBar = new JProgressBar();
       progressBar.setMinimum(0);         //设置最小值,最大值,初值
       progressBar.setMaximum(100);
       progressBar.setValue(0);           //设置进度条的当前值为0；
       progressBar.setStringPainted(true);//显示进度条进度文本
       progressBar.setBorderPainted(true);//显示进度条边框
       //progressBar.setPreferredSize(new Dimension(200,30));//设置进度条大小,背景色,前景色
       progressBar.setBackground(Color.WHITE);
       progressBar.setForeground(Color.GREEN);
       
       
       
       lacomplement=new JLabel("文件传送已完成.....");
        bucancel=new JButton("取消");
       
       lacomplement.setBounds(10, 10, 150, 30);
       progressBar.setBounds(50,50,300,30);
       bucancel.setBounds(300,100,60,30);
      
       Progressbar.add(lacomplement);
       Progressbar.add(progressBar);
       Progressbar.add(bucancel);
     
       
     
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       // 将窗口定位在屏幕的中央
       scrnsize=toolkit.getScreenSize();
       this.setLocation(scrnsize.width/2-this.getWidth()/2,
    	                 scrnsize.height/2-this.getHeight()/2);
       }
	
    public JProgressbard(File f) 
    {
       super("正在传送文件【  】.....");
       setSize(400, 200);
       
       Progressbar=new JPanel();
       this.getContentPane().add(Progressbar);
       Progressbar.setLayout(null);
       
	  
       //创建进度条
       progressBar = new JProgressBar();
       progressBar.setMinimum(0);         //设置最小值,最大值,初值
       progressBar.setMaximum(100);
       progressBar.setValue(0);           //设置进度条的当前值为0；
       progressBar.setStringPainted(true);//显示进度条进度文本
       progressBar.setBorderPainted(true);//显示进度条边框
       //progressBar.setPreferredSize(new Dimension(200,30));//设置进度条大小,背景色,前景色
       progressBar.setBackground(Color.WHITE);
       progressBar.setForeground(Color.GREEN);
       
       
       
       lacomplement=new JLabel("文件传送已完成.....");
        bucancel=new JButton("取消");
       
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
   