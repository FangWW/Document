package chat;

//import Client;

import javax.swing.JFileChooser;
import javax.swing.JProgressBar;
import java.util.Date;
import java.util.Calendar;

import javax.swing.JTextArea; //import RTFReceiveFrame;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.net.*;
import java.io.*;
import java.net.Socket;

public class ChatFrame extends JFrame implements ActionListener, ItemListener,
		Runnable, KeyListener
{

	sendfilethread sendthread;// 发送文件线程;
	acceptfilethread acceptthread;// 接收文件线程
	Socket acceptfilesocket; // 接收文件传输连接
	Socket sendfilesocket; // 发送文件传输连接
	ServerSocket fileserver; // 传输文件服务器
	String filemsg = null; // 保存文件输入流
	String sendfilename = null;// 保存欲发送的文件名
	String IP;// 保存本机IP= InetAddress.getLocalHost();//
	Boolean acceptboolean = false;
	Boolean sendboolean = false;
	
	int port = 6200;
	JProgressBar acceptProgressbar;// 接收进度条
	JProgressBar sendProgressbar; // 发送进度条

	private Box leftbox = null;
	private Box rightbox = null;
	private Box leftrightbox = null;
	private Box rightleftbox = null;
	private JButton sendfile; // 发送文件按钮
	private JButton cancelsendfile; // 取消发送文件按钮
	private JButton acceptfile; // 接收文件按钮
	private JButton refusefile; // 拒绝接收文件按钮
	private MyTextArea sendfileArea = null; // 发送文件显示区
	private JTextPane acceptfileArea = null; // 接收文件显示区

	private static final long serialVersionUID = 1L;
	// ***************菜单栏***********************
	private Box box = null; // 放输入组件的容器
	private JComboBox fontName = null, fontSize = null, fontStyle = null,
			fontColor = null;// sendings = null;// fontBackColor = null;
	// 字体名称;字号大小;文字样式;文字颜色;传送
	private StyledDocument doc = null;

	private JFileChooser jfc;// 文件保存路径选择器
	// private JFrame fr;

	private JTextPane commonArea = null; // 公共发言区
	private JTextPane myMsgArea = null;// 我的频道发言区
	public JComboBox perponsComboBox; // 下拉菜单
	private JTextArea inMsgField; // 发言输入框
	private JCheckBox privateTalk;// 私聊checkbox
	private boolean privateTalkFlag = false; // 是否是私聊,默认值为假
	private JButton sentButton; // 发送消息按钮

	private JButton screenCapture;// 截屏按钮
	private JMenuItem menuItem;
	private JMenuItem cMenuItem;
	public BufferedReader in;
	public PrintWriter out;
	public String myName;
	private String withWho = "所有人";
	String outmsg;// 发送的信息
	String mywords;// 要说的话
	JPanel centerPanel;
	JScrollPane commonAreaScroll;
	JScrollPane myMsgAreaScroll;
	JScrollPane inMsgFieldScroll;

	public PList plist;

	public ChatFrame(String host)
	{
		super(host + "的聊天室");
		try
		{ // 使用Windows的界面风格
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		myName = host;
		plist = new PList(this);

		try
		{
			InetAddress addr = InetAddress.getLocalHost();
			IP = addr.getHostAddress().toString();// 获得本机IP

		} catch (Exception e)
		{
			System.out.print("无法获取本地主机");

		}// System.out.println("本机的ip=" + inet.getHostAddress());

		acceptProgressbar = new JProgressBar();
		acceptProgressbar.setOrientation(JProgressBar.HORIZONTAL);
		// acceptProgressbar.setMinimum(0);
		// acceptProgressbar.setMaximum(100);
		acceptProgressbar.setValue(0);
		acceptProgressbar.setStringPainted(true);

		sendProgressbar = new JProgressBar();
		sendProgressbar.setOrientation(JProgressBar.HORIZONTAL);
		// sendProgressbar.setMinimum(0);
		// sendProgressbar.setMaximum(100);
		sendProgressbar.setValue(0);
		sendProgressbar.setStringPainted(true);

		// *****聊天室右侧************//
		//Icon sentIcon = new ImageIcon("1.gif");
		//sendfile = new JButton(sentIcon); // ??????????????
		sendfile = new JButton("发送文件"); // ??????????????
		cancelsendfile = new JButton("取消发送"); // ??????????????
		acceptfile = new JButton("接收文件"); // ??????????????
		refusefile = new JButton("拒绝文件"); // 拒绝接收文件按钮
		acceptfileArea = new JTextPane(); // 接收文件显示区
		acceptfileArea.setEditable(false); // 不可从外部写
		sendfileArea = new MyTextArea(); // 发送文件显示区
		sendfileArea.setEditable(false);
		leftrightbox = Box.createHorizontalBox(); // 行结构

		leftrightbox.add(acceptfile, BorderLayout.WEST);
		// leftrightbox.add(jpb,BorderLayout.CENTER);
		leftrightbox.add(refusefile, BorderLayout.EAST);
		Box rightabove = Box.createVerticalBox();
		rightabove.add(leftrightbox);
		rightabove.add(acceptProgressbar);

		// leftrightbox.add(jpb,BorderLayout.CENTER);

		rightbox = Box.createVerticalBox();// 竖结构
		rightbox.add(Box.createVerticalStrut(10));
		rightbox.add(rightabove);
		rightbox.add(Box.createVerticalStrut(10));

		JScrollPane inMsgFieldScroll1 = new JScrollPane(acceptfileArea);
		inMsgFieldScroll1
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		inMsgFieldScroll1.setBorder(BorderFactory.createTitledBorder("待接收的文件"));
		inMsgFieldScroll1.setBackground(new Color(188, 193, 199));
		rightbox.add(inMsgFieldScroll1, BorderLayout.CENTER);
		rightbox.setBackground(new Color(250, 0, 2));// /

		rightbox.add(Box.createVerticalStrut(10)); // /
		rightleftbox = Box.createHorizontalBox();
		rightleftbox.add(sendfile, BorderLayout.WEST);
		rightleftbox.add(cancelsendfile, BorderLayout.EAST);
		Box rightmiddle = Box.createVerticalBox();
		rightmiddle.add(rightleftbox);
		rightmiddle.add(sendProgressbar);
		rightbox.add(rightmiddle, BorderLayout.CENTER);

		JScrollPane inMsgFieldScroll2 = new JScrollPane(sendfileArea);
		inMsgFieldScroll2
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		// inMsgFieldScroll2
		//.setHorizontalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
		// );
		inMsgFieldScroll2.setBorder(BorderFactory.createTitledBorder("欲发送的文件"));
		inMsgFieldScroll2.setBackground(new Color(188, 193, 199));
		rightbox.add(inMsgFieldScroll2, BorderLayout.CENTER);
		rightbox.setBackground(new Color(0, 0, 255));
		// rightbox.add(sendfileArea);;

		// ****************************************************//

		// *****聊天室右侧************//
		// *********************聊天室顶层**************//

		JPanel upperPanel = new JPanel();
		String[] str_name = { "宋体", "黑体", "Dialog", "Gulim" };
		String[] str_Size = { "12", "14", "18", "22", "30", "40" };
		String[] str_Style = { "常规", "斜体", "粗体", "粗斜体" };
		String[] str_Color = { "黑色", "红色", "蓝色", "黄色", "绿色" };
		// String[] str_BackColor = { "无色", "灰色", "淡红", "淡蓝", "淡黄", "淡绿" };
		// String[] str_sendings = { "文件", "图片" };
		fontName = new JComboBox(str_name); // 字体名称
		fontSize = new JComboBox(str_Size); // 字号
		fontStyle = new JComboBox(str_Style); // 样式
		fontColor = new JComboBox(str_Color); // 颜色
		// fontBackColor = new JComboBox(str_BackColor); // 背景颜色
		// sendings = new JComboBox(str_sendings);

		box = Box.createVerticalBox(); // 竖结构
		Box box_1 = Box.createHorizontalBox(); // 横结构
		Box box_2 = Box.createVerticalBox(); // 横结构
		box.add(box_1);
		// box.add(Box.createVerticalStrut(8)); // 两行的间距
		// box.add(box_2);
		box.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8)); // 8个的边距
		// 开始将所需组件加入容器
		box_1.add(new JLabel("字体：")); // 加入标签
		box_1.add(fontName); // 加入组件
		box_1.add(Box.createHorizontalStrut(8)); // 间距
		box_1.add(new JLabel("样式："));
		box_1.add(fontStyle);
		box_1.add(Box.createHorizontalStrut(8));
		box_1.add(new JLabel("字号："));
		box_1.add(fontSize);
		box_1.add(Box.createHorizontalStrut(8));
		box_1.add(new JLabel("颜色："));
		box_1.add(fontColor);
		box_1.add(Box.createHorizontalStrut(8));
		// box_1.add(new JLabel("传送: "));
		// box_1.add(sendings);
		box_1.add(Box.createHorizontalStrut(8));

		upperPanel.add(box, BorderLayout.SOUTH);

		// **************中间聊天室两个窗口***********************//

		Border brd = BorderFactory.createMatteBorder(// 边框修饰色
				2, 2, 2, 1, new Color(125, 161, 253));

		centerPanel = new JPanel(new BorderLayout());

		commonArea = new JTextPane(); // 公共言论区
		commonArea.setBorder(brd);
		commonArea.setEditable(false); // 不可编辑
		commonArea.getScrollableUnitIncrement(new Rectangle(10, 20),
				SwingConstants.VERTICAL, -2);
		// Less than zero to scroll up/left, greater than zero for down/right
		commonAreaScroll = new JScrollPane(commonArea);
		commonAreaScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);// 设置滚动条什么时候出现
		commonAreaScroll
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		commonAreaScroll.setBorder(BorderFactory.createTitledBorder("群聊区"));

		box_2.add(commonAreaScroll);
		box_2.add(Box.createVerticalStrut(2));

		myMsgArea = new JTextPane(); // 我的发言
		// myMsgArea.setSize(20, 30);
		myMsgArea.setBorder(brd);
		myMsgArea.setEditable(false);
		//myMsgArea.setCaretPosition(myMsgArea.getText().length());
		myMsgAreaScroll = new JScrollPane(myMsgArea);
		myMsgAreaScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		myMsgAreaScroll
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		myMsgAreaScroll.setBorder(BorderFactory.createTitledBorder("悄悄话"));
		//myMsgAreaScroll.setValue(myMsgAreaScroll.getMaximumSize());
		//myMsgAreaScroll.s;

		box_2.add(myMsgAreaScroll);
		centerPanel.add(box_2);

		// ******************输入发送区***********************
		JPanel centerLowerPanel = new JPanel(new BorderLayout());
		JPanel tempPanel1 = new JPanel(new BorderLayout());
		JPanel tempPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));

		JLabel withWho = new JLabel("对");
		perponsComboBox = new JComboBox();
		perponsComboBox.addItem("所有人");
		privateTalk = new JCheckBox("私聊");

		inMsgField = new JTextArea(3, 2);
		inMsgField.setBorder(brd);
		inMsgField.setBackground(new Color(248, 243, 209));// 设置聊天框体的颜色
		inMsgField.addKeyListener(this);
		 //Icon sentIcon = new ImageIcon("2.gif");
		// sentButton= new JButton(sentIcon);
		sentButton = new JButton("发送");

		// JTextPane acceptfileArea = null; // 接收文件显示区

		screenCapture = new JButton("截屏");
		// Icon sentIcon = new ImageIcon("ButtonSenddown.gif");
		// sentButton.setIcon(sentIcon);
		inMsgFieldScroll = new JScrollPane(inMsgField);
		inMsgFieldScroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		inMsgFieldScroll.setBorder(BorderFactory.createTitledBorder("编辑区"));
		tempPanel1.add(inMsgFieldScroll, BorderLayout.CENTER);

		sentButton.setBackground(Color.WHITE);
		tempPanel2.add(withWho);
		tempPanel2.add(new JLabel(" "));
		tempPanel2.add(perponsComboBox);
		tempPanel2.add(new JLabel(" "));
		tempPanel2.add(privateTalk);
		tempPanel2.add(new JLabel("   "));
		tempPanel2.add(screenCapture);
		tempPanel2.add(new JLabel("    "));
		tempPanel2.add(sentButton);
		tempPanel2.add(new JLabel(""));

		centerLowerPanel.add(tempPanel1, BorderLayout.CENTER);
		centerLowerPanel.add(tempPanel2, BorderLayout.SOUTH);

		centerPanel.add(centerLowerPanel, BorderLayout.SOUTH);

		// ********************** 最下面的面板(South)*****************//
		JLabel BordBottomLabel = new JLabel();
		Icon BordBottom = new ImageIcon("images\\BordBottom.gif");
		BordBottomLabel.setIcon(BordBottom);

		// ******************组合整个框架**************************//
		//this.myMsgArea.setPreferredSize(new Dimension(50,100));
		//this.commonArea.setPreferredSize(new Dimension(50,100));
		//this.inMsgField.setPreferredSize(new Dimension(50,50));



		leftbox = Box.createVerticalBox();// 竖结构
		// leftbox = Box.createHorizontalStrut(30);
		leftbox.add(upperPanel, BorderLayout.NORTH);
		leftbox.add(centerPanel, BorderLayout.CENTER);
		leftbox.add(BordBottomLabel, BorderLayout.SOUTH);
		// leftbox.setMaximumSize(new Dimension(30,50));
		// leftbox.setMinimumSize(new Dimension(30,50));
		// rightbox.setMaximumSize(new Dimension(15,50));
		// rightbox.setMinimumSize(new Dimension(15,50));
		// setMaximumSize(15);
		this.add(leftbox, BorderLayout.CENTER);// ////////////?????????????
		this.add(rightbox, BorderLayout.EAST);// //????????????????
		//this.setResizable(false);

		sentButton.addActionListener(this);// 监听发送按钮

		sendfile.addActionListener(this);// 监听文件发送按钮//????????????????????
		acceptfile.addActionListener(this);// 监听文件接收按钮//?????/////////////////
		refusefile.addActionListener(this);// 监听文件拒绝按钮//?????/////////////////
		cancelsendfile.addActionListener(this);// 监听取消发送文件

		screenCapture.addActionListener(this);// 监听截屏命令

		perponsComboBox.addActionListener(this);// 监听下拉菜单////rfer

		// sendings.addActionListener(this);
		privateTalk.addItemListener(this);// 监听多选框状态
		this.createPopupMenu();
		// this.pack();
		inMsgField.requestFocus();
		this.setLocation(450, 50); // 居中
		this.setSize(600, 600);

		// when close the windows
		this.addWindowListener(new WindowAdapter()
		{ // 匿名类 监听窗口关闭时间

					public void windowClosing(WindowEvent event)
					{
						shutDown();
					}
				});

		this.setVisible(true);
	}

	public void createPopupMenu()
	{
		// Create the popup menu.
		JPopupMenu popup = new JPopupMenu();
		menuItem = new JMenuItem("清空群聊区信息");
		menuItem.addActionListener(this);
		popup.add(menuItem); // 鼠标右击显示
		cMenuItem = new JMenuItem("清空私聊区信息");
		cMenuItem.addActionListener(this);
		popup.add(cMenuItem);
		// Add listener to the text area so the popup menu can come up.
		MouseListener popupListener = new PopupListener(popup);// PopupListener继承MouseAdapter
		commonArea.addMouseListener(popupListener);
		myMsgArea.addMouseListener(popupListener);
	}

	public void init(BufferedReader in, PrintWriter out)
	{
		// 获得输入、输出流
		this.in = in;
		this.out = out;
		// 获得我自己的名字

		Thread th = new Thread(this);
		th.start();
	}

	public void run()
	{
		String inmsg;

		// sendthread = new sendfilethread();
		acceptthread = new acceptfilethread();
		// acceptfilesocket.close();
		while (true)
		{
			try
			{
				if ((inmsg = in.readLine()) != null)
				{

					// 已经在聊天室里的人显示到列表中
					if (inmsg.startsWith("old"))
					{
						String[] userInfo = inmsg.split("&");
						plist.listModel.addElement(userInfo[1] + " 〖"
								+ userInfo[2] + "〗"); // 更新用户列表
					} else if (inmsg.startsWith("new"))
					{ // 接收第一次服务器发送欢迎信息
						String[] userInfo = inmsg.split("&");
						plist.listModel.addElement(userInfo[1] + " 〖"
								+ userInfo[2] + "〗"); // 更新用户列表 new & zhangsan &
						// boy

						insert(commonArea, userInfo[1] + "上线了");

						insert(myMsgArea, userInfo[1] + "上线了");
						//myMsgArea.setCaretPosition(myMsgArea.getText().length(
						// ));
					} else if (inmsg != null)
					{ // 一般消息
						String[] sendfile = inmsg.split("&");
						if (sendfile[0].compareTo("cancelsendfile") == 0)
						{
							insert(myMsgArea, sendfile[1] + "取消了文件发送");
							acceptboolean = false;
							
							this.acceptfile.setEnabled(true);
							this.acceptfileArea.setText("");
							// this.acceptfileArea.s
						} else if (sendfile[0].compareTo("sendfile") == 0)
						{ // 如果是传送文件请求

							this.acceptfileArea.setText(sendfile[2]);
							insert(myMsgArea, sendfile[1] + " 发来文件");
							filemsg = inmsg;
						} else if (sendfile[0].compareTo("acceptfile") == 0)
						{ // 如果是传送文件请求
							// String[] acceptfile = inmsg.split("&");
							// acceptfileArea.setText(sendfile[2]);
							insert(myMsgArea, sendfile[1] + " 接收了你发的文件");

						} else if (sendfile[0].compareTo("refusefile") == 0)
						{ // 如果是传送文件请求

							insert(myMsgArea, sendfile[1] + " 拒绝了你发的文件");
							sendboolean = false;
							this.sendfile.setEnabled(true);
							this.sendfileArea.setText("");

						} else if (sendfile[0].compareTo("withWho") == 0)
						{
							if (sendfile[2].equals(myName))
							{ // 如果是发给自己的消息

								insert(myMsgArea, sendfile[1] + "对『"
										+ sendfile[2] + "』说:" + sendfile[3]);
							} // 显示到我的频道

							insert(commonArea, sendfile[1] + "对『" + sendfile[2]
									+ "』说:" + sendfile[3]);

						} else if (inmsg.startsWith("privateTalk"))
						{
							String showmsg[] = inmsg.split("&");
							if (showmsg[1].equals(myName))
							{// 如果接收到的是我自己发送的消息

								insert(commonArea, "您对『" + showmsg[2] + "』说: "
										+ showmsg[3]);
								//commonArea.setCaretPosition(commonArea.getText
								// ().length());
							} else
							{ // 接收到的是别人发给我的消息（悄悄话）

								insert(myMsgArea, "『" + showmsg[1] + "』对您说: "
										+ showmsg[3]);
							}
							// myMsgArea.setCaretPosition(myMsgArea.getText().
							// length());
						} else
						{

							insert(commonArea, inmsg);
						}
						//commonArea.setCaretPosition(commonArea.getText().length
						// ());
					}
				}
			} catch (Exception ee)
			{
				ee.printStackTrace();
				insert(myMsgArea, "与服务器中断，请重新登录！");
				// myMsgArea. setCaretPosition(myMsgArea.getText().length());
				// 将输出流，输入流设置为 null
				in = null;
				out = null;
				return;
			}
		}
	}

	// //////***********发送文件线程********************************//
	class sendfilethread extends Thread// ActionListenerkhhkh压郁
	{
		sendfilethread()// 构造函数
		{

		}

		public void run()
		{
			// try
			// {

			File file = new File(sendfilename);
			FileInputStream fos = null;

			try
			{
				fos = new FileInputStream(file);
			} catch (IOException e1)
			{
				System.out.print("发送文件打开文件异常");
			}
			// fos = new FileInputStream(file);

			out.println("sendfile" + "&" + withWho + "&"
					+ sendfileArea.getText() + "&" + IP + "&" + port + "&"
					+ (int) file.length() / 1000);
			out.flush();

			// 创建网络服务器接受客户请求
			try
			{
				fileserver = new ServerSocket(port);
			} catch (IOException e1)
			{
				System.out.print("发送文件创建服务器错误异常");
			}

			// fileserver = new ServerSocket(port);
			// sendfilesocket = fileserver.accept();
			try
			{
				sendfilesocket = fileserver.accept();
			} catch (IOException e1)
			{
				System.out.print("发送文件监听连接异常");
			}
			sendProgressbar.setMaximum((int) file.length() / 1000); // :
			// JProgressBar
			sendProgressbar.setMinimum(0);

			// 创建网络输出流并提供数据包装器
			int filetemp = 0;

			// OutputStream netOut = sendfilesocket.getOutputStream();
			// OutputStream doc = new DataOutputStream(new BufferedOutputStream(
			// netOut));
			OutputStream netOut = null;
			OutputStream doc = null;
			try
			{
				netOut = sendfilesocket.getOutputStream();
				doc = new DataOutputStream(new BufferedOutputStream(netOut));
			} catch (IOException e1)
			{
				System.out.print("发送文件创建网络输出流并提供数据包装器异常");
			}

			// 创建文件读取缓冲区

			// byte[] buf = new byte[20480];
			byte[] buf = new byte[8000000];
			int num = -1;
			try
			{
				num = fos.read(buf);// 读文件
			} catch (IOException e1)
			{
				System.out.print("发送文件读文件异常");
			}

			// int num = fos.read(buf);// 读文件

			while (num != (-1) && sendboolean)
			{// 是否读完文件
				filetemp = filetemp + num / 1000;
				sendProgressbar.setValue(filetemp);

				// doc.write(buf, 0, num);// 把文件数据写出网络缓冲区
				// doc.flush();// 刷新缓冲区把数据写往客户端
				try
				{
					doc.write(buf, 0, num);// 读文件
					doc.flush();
				} catch (IOException e1)
				{
					System.out.print("发送文件把文件数据写出网络缓冲区异常");
				}
				// num = fos.read(buf);// 继续从文件中读取数据
				try
				{
					num = fos.read(buf);// 继续从文件中读取数据
				} catch (IOException e1)
				{
					System.out.print("发送文件继续从文件中读取数据异常");
				}
			}
			if (num == (-1) && sendboolean)
			{
				insert(myMsgArea, "文件发送完毕");
			} else
			{
				insert(myMsgArea, "文件发送中断");
			}
			sendProgressbar.setValue(0);
			// fos.close();
			// doc.close();
			try
			{
				fos.close();
				doc.close();
			} catch (IOException e1)
			{
				System.out.print("发送文件关闭读或写异常");
			}
			// sendfilesocket.close();
			// fileserver.close();
			try
			{
				sendfilesocket.close();
				fileserver.close();
			} catch (IOException e1)
			{
				System.out.print("发送文件关闭连接或服务器异常");
			}
			sendfileArea.setText("");
			sendfile.setEnabled(true);

			// } catch (IOException e1)
			// {
			// System.out.print("发送异常");
			// }
			return;

		}
	}

	// //*****************************************************//////////////

	// //////////************接收文件线程********************////
	public class acceptfilethread extends Thread
	{
		private String ip, filepath;
		int port;

		public void ipport(String ipp, int portt, String filepathh)
		{
			ip = ipp;
			port = portt;
			filepath = filepathh;
		}

		acceptfilethread()
		{
		}

		public void run()
		{
			// try
			// {
			// File file = new File("D:\\b.rar");//可加弹出框
			File file = new File(filepath);// 可加弹出框
			// file.createNewFile();
			// RandomAccessFile raf = new RandomAccessFile(file, "rw");
			RandomAccessFile raf = null;
			try
			{
				file.createNewFile();
				raf = new RandomAccessFile(file, "rw");
			} catch (IOException e1)
			{
				System.out.print("接收文件新建文件并打开异常");
			}

			// 通过Socket连接文件服务器

			// /acceptfilesocket = new Socket(ip, port);

			try
			{
				acceptfilesocket = new Socket(ip, port);
			} catch (IOException e1)
			{
				System.out.print("接收文件通过Socket连接文件服务器异常");
			}

			String[] tem = filemsg.split("&");
			acceptProgressbar.setMaximum(Integer.parseInt(tem[5])); // :
			// JProgressBar
			acceptProgressbar.setMinimum(0);

			// 创建网络接受流接受服务器文件数据

			// InputStream netIn = acceptfilesocket.getInputStream();

			// InputStream in = new DataInputStream(new BufferedInputStream(
			// netIn));
			InputStream netIn = null;
			InputStream in = null;

			try
			{
				netIn = acceptfilesocket.getInputStream();

				in = new DataInputStream(new BufferedInputStream(netIn));
			} catch (IOException e1)
			{
				System.out.print("接收文件创建网络接受流接受服务器文件数据异常");
			}

			// 创建缓冲区缓冲网络数据

			byte[] buf = new byte[8000000];
			// timer.start();
			// int temleng =0;
			// int num = in.read(buf);
			int num = -1;

			try
			{
				num = in.read(buf);
			} catch (IOException e1)
			{
				System.out.print("接收文件创建缓冲区缓冲网络数据异常");
			}

			int temleng = num / 1000;

			while (num != (-1) && acceptboolean)
			{// 是否读完所有数据

				temleng = temleng + num / 1000;
				acceptProgressbar.setValue(temleng);

				// raf.write(buf, 0, num);// 将数据写往文件
				// raf.skipBytes(num);// 顺序写文件字节
				// num = in.read(buf);// 继续从网络中读取文件
				try
				{
					raf.write(buf, 0, num);// 将数据写往文件
					raf.skipBytes(num);// 顺序写文件字节
					num = in.read(buf);// 继续从网络中读取文件
				} catch (IOException e1)
				{
					System.out.print("接收文件将数据写往文件或继续从网络中读取文件异常");
				}
			}
			// if (num == (-1) && acceptboolean)
			if (acceptboolean)
			{
				insert(myMsgArea, "文件接收完毕");
			} else
			{
				insert(myMsgArea, "文件接收中断");
			}
			acceptProgressbar.setValue(0);
			// in.close();
			// raf.close();
			try
			{
				in.close();
				raf.close();
			} catch (IOException e1)
			{
				System.out.print("接收文件关闭读写失败异常");
			}
			// acceptfilesocket.close();
			try
			{
				acceptfilesocket.close();
			} catch (IOException e1)
			{
				System.out.print("接收文件关闭连接异常");
			}

			acceptfileArea.setText("");

			// } catch (IOException q)
			// {
			// System.out.println("文件接收异常");
			// }
			acceptfile.setEnabled(true);

			return;

		}
	}

	///////*********************************************************************

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == acceptfile)
		{// 接收文件传输按钮
			if (this.acceptfileArea.getText().length() == 0)
			{
				this.insert(myMsgArea, "没有文件需要接受");
			} else
			{
				String[] acpfile = filemsg.split("&");

				out.println("acceptfile" + "&" + acpfile[1]);
				out.flush();
				acceptthread = new acceptfilethread();
				acceptboolean = true;
				// String filepath = acpfile[2];
				String filepath = "D:\\a.rar";

				JFileChooser jfc = new JFileChooser();
				int result = jfc.showSaveDialog(this);
				File file = null;
				if (result == JFileChooser.APPROVE_OPTION)
				{
					file = jfc.getSelectedFile();
				}

				filepath = file.getPath();

				// 可加弹出框选择保存路径hhhuy
				acceptthread.ipport(acpfile[3], (Integer.parseInt(acpfile[4])),
						filepath);
				acceptfile.setEnabled(false);// 防止重复点接收
				acceptthread.start();// 
			}

		}
		if (e.getSource() == refusefile)
		{// 拒绝文件传输按钮
			if (this.acceptfileArea.getText().length() == 0)
			{
				this.insert(myMsgArea, "没有文件需要接收，拒绝无效");
			} else
			{
				String[] acpfile = filemsg.split("&");
				out.println("refusefile" + "&" + acpfile[1]);
				out.flush();

				this.insert(myMsgArea, "你取消了 " + acpfile[1] + " 发来的文件");
				this.acceptfileArea.setText("");
				acceptboolean = false;
				acceptfile.setEnabled(true);
			}

		}
		if (e.getSource() == sendfile)
		{// 发送文件按钮
			if (withWho.endsWith("所有人"))
			{
				this.insert(myMsgArea, "请选择要发送的目标");
			} else
			{
				sendfilename = "";
				if (this.sendfileArea.getText().length() != 0)
				{
					sendfilename = this.sendfileArea.getText();

				}
				if (sendfilename.length() == 0)// 没有获得需要发送的文件
				{
					this.insert(myMsgArea, "没有文件发送，请选择欲发送的文件或将其拖入待发送文件框");
				} else
				{ // 发送文件
					sendthread = new sendfilethread();
					sendboolean = true;
					sendfile.setEnabled(false);
					sendthread.start();

				}
			}
		}

		if (e.getSource() == cancelsendfile)
		{// 取消文件发送按钮
			if (this.sendfileArea.getText().length() == 0)
			{
				this.insert(myMsgArea, "没有文件发送，不需要取消");
			} else
			{
				
				this.insert(myMsgArea, "你取消了文件发送");
				this.sendfileArea.setText("");

				if (this.sendboolean)
				{
					out.println("cancelsendfile" + "&" + withWho);
					out.flush();
					sendboolean = false;
				    sendfile.setEnabled(true);
				}

			}

		}

		// /////////////////////////////////////

		if (e.getSource() == sentButton)
		{// 如果监听到发送信息按钮被点击
			try
			{
				mywords = inMsgField.getText();
				if ((mywords.trim()).length() != 0)
				{ // 不能发送空消息也不能都发空格
					if (withWho.equals("所有人"))
					{
						outmsg = mywords;
						// 发送到服务器
						out.println(outmsg);
						out.flush();

						// 显示到我的频道里面
						insert(myMsgArea, myName + "：" + mywords);

						//myMsgArea.setCaretPosition(myMsgArea.getText().length(
						// ));
					} else
					{ // 对某个人交谈
						outmsg = "withWho" + "&" + "privateFalse" + "&"
								+ withWho + "&" + mywords;
						if (privateTalkFlag)
						{
							outmsg = "withWho" + "&" + "privateTure" + "&"
									+ withWho + "&" + mywords;
							insert(myMsgArea, "您对『" + withWho + "』说: "
									+ mywords);

							// myMsgArea.setCaretPosition(myMsgArea.getText().
							// length());
						} else
						{
							insert(myMsgArea, myName + " 对『" + withWho + "』说: "
									+ mywords);

						}
						//myMsgArea.setCaretPosition(myMsgArea.getText().length(
						// ));
						// 发送到服务器
						out.println(outmsg);
						out.flush();
					}
				}
			} catch (Exception ee)
			{
				ee.printStackTrace();
				insert(myMsgArea, "与服务器连接中断，请重新登录！");
				// myMsgArea.setCaretPosition(myMsgArea.getText().length());
			} finally
			{
				inMsgField.setText(""); // 清空输入框
			}
		}

		if (e.getSource() == perponsComboBox)
		{// 如果监听到下拉菜单信息
			withWho = (String) perponsComboBox.getSelectedItem(); // 获得选择的名称

		}

		if (e.getSource() == menuItem)
		{// 如果监听到右击清空主聊天频道被选中
			commonArea.setText("");
		}
		if (e.getSource() == cMenuItem)
		{// 如果监听到右击清空私聊天频道被选中
			myMsgArea.setText("");
		}

		if (e.getSource() == screenCapture)
		{
			new CaptureScreen();
		}
	}

	public void itemStateChanged(ItemEvent e)
	{
		if (e.getSource() == privateTalk)
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
			{ // 如果选中
				privateTalkFlag = true;
			} else
			{
				privateTalkFlag = false;
			}
		}
	}

	public void shutDown()
	{
		try
		{
			out.println("quit");
			out.flush();
		} catch (Exception ee)
		{
			// JOptionPane.showMessageDialog(this, ee, "错误",
			// JOptionPane.ERROR_MESSAGE);
		} finally
		{
			// this.dispose();
			System.exit(0);
		}
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == 10)
		{ // 监听到回车键则输出信息
			try
			{
				mywords = inMsgField.getText();
				if ((mywords.trim()).length() != 0)
				{ // 不能发送空消息也不能都发空格
					if (withWho.equals("所有人"))
					{
						outmsg = mywords;
						// 发送到服务器
						out.println(outmsg);
						out.flush();
						// 显示到我的频道里面
						insert(myMsgArea, myName + "：" + mywords);
						// myMsgArea.append(myName+"："+mywords+"\n");
						//myMsgArea.setCaretPosition(myMsgArea.getText().length(
						// ));
					} else
					{ // 对某个人交谈
						outmsg = "withWho" + "&" + "privateFalse" + "&"
								+ withWho + "&" + mywords;
						if (privateTalkFlag)
						{
							outmsg = "withWho" + "&" + "privateTure" + "&"
									+ withWho + "&" + mywords;
							insert(myMsgArea, "您对『" + withWho + "』说: "
									+ mywords);
							//myMsgArea.append("您对『"+withWho+"』说: "+mywords+"\n"
							// );
							// myMsgArea.setCaretPosition(myMsgArea.getText().
							// length());
						} else
						{
							insert(myMsgArea, myName + " 对『" + withWho + "』说: "
									+ mywords);
							// myMsgArea.append(myName+" 对『"+withWho+"』说: "+
							// mywords+"\n");
						}
						//myMsgArea.setCaretPosition(myMsgArea.getText().length(
						// ));
						// 发送到服务器
						out.println(outmsg);
						out.flush();
					}
				}
			} catch (Exception ee)
			{
				System.out.println(ee);
				// myMsgArea.append("与服务器连接中断,请重新登录！\n");
				insert(myMsgArea, "与服务器连接中断,请重新登录！");

				// myMsgArea.setCaretPosition(myMsgArea.getText().length());
			} finally
			{
				inMsgField.setText("");// 清空输入框
			}
		}
	}

	// 作为非抽象类，必须“重写”下面这些抽象方法
	public void keyReleased(KeyEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{
	}

	// *********************设置阶段**************************//

	/**
	 * 将文本插入JTextPane
	 * 
	 * @param attrib
	 */
	private void insert(JTextPane j, String words)
	{
		// Date noeTime = new Date();
		// SimpleDateFormat matter = new SimpleDateFormat("HH:mm:ss ");
		int y, mi, d, h, m, s;
		Calendar cal = Calendar.getInstance();
		y = cal.get(Calendar.YEAR);
		mi = cal.get(Calendar.MONTH);
		d = cal.get(Calendar.DATE);
		h = cal.get(Calendar.HOUR_OF_DAY);
		m = cal.get(Calendar.MINUTE);
		s = cal.get(Calendar.SECOND);

		doc = j.getStyledDocument();
		try
		{ // 插入文本
			doc.insertString(doc.getLength(), h + ":" + m + ":" + s + " "
					+ words + "\n", getFontAttrib().getAttrSet());
			this.inMsgField.setText("");
			
		} catch (BadLocationException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 获取所需要的文字设置
	 * 
	 * @return FontAttrib
	 */
	private FontAttrib getFontAttrib()
	{
		FontAttrib att = new FontAttrib();
		att.setText(inMsgField.getText());
		att.setName((String) fontName.getSelectedItem());
		att.setSize(Integer.parseInt((String) fontSize.getSelectedItem()));
		String temp_style = (String) fontStyle.getSelectedItem();
		if (temp_style.equals("常规"))
		{
			att.setStyle(FontAttrib.GENERAL);
		} else if (temp_style.equals("粗体"))
		{
			att.setStyle(FontAttrib.BOLD);
		} else if (temp_style.equals("斜体"))
		{
			att.setStyle(FontAttrib.ITALIC);
		} else if (temp_style.equals("粗斜体"))
		{
			att.setStyle(FontAttrib.BOLD_ITALIC);
		}
		String temp_color = (String) fontColor.getSelectedItem();
		if (temp_color.equals("黑色"))
		{
			att.setColor(new Color(0, 0, 0));
		} else if (temp_color.equals("红色"))
		{
			att.setColor(new Color(255, 0, 0));
		} else if (temp_color.equals("蓝色"))
		{
			att.setColor(new Color(0, 0, 255));
		} else if (temp_color.equals("黄色"))
		{
			att.setColor(new Color(255, 255, 0));
		} else if (temp_color.equals("绿色"))
		{
			att.setColor(new Color(0, 255, 0));
		}

		return att;
	}

	/**
	 * 字体的属性类
	 */
	private class FontAttrib
	{
		public static final int GENERAL = 0; // 常规

		public static final int BOLD = 1; // 粗体

		public static final int ITALIC = 2; // 斜体

		public static final int BOLD_ITALIC = 3; // 粗斜体

		private SimpleAttributeSet attrSet = null; // 属性集

		private String text = null, name = null; // 要输入的文本和字体名称

		private int style = 0, size = 0; // 样式和字号

		private Color color = null, backColor = null; // 文字颜色和背景颜色

		/**
		 * 一个空的构造（可当做换行使用）
		 */
		public FontAttrib()
		{
		}

		/**
		 * 返回属性集
		 * 
		 * @return
		 */
		public SimpleAttributeSet getAttrSet()
		{
			attrSet = new SimpleAttributeSet();
			if (name != null)
				StyleConstants.setFontFamily(attrSet, name);
			if (style == FontAttrib.GENERAL)
			{
				StyleConstants.setBold(attrSet, false);
				StyleConstants.setItalic(attrSet, false);
			} else if (style == FontAttrib.BOLD)
			{
				StyleConstants.setBold(attrSet, true);
				StyleConstants.setItalic(attrSet, false);
			} else if (style == FontAttrib.ITALIC)
			{
				StyleConstants.setBold(attrSet, false);
				StyleConstants.setItalic(attrSet, true);
			} else if (style == FontAttrib.BOLD_ITALIC)
			{
				StyleConstants.setBold(attrSet, true);
				StyleConstants.setItalic(attrSet, true);
			}
			StyleConstants.setFontSize(attrSet, size);
			if (color != null)
				StyleConstants.setForeground(attrSet, color);
			if (backColor != null)
				StyleConstants.setBackground(attrSet, backColor);
			return attrSet;
		}

		/**
		 * 设置属性集
		 * 
		 * @param attrSet
		 */
		public void setAttrSet(SimpleAttributeSet attrSet)
		{
			this.attrSet = attrSet;
		}

		public String getText()
		{
			return text;
		}

		public void setText(String text)
		{
			this.text = text;
		}

		public Color getColor()
		{
			return color;
		}

		public void setColor(Color color)
		{
			this.color = color;
		}

		public Color getBackColor()
		{
			return backColor;
		}

		public void setBackColor(Color backColor)
		{
			this.backColor = backColor;
		}

		public String getName()
		{
			return name;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public int getSize()
		{
			return size;
		}

		public void setSize(int size)
		{
			this.size = size;
		}

		public int getStyle()
		{
			return style;
		}

		public void setStyle(int style)
		{
			this.style = style;
		}
	}

	public static void main(String[] args)
	{
		// new sendfilethread();
		// ChatFrame a =
		new ChatFrame("计科0704");
	}

}
