import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.io.File;

class SendFile implements Serializable {
	String chatUser;
	String chatToUser;
	File file1;
	boolean whisper;
}


class Exit1 implements Serializable {
	String exitname;
}


public class ChatRoom extends Thread implements ActionListener
{

	static JFrame frmChat;
	JPanel pnlChat, pnlChoice;
	File file;
	JButton btnCls, btnExit, btnSend, btnSave, btnSendFile,
			btnBrowse;
	JLabel lblChatIag, lblUserList, lblUserMessage, lblSendMessage,
			lblChatUser, lblnumber, lbluser, lblpicture, lblname, lblTochat;
	JLabel lblUserTotal, lblCount, lblBack, lblFile;
	JTextField txtMessage, txtFile,txtTouser;
	java.awt.List lstUserList;
	TextArea taUserMessage; // 信息文本区
    JComboBox cmbUser; // 列表选择
	JRadioButton  chPrivateChat,chPublicChat,PublicChat;
	ButtonGroup a,b;
	String strServerIp, strLoginName; // strLoginName作为本窗口的用户
	Thread thread;
	JMenuBar mbChat;         // 菜单条
	JMenu mnuSystem, mnuHelp;// 菜单
	JMenuItem mnuiCls, mnuiSave, mnuiClock, mnuiExit, mnuiContent,mnuiAbout;
	Introduction dailog11;
	Introductions dailogs;
	// 用于将窗口用于定位
	Dimension scrnsize;
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	byte butter[];      //接受文本文件处理

