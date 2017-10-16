import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
//////////*服务器窗口类*///////////////
public class ServerFrame extends JFrame implements ActionListener
{
	//服务器信息面板
	JPanel pnlServer,pnlServerInfo;//服务器日志面板；服务器状态面板
	JButton btnStop,btnSaveLog,lblExit;    //btnStop:关闭服务器,btnSaveLog：保存日志
	TextArea taLog;                //日志显示文本区
	//JTabbedPane tpServer;          //两个面板的选项卡
	JLabel  lblserver1,lblServer,lblStatus,lblNumber,lblMax,lblServerName,lblProtocol,lblIP,lblPort,lblLog;//lblLog:服务器日志;
	JTextField txtStatus,txtNumber,txtMax,txtServerName,txtProtocol,txtIP,txtPort;
	
    
	
	
	
	//用户信息面板
	//JPanel pnlUser;//用户选择面板
	TextArea  taMessage;// 用户信息文本区
	JLabel lblMessage,lblUser,lblNotice,lblUserCount;
	java.awt.List lstUser;//用户列表
	ScrollPane spUser;//用户列表的滚动条
	JTextField txtNotice;
	JButton btnSend,btnKick;
	
	

	public 	ServerFrame()
	{
	 	//服务器窗口
	 	super("姜曙光聊天服务器");
	  	setSize(800,550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //      在屏幕居中显示
        Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();
        Dimension fra=this.getSize();
        if(fra.width>scr.width)
        {
            fra.width=scr.width;
        }
        if(fra.height>scr.height)
        {
            fra.height=scr.height;
        }
        this.setLocation((scr.width-fra.width)/2,(scr.height-fra.height)/2);
                 
    

 //==========服务器信息面板=========================
	 	pnlServer=new JPanel();
	 	pnlServer.setLayout(null);  //组件用手动布局
	 	pnlServer.setBackground(new Color(255,130,203));
	 	
	 	pnlServerInfo=new JPanel(new GridLayout(12,1));    // GridLayout(14,1)  布局 14行 1列
        pnlServerInfo.setBackground(new Color(255,130,203));
        pnlServerInfo.setFont(new Font("宋体",0,12));
        pnlServerInfo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(""),BorderFactory.createEmptyBorder(1,1,1,1)));
         
        
        lblStatus=new JLabel("当前状态:");
        lblStatus.setForeground(Color.YELLOW);
        lblStatus.setFont(new Font("宋体",0,12));
        txtStatus=new JTextField(10);
        txtStatus.setBackground(Color.decode("#d6f4f2"));
        txtStatus.setFont(new Font("宋体",0,12));
        txtStatus.setEditable(false);
         
        lblNumber=new JLabel("当前在线人数:");
        lblNumber.setForeground(Color.YELLOW);
        lblNumber.setFont(new Font("宋体",0,12));
        txtNumber=new JTextField("0 人",10);
        txtNumber.setBackground(Color.decode("#d6f4f2"));
        txtNumber.setFont(new Font("宋体",0,12));
        txtNumber.setEditable(false);
         
        lblMax=new JLabel("最多在线人数:");
        lblMax.setForeground(Color.YELLOW);
        lblMax.setFont(new Font("宋体",0,12));
        txtMax=new JTextField("50 人",10);
        txtMax.setBackground(Color.decode("#d6f4f2"));
        txtMax.setFont(new Font("宋体",0,12));
        txtMax.setEditable(false);
         
        lblServerName=new JLabel("服务器名称:");
        lblServerName.setForeground(Color.YELLOW);
        lblServerName.setFont(new Font("宋体",0,12));
        txtServerName=new JTextField(10);
        txtServerName.setBackground(Color.decode("#d6f4f2"));
        txtServerName.setFont(new Font("宋体",0,12));
        txtServerName.setEditable(false);
         
        lblProtocol=new JLabel("访问协议:");
        lblProtocol.setForeground(Color.YELLOW);
        lblProtocol.setFont(new Font("宋体",0,12));
        txtProtocol=new JTextField("HTTP",10);
        txtProtocol.setBackground(Color.decode("#d6f4f2"));
        txtProtocol.setFont(new Font("宋体",0,12));
        txtProtocol.setEditable(false);
         
        lblIP=new JLabel("服务器IP:");
        lblIP.setForeground(Color.YELLOW);
        lblIP.setFont(new Font("宋体",0,12));
        txtIP=new JTextField(10);
        txtIP.setBackground(Color.decode("#d6f4f2"));
        txtIP.setFont(new Font("宋体",0,12));
        txtIP.setEditable(false);
         
        lblPort=new JLabel("服务器端口:");
        lblPort.setForeground(Color.YELLOW);
        lblPort.setFont(new Font("宋体",0,12));
        txtPort=new JTextField("8000",10);
        txtPort.setBackground(Color.decode("#d6f4f2"));
        txtPort.setFont(new Font("宋体",0,12));
        txtPort.setEditable(false);
        
        
         
