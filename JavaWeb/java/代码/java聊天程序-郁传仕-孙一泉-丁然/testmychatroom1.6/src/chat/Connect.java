package chat;

class Connect extends JFrame implements ActionListener, ItemListener,
		KeyListener
{
	/**
	 * �û���¼����p
	 */
	private static final long serialVersionUID = 1L;
	private Socket client;// �׽���
	private String hostName = "localhost";
	private int port = 6000;
	private JLabel nameLabel;
	private JTextField nameText;
	private JRadioButton boyRadio, girlRadio, secretRadio;
	private String sex = ""; // ��ʾ�û��Ա���Ϣ
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
	{ // ������

		super("�ƿ�0704�����½");

		try
		{ // ʹ��Windows�Ľ�����
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		c = this.getContentPane();
		c.setLayout(new BorderLayout());

		hostLabel = new JLabel("��ַ: ");
		portLabel = new JLabel("�˿�: ");
		hostText = new JTextField(10);
		hostText.setText(hostName); // ����Ĭ��ֵ
		portText = new JTextField(10);
		portText.setText(Integer.toString(port));
		cancell = new JButton("�˳�");
		ok = new JButton("��½");
		nameLabel = new JLabel("������");
		nameText = new JTextField(17);
		boyRadio = new JRadioButton("����");
		girlRadio = new JRadioButton("Ů��");
		secretRadio = new JRadioButton("����");
		ButtonGroup sexGroup = new ButtonGroup();
		sexGroup.add(boyRadio);
		sexGroup.add(girlRadio);
		sexGroup.add(secretRadio);

		// *********�û���Ϣ���*********************//
		JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		userInfoPanel.add(nameLabel);
		userInfoPanel.add(nameText);

		// **********ͼƬ***************************//
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

		// ****************���ӷ��������******************//
		JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// centerPanel.add(hostLabel);
		// centerPanel.add(hostText);
		// centerPanel.add(portLabel);
		// centerPanel.add(portText);
		centerPanel.add(boyRadio);
		centerPanel.add(girlRadio);
		centerPanel.add(secretRadio);

		// *******************��������������*****************//
		JPanel ul = new JPanel(new GridLayout(2, 1)); // ���
		ul.setBackground(new Color(70, 61, 129));
		ul.add(userInfoPanel);
		ul.add(centerPanel);

		JPanel upperPanel = new JPanel(new GridLayout(1, 2));
		upperPanel.setBorder(BorderFactory.createLineBorder(new Color(125, 161,
				253), 2));// �趨��ɫ

		upperPanel.add(ul);
		upperPanel.add(pi);
		// upperPanel.setBorder(new LineBorder(Color.blue,2));//��һ�����ñ߿�ķ���
		//upperPanel.setBorder(BorderFactory.createEtchedBorder(Color.red,Color.
		// yellow));
		// ����һ������ı߿����ø�������Ӱ����ɫ

		// ****************����ȷ�����ӵ����******************//
		JPanel lowerPanel = new JPanel();
		JLabel spaceLabel = new JLabel("");
		spaceLabel.setPreferredSize(new Dimension(20, 20));
		lowerPanel.add(ok);
		lowerPanel.add(spaceLabel);
		lowerPanel.add(cancell);
		// ***************����������**********************//
		c.add(upperPanel, BorderLayout.NORTH);
		c.add(lowerPanel, BorderLayout.SOUTH);

		// �¼����� ��ѡ
		boyRadio.addItemListener(this);
		girlRadio.addItemListener(this);
		secretRadio.addItemListener(this);
		boyRadio.addKeyListener(this);
		girlRadio.addKeyListener(this);
		secretRadio.addKeyListener(this);
		hostText.addKeyListener(this);
		portText.addKeyListener(this);
		nameText.addKeyListener(this);
		// �¼����� ��ť
		cancell.addActionListener(this);
		ok.addActionListener(this);

		this.setPreferredSize(new Dimension(350, 150));// Ĭ�ϴ�С
		this.setMaximumSize(new Dimension(350, 150));// ���
		this.setLocationRelativeTo(null);
		this.pack(); // ���ô���Ĵ�С���ʺ���������
		this.setResizable(false); // ���ò������
		this.setVisible(true);// ���ÿɲ��ɼ�
	}

	// �¼� ��ѡ ���û���ѡ����ȡ��ѡ��ĳ��ʱ���á�
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

	// �¼� ��ť
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == cancell)
		{
			this.shutDown();
		} else if (e.getSource() == ok)
		{
			if ((nameText.getText()).trim().length() == 0)
			{
				// �����ڷ���ص���Ҫ���û��ṩֵ�����䷢��֪ͨ�ı�׼�Ի���
				JOptionPane.showMessageDialog(this, "������һ������", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if (sex.length() == 0)
			{
				JOptionPane.showMessageDialog(this, "��ѡ���Ա�", "��ʾ",
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
					ok.setEnabled(false); // ȷ�����ᱻ�ٴε��
					// this.setVisible(false);
					this.dispose();

				} catch (Exception ee)
				{
					JOptionPane.showMessageDialog(this, "��½ʧ��", "ʧ��",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		}
	}

	public void link() throws Exception
	{ // ���ӷ�����
		hostName = hostText.getText().trim();
		port = Integer.parseInt(portText.getText());
		client = new Socket(hostName, port);// ����һ�����׽��ֲ��������ӵ�ָ�� IP ��ַ��ָ���˿ںš�

		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new PrintWriter(client.getOutputStream());
		out.println(nameText.getText() + "&" + sex);
		out.flush();
	}

	/**
	 * �رմ���
	 */
	public void shutDown()
	{
		System.exit(0);
	}

	public static void main(String[] args)
	{
		Connect app = new Connect();
		//app.

		app.addWindowListener( // �ڲ���
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
				// �����ڷ���ص���Ҫ���û��ṩֵ�����䷢��֪ͨ�ı�׼�Ի���
				JOptionPane.showMessageDialog(this, "������һ������", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else if (sex.length() == 0)
			{
				JOptionPane.showMessageDialog(this, "��ѡ���Ա�", "��ʾ",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			} else
			{
				try
				{
					this.link();
					ChatFrame app = new ChatFrame(nameText.getText());
					app.init(in, out);
					ok.setEnabled(false); // ȷ�����ᱻ�ٴε��
					// this.setVisible(false);
					this.dispose();
				} catch (Exception ee)
				{
					JOptionPane.showMessageDialog(this, "��½ʧ��", "ʧ��",
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