	// 构造方法
	public ChatRoom(String name, String ip, String picturec) {
		strServerIp = ip;
		strLoginName = name;

		frmChat = new JFrame("姜曙光聊天室" + "[用户:" + name + "]");
		pnlChat = new JPanel();
		dailog11 =new Introduction();
		dailogs=new Introductions();
		
		//窗体关闭时默认执行的操作
		frmChat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChat.getContentPane().add(pnlChat);

		Font fntDisp1 = new Font("宋体", Font.PLAIN, 12);
		// Font fntDisp2=new Font("宋体",Font.PLAIN,11);

		mbChat = new JMenuBar();
		mnuSystem = new JMenu("系统(S)");
		mnuSystem.setMnemonic(KeyEvent.VK_S);
		mnuSystem.setFont(fntDisp1);

		mnuHelp = new JMenu("帮助(H)");
		mnuHelp.setMnemonic(KeyEvent.VK_H);
		mnuHelp.setFont(fntDisp1);

		mbChat.add(mnuSystem);
		mbChat.add(mnuHelp);

		// 系统菜单
		mnuiCls = new JMenuItem("清除屏幕");
		mnuiCls.setFont(fntDisp1);
		mnuiSave = new JMenuItem("保存聊天记录");
		mnuiSave.setFont(fntDisp1);
		// mnuiClock=new JMenuItem("查看时间");
		// mnuiClock.setFont(fntDisp1);
		mnuiExit = new JMenuItem("退出系统");
		mnuiExit.setFont(fntDisp1);
		mnuSystem.add(mnuiCls);
		mnuSystem.add(mnuiSave);
		// mnuSystem.add(mnuiClock);
		mnuSystem.add(mnuiExit);
		mnuiCls.addActionListener(this);
		mnuiSave.addActionListener(this);
		mnuiExit.addActionListener(this);

		// 帮助菜单
		mnuiContent = new JMenuItem("目录");
		mnuiContent.setFont(fntDisp1);
		mnuiAbout = new JMenuItem("姜曙光聊天系统简介");
		mnuiAbout.setFont(fntDisp1);
		mnuHelp.add(mnuiContent);
		mnuHelp.add(mnuiAbout);
		mnuiContent.addActionListener(this);
		mnuiAbout.addActionListener(this);
		

		// ////////////////////// 菜单栏/////////////////
		frmChat.setJMenuBar(mbChat);
		String list[] = { "所有人" };
		btnSendFile=new JButton("发送文件");
		btnCls = new JButton("音乐");
		btnExit = new JButton("退出");
		btnSend = new JButton("发送(N)");
		btnSave = new JButton("保存");
		btnBrowse = new JButton("浏览...");
		lblUserList = new JLabel("【在线用户列表】");

		lblname = new JLabel("用户:【" + strLoginName + "】");
		// lblpicture=new JLabel();

		lblUserMessage = new JLabel("【聊天信息】");
		lblSendMessage = new JLabel("聊天内容:");
		lblChatUser = new JLabel("你正在和:");

		lblUserTotal = new JLabel("在线人数:");
		lblCount = new JLabel("0");

		lblFile = new JLabel("文本文件:");
		txtFile = new JTextField(20);
		lstUserList = new java.awt.List();
		txtMessage = new JTextField(170);
		cmbUser = new JComboBox(list);
		lblTochat = new JLabel(" 所有人");
		txtTouser = new JTextField("所有人");
		PublicChat=new JRadioButton("群聊",true);
		chPrivateChat = new JRadioButton("私聊");
		chPublicChat = new JRadioButton("公聊");
		a=new ButtonGroup();
		a.add(chPublicChat);
		a.add(PublicChat);
		a.add(chPrivateChat);
		
	
		taUserMessage = new TextArea("", 300, 200,
				TextArea.SCROLLBARS_VERTICAL_ONLY);// 只能向下滚动
		taUserMessage.setEditable(false); // 不可写入

		pnlChat.setLayout(null);           //手动布局
		pnlChat.setBackground(new Color(255, 130, 203));
		btnSendFile.setBounds(470, 465, 100, 25);
		btnBrowse.setBounds(400, 465, 80, 25);
		btnSend.setBounds(500, 410, 80, 25);

		lblFile.setBounds(210, 440, 80, 25);
		lblUserList.setBounds(5, 50, 120, 40);
		lblUserTotal.setBounds(130, 50, 60, 40);
		lblCount.setBounds(190, 0, 110, 40);
		lblUserMessage.setBounds(225, 100, 180, 40);
		lblChatUser.setBounds(160, 340, 110, 40);
		lblSendMessage.setBounds(160, 380, 60, 40);

		// 用户图像的显示
		lblname.setBounds(430, 50, 100, 40);

		lblUserTotal.setBounds(25, 390, 80, 40);// 在线用户人数的显示
		lblCount.setBounds(110, 390, 100, 40);

		txtFile.setBounds(275, 440, 300, 25);
		lstUserList.setBounds(25, 90, 120, 305);
		taUserMessage.setBounds(165, 90, 400, 255);
		txtMessage.setBounds(220, 385, 360, 25);
		// cmbUser.setBounds(220,350,80,25);
		lblTochat.setBounds(220, 350, 80, 25);
		txtTouser.setBounds(220, 350, 80, 25);
		chPrivateChat.setBounds(335, 352, 60, 20);
		chPublicChat.setBounds(400, 352, 60, 20);
		PublicChat.setBounds(465, 352, 60, 20);

		btnSendFile.setFont(fntDisp1);
		btnBrowse.setFont(fntDisp1);
		btnCls.setFont(fntDisp1);
		btnExit.setFont(fntDisp1);
		btnSend.setFont(fntDisp1);
		btnSave.setFont(fntDisp1);
		lblFile.setFont(fntDisp1);
		lblUserList.setFont(fntDisp1);
		lblUserMessage.setFont(fntDisp1);
		lblChatUser.setFont(fntDisp1);
		lblSendMessage.setFont(fntDisp1);
		// lblUserTotal.setFont(fntDisp1);
		// lblCount.setFont(fntDisp1);
		txtFile.setFont(fntDisp1);
		cmbUser.setFont(fntDisp1);

		chPrivateChat.setFont(fntDisp1);
		chPublicChat.setFont(fntDisp1);
		taUserMessage.setFont(new Font("宋体", Font.PLAIN, 12));

		lblUserList.setForeground(Color.YELLOW);
		lblUserMessage.setForeground(Color.YELLOW);
		lblSendMessage.setForeground(Color.red);
		lblChatUser.setForeground(Color.red);
		lblSendMessage.setForeground(Color.black);
		lblUserTotal.setForeground(Color.red);
		lblCount.setForeground(Color.red);
		cmbUser.setForeground(Color.black);
		chPrivateChat.setForeground(Color.black);
		chPublicChat.setForeground(Color.black);
		lstUserList.setBackground(Color.white);
		taUserMessage.setBackground(Color.white);
		btnBrowse.setBackground(Color.PINK);
		lblTochat.setBackground(Color.PINK);
		lblTochat.setForeground(Color.GREEN);
		btnSendFile.setBackground(Color.PINK);
		btnCls.setBackground(Color.ORANGE);
		btnExit.setBackground(Color.ORANGE);
		btnSend.setBackground(Color.PINK);
		btnSave.setBackground(Color.ORANGE);
//		cmbmusic.setForeground(Color.RED);
//		cmbmusic.setBackground(Color.yellow);
		chPrivateChat.setOpaque(false);   
		chPublicChat.setOpaque(false);
		PublicChat.setOpaque(false);

		pnlChat.add(lblFile);
		pnlChat.add(txtFile);
		pnlChat.add(btnSendFile);
		pnlChat.add(btnBrowse);
		pnlChat.add(lblname);
		pnlChat.add(btnSend);
		// pnlChat.add(btnSave);
		// pnlChat.add(lblUserList);
		pnlChat.add(lblUserMessage);
		pnlChat.add(lblSendMessage);
		pnlChat.add(lblChatUser);
		pnlChat.add(lblUserTotal);
		pnlChat.add(lblCount);
		pnlChat.add(lstUserList);
		pnlChat.add(taUserMessage);
		pnlChat.add(txtMessage);
		pnlChat.add(txtTouser);
		pnlChat.add(chPrivateChat);
		pnlChat.add(chPublicChat);
		pnlChat.add(PublicChat);

		// /////////////附加功能面板/////////////////
		pnlChoice = new JPanel(new GridLayout(1, 5)); // GridLayout(14,1) 布局
		// 14行 1列
		pnlChoice.setBackground(new Color(255, 130, 203));
		pnlChoice.setFont(new Font("宋体", 0, 12));
		pnlChoice.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder(""), BorderFactory.createEmptyBorder(1, 1,
				1, 1)));

		pnlChoice.setBounds(25, 475, 350, 35);
		pnlChat.add(pnlChoice);
		// pnlChoice.add(cmbmusic);
		pnlChoice.add(btnSave);
		pnlChoice.add(btnCls);
		pnlChoice.add(btnExit);

		frmChat.addWindowListener(new Windowclose());

		btnSendFile.addActionListener(this);
		btnBrowse.addActionListener(this);
		btnCls.addActionListener(this);
		btnExit.addActionListener(this);
		btnSend.addActionListener(this);
		btnSave.addActionListener(this);
		lstUserList.addActionListener(this);
		txtMessage.addActionListener(this);
		PublicChat.addActionListener(this);
		chPrivateChat.addActionListener(this);
		chPublicChat.addActionListener(this);