        btnStop=new JButton("关闭服务器");
        btnStop.setBackground(Color.ORANGE);
        btnStop.setFont(new Font("宋体",0,12));
        
        lblLog=new JLabel("[用户日志]");
        lblLog.setForeground(Color.red);
        lblLog.setFont(new Font("宋体",0,12));
         
        taLog=new TextArea(20,50);
        taLog.setFont(new Font("宋体",0,12));
        btnSaveLog=new JButton("保存日志");
        btnSaveLog.setBackground(Color.ORANGE);
        btnSaveLog.setFont(new Font("宋体",0,12));
	 	
	    pnlServerInfo.add(lblStatus);
        pnlServerInfo.add(txtStatus);        
        pnlServerInfo.add(lblNumber);
        pnlServerInfo.add(txtNumber);
        pnlServerInfo.add(lblMax);
        pnlServerInfo.add(txtMax);
        //pnlServerInfo.add(lblServerName);
        //pnlServerInfo.add(txtServerName);
//        pnlServerInfo.add(lblProtocol);
//        pnlServerInfo.add(txtProtocol);
        pnlServerInfo.add(lblIP);
        pnlServerInfo.add(txtIP);
        pnlServerInfo.add(lblPort);
        pnlServerInfo.add(txtPort);
         
        pnlServerInfo.setBounds(30,115,100,340);
        lblLog.setBounds(135,85,100,30);
        taLog.setBounds(135,115,250,340);
        btnStop.setBounds(30,480,120,30);
        btnSaveLog.setBounds(250,460,120,30);
        
        //===========在线用户面板====================          
       /* pnlUser=new JPanel();
        pnlUser.setLayout(null);
        pnlUser.setBackground(new Color(255,130,203));
        pnlUser.setFont(new Font("宋体",0,12));*/
        lblMessage=new JLabel("[聊天消息总汇]"); 
        lblMessage.setFont(new Font("宋体",0,12));
        lblMessage.setForeground(Color.red);
        taMessage=new TextArea(20,20);
        taMessage.setFont(new Font("宋体",0,12));
        lblNotice=new JLabel(" 系统消息：");
        lblNotice.setFont(new Font("宋体",0,12));
        txtNotice=new JTextField (20);
        txtNotice.setFont(new Font("宋体",0,12));
        btnSend=new JButton("发送");
        lblExit=new JButton("关闭服务器");
        btnSend.setBackground(Color.ORANGE);
        btnSend.setFont(new Font("宋体",0,12));
        
         
        lblUserCount=new JLabel("在线总人数 0 人");
        lblUserCount.setFont(new Font("宋体",0,12));
		 
		btnKick=new JButton("踢人(K)");
        btnKick.setBackground(Color.ORANGE);
        btnKick.setFont(new Font("宋体",0,12));
        lblUser=new JLabel("[在线用户列表]"); 
        lblUser.setFont(new Font("宋体",0,12));
        lblUser.setForeground(Color.red);
        
        lstUser=new java.awt.List();
        lstUser.setFont(new Font("宋体",0,12));
        //lstUser.setVisibleRowCount(17);
        //lstUser.setFixedCellWidth(180);
		//lstUser.setFixedCellHeight(18);
        //lstUser.setListData(listVector);
         
        spUser=new ScrollPane();
        spUser.setBackground(Color.decode("#d6f4f2"));
        spUser.setFont(new Font("宋体",0,12));
		//spUser.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//spUser.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		spUser.add(lstUser);
		
		lblMessage.setBounds(525,85,150,30);
		taMessage.setBounds(525,115,250,340);
		lblUser.setBounds(395,85,100,30);
		spUser.setBounds(395,115,120,340);
		lblNotice.setBounds(480,460,70,25);
		txtNotice.setBounds(545,460,240,25);
		btnSend.setBounds(710,490,80,25);
		lblUserCount.setBounds(320,410,100,25);
		btnKick.setBounds(440,410,80,25);    
        
//==========服务器信息面板=========================      
        
        pnlServer.add(pnlServerInfo); 
        pnlServer.add(lblLog);
        pnlServer.add(taLog);
        pnlServer.add(btnStop);
        pnlServer.add(btnSaveLog);
        pnlServer.add(taMessage);
        pnlServer.add(spUser);
        pnlServer.add(lblNotice);
        pnlServer.add(txtNotice);
        pnlServer.add(btnSend);
        pnlServer.add(lblMessage);
        pnlServer.add(lblUser);
        
   
        
  //     设置背景图片
  		Icon logo1 = new ImageIcon("images\\server.jpg");
 	 	lblServer = new JLabel(logo1);
  		lblServer.setBounds(0,-20,800,550);
  		pnlServer.add(lblServer);
        
        this.getContentPane().add(pnlServer);
        setVisible(true);
	}
	public void actionPerformed(ActionEvent evt)
	{

	}
	
	public static void main(String args[])
	{
		new ServerFrame();
	}
}