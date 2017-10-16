//服务器端程序
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
public class SocketServerExample extends Frame implements ActionListener
{ Label label=new Label("输入聊天信息");
  TextField tf= new TextField(20);
  TextArea ta=new TextArea( );
      Panel panel=new Panel( );//创建面板对象
  ServerSocket server;
  Socket Client;
  InputStream DataIn;
  OutputStream DataOut;
  public SocketServerExample( )
  { super("这里是服务器");
    setSize(300,180);
    panel.add(label);//在面板上添加标签
    panel.add(tf);//在面板上添加文本框
    tf.addActionListener(this);//注册
    add("North",panel);//在窗体上添加面板
    add("Center",ta);//在窗体上添加文本区
    addWindowListener(new WindowAdapter( )
    { public void windowClosing(WindowEvent e)
    { System.exit(0);}});
    show( );
    try
    	{ server =new ServerSocket(5000);
    	   Client=server.accept( );
    	   ta.append("已经和客户机连接:"+Client.getInetAddress( ).getHostName( )+"\n\n");
    	   DataIn=Client.getInputStream( );
    	   DataOut=Client.getOutputStream( );	
    }
    catch(IOException ioe){ }
    while(true)
    {
    	try{
    		byte buff[ ]=new byte[512];//缓冲数组
    		DataIn.read(buff);
    		String str=new String(buff);//接受客户端发送的数据包
    		ta.append("客户机说:"+str+"\n");
    	}
    	catch(IOException ioe){ }
    }
    }
    public static void main(String args[ ])
    {
    	new SocketServerExample( );
    }
    public void actionPerformed(ActionEvent e)//事件处理程序
    {
    	try 
    		{
    		String str=new String(tf.getText());
    		byte buf[ ]=str.getBytes( );
    		tf.setText(" ");
    		DataOut.write(buf);
    		ta.append("服务器说:"+str+"\n");
    	}
    		catch(IOException ioe){ }
    }
    }
  
