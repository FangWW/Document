package chat;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.net.*;
import java.io.*;

class Connect extends JFrame implements ActionListener, ItemListener,
		KeyListener
{
	/**
	 * 用户登录界面p
	 */
	private static final long serialVersionUID = 1L;
	private Socket client;// 套接字
	private String hostName = "localhost";
	private int port = 6000;
	private JLabel nameLabel;
	private JTextField nameText;
	private JRadioButton boyRadio, girlRadio, secretRadio;
	private String sex = ""; // 标示用户性别信息
	private JLabel hostLabel;
	private JLabel portLabel;
	private JTextField hostText;
	private JTextField portText;
	private JButton cancell;
	private JButton ok;
	private Container c;
	private BufferedReader in;
	private PrintWriter out;

	public Connect()
	{ // 构造器

		super("计科0704聊天登陆");

		try
		{ // 使用Windows的界面风格
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		c = this.getContentPane();
		c.setLayout(new BorderLayout());

		hostLabel = new JLabel("地址: ");
		portLabel = new JLabel("端口: ");
		hostText = new JTextField(10);
		hostText.setText(hostName); // 设置默认值
		portText = new JTextField(10);
		portText.setText(Integer.toString(port));
		cancell = new JButton("退出");
		ok = new JButton("登陆");
		nameLabel = new JLabel("姓名：");
		nameText = new JTextField(17);
		boyRadio = new JRadioButton("男生");
		girlRadio = new JRadioButton("女生");
		secretRadio = new JRadioButton("保密");
		ButtonGroup sexGroup = new ButtonGroup();
		sexGroup.add(boyRadio);
		sexGroup.add(girlRadio);
		sexGroup.add(secretRadio);

		// *********用户信息面板*********************//
		JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		userInfoPanel.add(nameLabel);
		userInfoPanel.add(nameText);

		// **********图片***************************//
		JPanel pic = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel picc = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel pi = new JPanel(new GridLayout(2, 1));
		// JLabel title = new JLabel();
		// Icon tit = new ImageIcon("images/load.jpg");
		// title.setIcon(tit);
		pic.add(hostLabel);
		pic.add(hostText);
		picc.add(portLabel);
		picc.add(portText);

		pi.add(pic);
		pi.add(picc);

		// ****************连接服务器面板******************//
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// centerPanel.add(hostLabel);
		// centerPanel.add(hostText);
		// centerPanel.add(portLabel);
		// centerPanel.add(portText);
		centerPanel.add(boyRadio);
		centerPanel.add(girlRadio);
		centerPanel.add(secretRadio);

		// *******************组合以上三个面板*****************//
		JPanel ul = new JPanel(new GridLayout(2, 1)); // 面板
		ul.setBackground(new Color(70, 61, 129));
		ul.add(userInfoPanel);
		ul.add(centerPanel);

		JPanel upperPanel = new JPanel(new GridLayout(1, 2));
		upperPanel.setBorder(BorderFactory.createLineBorder(new Color(125, 161,
				253), 2));// 设定颜色

		upperPanel.add(ul);
		upperPanel.add(pi);
		// upperPanel.setBorder(new LineBorder(Color.blue,2));//另一种设置边框的方法
		//upperPanel.setBorder(BorderFactory.createEtchedBorder(Color.red,Color.
		// yellow));
		// 创造一个浮雕的边框设置高亮和阴影的颜色

		// ****************连接确认连接的面板******************//
		JPanel lowerPanel = new JPanel();
		JLabel spaceLabel = new JLabel("");
		spaceLabel.setPreferredSize(new Dimension(20, 20));
		lowerPanel.add(ok);
		lowerPanel.add(spaceLabel);
		lowerPanel.add(cancell);
		// ***************组合整个框架**********************//
		c.add(upperPanel, BorderLayout.NORTH);
		c.add(lowerPanel, BorderLayout.SOUTH);

		// 事件监听 单选
		boyRadio.addItemListener(this);
		girlRadio.addItemListener(this);
		secretRadio.addItemListener(this);
		boyRadio.addKeyListener(this);
		girlRadio.addKeyListener(this);
		secretRadio.addKeyListener(this);
		hostText.addKeyListener(this);
		portText.addKeyListener(this);
		nameText.addKeyListener(this);
		// 事件监听 按钮
		cancell.addActionListener(this);
		ok.addActionListener(this);

		this.setPreferredSize(new Dimension(350, 150));// 默认大小
		this.setMaximumSize(new Dimension(350, 150));// 最大
		this.setLocationRelativeTo(null);
		this.pack(); // 设置窗体的大小最适合里面内容
		this.setResizable(false); // 设置不能最大化
		this.setVisible(true);// 设置可不可见
	}

	// 事件 单选 在用户已选定或取消选定某项时调用。
	public void itemStateChanged(ItemEvent e)
	{
		if (e.getSource() == boyRadio)
		{
			sex = "Boy";
		}
		if (e.getSource() == girlRadio)
		{
			sex = "Girl";
		}
		if (e.getSource() == secretRadio)
		{
			sex = "Secret";
		}
	}

	// 事件 按钮
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == cancell)
		{
			this.shutDown();
		} else if (e.getSource() == ok)
		{
			if ((nameText.getText()).trim().length() == 0)
			{
				// 有助于方便地弹出要求用户提供值或向其发出通知的标准对话框。
				JOptionPane.showMessageDialog(this, "请输入一个名字", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if (sex.length() == 0)
			{
				JOptionPane.showMessageDialog(this, "请选择性别", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else
			{
				try
				{
					this.link();
					ChatFrame app = new ChatFrame(nameText.getText());
					//app.ct= null;//new Thread(app.sendfilethread);
					app.init(in, out);
					ok.setEnabled(false); // 确保不会被再次点击
					// this.setVisible(false);
					this.dispose();

				} catch (Exception ee)
				{
					JOptionPane.showMessageDialog(this, "登陆失败", "失败",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
	}

	public void link() throws Exception
	{ // 连接服务器
		hostName = hostText.getText().trim();
		port = Integer.parseInt(portText.getText());
		client = new Socket(hostName, port);// 创建一个流套接字并将其连接到指定 IP 地址的指定端口号。

		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream());
		out.println(nameText.getText() + "&" + sex);
		out.flush();
	}

	/**
	 * 关闭窗口
	 */
	public void shutDown()
	{
		System.exit(0);
	}

	public static void main(String[] args)
	{
		Connect app = new Connect();
		//app.

		app.addWindowListener( // 内部类
				new WindowAdapter()
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				});
	}

	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == 10)
		{
			if ((nameText.getText()).trim().length() == 0)
			{
				// 有助于方便地弹出要求用户提供值或向其发出通知的标准对话框。
				JOptionPane.showMessageDialog(this, "请输入一个名字", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if (sex.length() == 0)
			{
				JOptionPane.showMessageDialog(this, "请选择性别", "提示",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else
			{
				try
				{
					this.link();
					ChatFrame app = new ChatFrame(nameText.getText());
					app.init(in, out);
					ok.setEnabled(false); // 确保不会被再次点击
					// this.setVisible(false);
					this.dispose();
				} catch (Exception ee)
				{
					JOptionPane.showMessageDialog(this, "登陆失败", "失败",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{
	}
}//219.242.119.82
