//�ͻ��˳���
public class SocketClientExample extends Frame implements ActionListener
{ Label label=new Label("����������Ϣ");
  TextField tf= new TextField(20);
  TextArea ta=new TextArea( );
  Panel panel=new Panel( );//����������
  Socket Client;
  InputStream DataIn;
  OutputStream DataOut;
  public SocketClientExample( )
  { super("�����ǿͻ���");
    setSize(300,180);
    panel.add(label);//���������ӱ�ǩ
    panel.add(tf);//�����������ı���
    tf.addActionListener(this);//ע��
    add("North",panel);//�ڴ�����������
    add("Center",ta);//�ڴ���������ı���
    addWindowListener(new WindowAdapter( )
    { public void windowClosing(WindowEvent e)
    { System.exit(0);}});
    show( );
    try
    	{  Client=new Socket(InetAddress.getLocalHost( ),5000);
    	   ta.append("�Ѿ��ͷ���������:"+Client.getInetAddress( ).getHostName( )+"\n\n");
    	   DataIn=Client.getInputStream( );
    	   DataOut=Client.getOutputStream( );	
    }
    catch(IOException ioe){ }
    while(true)
    {
    	try{
    		byte buff[ ]=new byte[512];//��������
    		DataIn.read(buff);
    		String str=new String(buff);//���ܿͻ��˷��͵����ݰ�
    		ta.append("������˵:"+str+"\n");
    	}
    	catch(IOException ioe){ }
    }
    }
    public static void main(String args[ ])
    {
    	new SocketClientExample( );
    }
    public void actionPerformed(ActionEvent e)//�¼��������
    {
    	try 
    		{
    		String str=new String(tf.getText());
    		byte buf[ ]=str.getBytes( );
    		tf.setText(" ");
    		DataOut.write(buf);
    		ta.append("�ͻ���˵:"+str+"\n");
    	}
    		catch(IOException ioe){ }
    }
    }
  