//		cmbmusic.addActionListener(this);

		// 启动聊天页面信息刷新线程
		Thread thread = new Thread(this);
		thread.start();

		frmChat.setSize(605, 560);// 600,440
		frmChat.setVisible(true);
		frmChat.setResizable(false);

		// 将窗口定位在屏幕中央
		scrnsize = toolkit.getScreenSize();
		frmChat.setLocation(scrnsize.width / 2 - frmChat.getWidth() / 2,
				scrnsize.height / 2 - frmChat.getHeight() / 2);

		// Image img=toolkit.getImage("chat3.jpg");
		// frmChat.setIconImage(img);

		// 用户头像
		Icon log;
		log = new ImageIcon("images\\"+"touxiang"+picturec+".jpg");
		lblpicture = new JLabel(log);
		lblpicture.setBounds(525, 50, 40, 40);
		pnlChat.add(lblpicture);

		Icon logo1 = new ImageIcon("images\\chat12.jpg");
		lblChatIag = new JLabel(logo1);
		lblChatIag.setBounds(0, -5, 600, 520);
		pnlChat.add(lblChatIag);

	} // 构造方法结束

	public void run() {
		int intMessageCounter = 0; // 聊天记录的条数(包括聊天内容和聊天对象)
		int intUserTotal = 0; // 登陆的用户数
		boolean isFirstLogin = true; // 判断是否刚登陆
		boolean isFound; // 判断是否找到用户
		Vector user_exit = new Vector();

		try {
			while (true) {
				Socket toServer;
				toServer = new Socket(strServerIp, 8000);
				// 将信息发往服务器
				Message messobj = new Message();
				ObjectOutputStream streamtoserver = new ObjectOutputStream(
						toServer.getOutputStream());
				streamtoserver.writeObject((Message) messobj);
				// 收来自服务器的信息
				ObjectInputStream streamfromserver = new ObjectInputStream(
						toServer.getInputStream());
				messobj = (Message) streamfromserver.readObject();

				// //////刷新聊天信息列表//////////
				if (isFirstLogin) // 如果刚登陆
				{
					intMessageCounter = messobj.chat.size(); // 屏蔽该用户登陆前的聊天内容
					isFirstLogin = false;
				}

				for (int i = intMessageCounter; i < messobj.chat.size(); i++) {

					if (messobj.chat.elementAt(i).getClass().getName().equals(
							"Chat"))// 如果接受的内容是聊天记录

					{
						Chat temp = (Chat) messobj.chat.elementAt(i);
						String temp_message;

						if (temp.chatUser.equals(strLoginName)) {
							if (temp.chatToUser.equals(strLoginName)) {
								temp_message = "系统提示您：请不要自言自语！" + "\n" + "\n";
							} else {
								if (!temp.whisper) // 不是悄悄话
								{
									temp_message = "【你】对【" + temp.chatToUser
											+ "】说：" + "\n  " + temp.chatMessage
											+ "\n" + "\n";
								} else {
									temp_message = "【你】悄悄对【" + temp.chatToUser
											+ "】说：" + "\n  " + temp.chatMessage
											+ "\n" + "\n";
								}
							}
						}
						else {
							if (temp.chatToUser.equals(strLoginName)) {
								if (!temp.whisper) // 不是悄悄话
								{
									temp_message = "【" + temp.chatUser
											+ "】对【你】说：" + "\n  "
											+ temp.chatMessage + "\n" + "\n";
								} else {
									temp_message = "【" + temp.chatUser
											+ "】悄悄对【你】说：" + "\n  "
											+ temp.chatMessage + "\n" + "\n";
								}
							} else {
								if (!temp.chatUser.equals(temp.chatToUser)) // 对方没有自言自语
								{
									if (!temp.whisper) // 不是悄悄话
									{
										temp_message = "【" + temp.chatUser
												+ "】对【" + temp.chatToUser
												+ "】说：" + "\n  "
												+ temp.chatMessage + "\n"
												+ "\n";
									} else {
										temp_message = "";
									}
								} else {
									temp_message = "";
								}
							}
						}
						taUserMessage.append(temp_message);

					}
					
					
					
					

					// ////////接发文件的处理////////
					if (messobj.chat.elementAt(i).getClass().getName().equals(
							"SendFile")) 
					{   
						 
						 
						SendFile Femp = (SendFile) messobj.chat.elementAt(i);
						butter = new byte[2500];
						int b;
						if((Femp.chatToUser.equals(strLoginName)||(!Femp.whisper))&&!(Femp.file1.getName().equals("aaa.txt")))
						{
							
							//new SaveFile(Femp.file1);
							try
					     {
					         butter = new byte[2500];                                 //创建一个2500个字节大小的字节数组
					         FileInputStream readfile=new FileInputStream(Femp.file1);//创建文件输入流,并将其指向之前已经创建好的文件对象file
					       if(!Femp.chatUser.equals(strLoginName)) {
					         while((b=readfile.read(butter,0,2500))!=-1)              //使用read()方法，读取文件内容，当到达文件尾时循环结束
					   	    {
					           String str=new String(butter,0,2500);//创建字符串str,用于转换并存储字节数组中内容
                               taUserMessage.append("");
                               taUserMessage.append(Femp.chatUser+"给你传送了文件:"+'\n'+"\n"); 
					           taUserMessage.append(str); 
					           taUserMessage.append("\n"+"\n");
					           //在文本区中加入读取到的文件内容
					   	    }
					         }
					         readfile.close();   
					              //关闭文件输入流
                                  // 特殊的文件，用于处理文件传输完成的反馈信息
					         if(Femp.whisper||((!Femp.whisper)&&Femp.chatUser.equals(strLoginName)))
					         {
					            SendFile Filesend = new SendFile();
								Filesend.file1 = new File("aaa.txt");
								Filesend.chatUser  =  Femp.chatUser;
								Filesend.chatToUser = Femp.chatUser;
								Filesend.whisper = Femp.whisper;
								

								try {
									Socket tooServer = new Socket(strServerIp, 8000);
									ObjectOutputStream outObj = new ObjectOutputStream(tooServer
											.getOutputStream());
									outObj.writeObject(Filesend);
									outObj.close();
									toServer.close();

								} catch (Exception e) {
									System.out.println(e);
								}
					      }
					     }
					      catch(IOException e)
					      {System.out.println("File read error");}
					     
				
						}
						
						
						
						if (Femp.chatToUser.equals(strLoginName)&&Femp.chatUser.equals(strLoginName)&&Femp.file1.getName().equals("aaa.txt"))
						{
							taUserMessage.append("文件传送完毕！" + "\n" + "\n");
							
						}

					}

					// ////////// 接收系统消息//////////////////
					if (messobj.chat.elementAt(i).getClass().getName().equals(
							"SystenMessage")) {
						SystenMessage Sysme = (SystenMessage) messobj.chat
								.elementAt(i);
						
						taUserMessage.append("  系统消息：" + "\n" + Sysme.message
								+ "\n" + "\n");
					}
					intMessageCounter++;

				} // for结束

				
				
				// //////刷新在线用户//////////
				lstUserList.removeAll();
//				cmbUser.removeAllItems();
//				cmbUser.addItem("所有人");
				for (int i = 0; i < messobj.userOnLine.size(); i++) { // boolean
					// b;
					// int[] c;

					String User = (String) messobj.userOnLine.elementAt(i);
					lstUserList.add(User);
					// for(int j=0;j<cmbUser.getItemCount();j++)
					// {if

					// }
//					cmbUser.addItem(User);
				}
				Integer a = new Integer(messobj.userOnLine.size()); // 显示在线人数
				lblCount.setText(a.toString());

				// 显示用户进入聊天室的信息
				if (messobj.userOnLine.size() > intUserTotal) {
					String tempstr = messobj.userOnLine.elementAt(
							messobj.userOnLine.size() - 1).toString();
					if (!tempstr.equals(strLoginName)) {
						taUserMessage.append("【" + tempstr + "】来了" + "\n");
					}
				}

				// 显示用户离开聊天室的信息
				if (messobj.userOnLine.size() < intUserTotal) {
					for (int b = 0; b < user_exit.size(); b++) {
						isFound = false;
						for (int c = 0; c < messobj.userOnLine.size(); c++) {
							if (user_exit.elementAt(b).equals(
									messobj.userOnLine.elementAt(c))) {
								isFound = true;
								break;
							}
						}
						if (!isFound) // 没有发现该用户
						{
							if (!user_exit.elementAt(b).equals(strLoginName)) {
								messobj.userOnLine.remove(user_exit
										.elementAt(b));
								taUserMessage
										.append("【" + user_exit.elementAt(b)
												+ "】走了" + "\n");
							}
						}
					}
				}
				user_exit = messobj.userOnLine;
				intUserTotal = messobj.userOnLine.size();
				streamtoserver.close();
				streamfromserver.close();
				toServer.close();
				this.thread.sleep(1000);
			}

		} catch (Exception e) {
			JOptionPane jop = new JOptionPane();
			jop.showMessageDialog(null, "不能连接服务器！");
			System.out.println(e);
		}

	} // run()结束

	// /////////监听按钮响应//////////////
	public void actionPerformed(ActionEvent ae) {
		Object source = (Object) ae.getSource();
		if (source.equals(btnCls)) // 音乐播放器
		{
			new Music();
			
			
		}
		if (source.equals(mnuiContent))//目录
		{
			dailog11.setVisible(true);
		}
		if (source.equals(mnuiAbout))// 聊天室简介
		{
			dailogs.setVisible(true);
		}

		if (source.equals(mnuiExit)) // 退出聊天界面
		{
			exit();
		}
		if (source.equals(mnuiSave)) // 保存聊天记录
		{
			saveMessage();
		}

		if (source.equals(btnExit)) // 退出聊天界面
		{
			exit();
			
		}
		if (source.equals(btnSend)) // 发送聊天信息
		{
			sendMessage();
		}
		if (source.equals(btnSave)) // 保存聊天记录
		{
			saveMessage();
		}
		if (source.equals(lstUserList)) // 双击列表框
		{
			changeUser();

		}
		if (source.equals(PublicChat)) // 群聊
		{
			 txtTouser.setText("所有人");
			 btnSend.setEnabled(true);
			 btnSendFile.setEnabled(true);
			  
			}
		if (source.equals(chPrivateChat)) // 私聊
			
		{	if(txtTouser.getText().equals("所有人"))
			{
			 txtTouser.setText("");
			 btnSend.setEnabled(false);
			 btnSendFile.setEnabled(false);
			} 
			}
		
       if (source.equals(chPublicChat)) // 私聊
			
		{	if(txtTouser.getText().equals("所有人"))
			{
			 txtTouser.setText("");
			 btnSend.setEnabled(false);
			 btnSendFile.setEnabled(false);
			} 
			}
			
		
		if (source.equals(mnuiCls)) // 清屏菜单项
		{
			clearMessage();
		}

		if (source.equals(btnBrowse)) // 文件的打开按钮
		{
			JFileChooserDemo jFramefile = new JFileChooserDemo();
     		file = jFramefile.fileChooser.getSelectedFile();
			txtFile.setText(file.getName());

		}
		if (source.equals(btnSendFile)) // 文件的发送按钮
		{
			sendFile();
		}

	} // actionPerformed()结束

	// /////////监听窗口关闭响应//////////////
	class Windowclose extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			exit();
		}
	}

	// "清屏"按钮
	public void clearMessage() {
		taUserMessage.setText("");
	}

	// "退出"按钮
	public void exit() {
		Exit1 exit = new Exit1();
		exit.exitname = strLoginName;
		// 发送退出信息
		try {
			Socket toServer = new Socket(strServerIp, 8000);
			// 向服务器发送信息
			ObjectOutputStream outObj = new ObjectOutputStream(toServer
					.getOutputStream());
			outObj.writeObject(exit);

			outObj.close();
			toServer.close();
			frmChat.dispose();
		} catch (Exception e) {
		}

	} // exit()结束

	// "发送"按钮
	public void sendMessage() {
		Chat chatobj = new Chat();
		chatobj.chatUser = strLoginName;
		chatobj.chatMessage = txtMessage.getText();
		chatobj.chatToUser = txtTouser.getText();
		chatobj.whisper = chPrivateChat.isSelected() ? true : false;

		// 向服务器发送信息
		try {
			Socket toServer = new Socket(strServerIp, 8000);
			ObjectOutputStream outObj = new ObjectOutputStream(toServer
					.getOutputStream());
			outObj.writeObject(chatobj);
			txtMessage.setText(""); // 清空文本框
			outObj.close();
			toServer.close();
		} catch (Exception e) {
		}
	} // sendMessage()结束

	// "保存"按钮
	public void saveMessage() {
		try {
			FileOutputStream fileoutput = new FileOutputStream("message.txt",
					true);
			String temp = taUserMessage.getText();
			System.out.println(temp);
			fileoutput.write(temp.getBytes());
			fileoutput.close();
			// JOptionPane jop = new JOptionPane();
			// jop.showMessageDialog(null,"聊天记录已保存！")；

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// 将所选用户添加到cmbUser中
	public void changeUser() {
		// boolean key = true;
		String selected = lstUserList.getSelectedItem();

		/*
		 * for(int i = 0; i < cmbUser.getItemCount(); i++) {
		 * if(selected.equals(cmbUser.getItemAt(i))) { key = false; break; } }
		 * if(key == true) { cmbUser.insertItemAt(selected,0); }
		 * cmbUser.setSelectedItem(selected);
		 */

		txtTouser.setText(selected);
		chPrivateChat.setSelected(true);
		btnSend.setEnabled(true);
		btnSendFile.setEnabled(true);

	} // changeUser()结束

	public void sendFile() {

		if(txtTouser.getText().equals(strLoginName))
		{
			taUserMessage.append("\n"+"不能自己给自己发送文件"+"\n");
		}
		else{
		
		SendFile Filesend = new SendFile();

		Filesend.file1 = file;
		Filesend.chatUser = strLoginName;
		Filesend.chatToUser = txtTouser.getText();
		Filesend.whisper = chPrivateChat.isSelected() ? true : false;

		try {
			Socket toServer = new Socket(strServerIp, 8000);
			ObjectOutputStream outObj = new ObjectOutputStream(toServer
					.getOutputStream());
			outObj.writeObject(Filesend);
			outObj.close();
			toServer.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		}
		taUserMessage.append("正在发送文件【"+file.getName()+"】....." + "\n" + "\n");

	}

	public static void main(String args[]) {
		new ChatRoom("用户", "127.0.0.1", "1");
	}

}