/*
 * 这是与好友聊天的界面
 * 因为客户端要处于读取的状态、因此我们把它做成一个线程
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.client.model.*;
import com.qq.common.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class QqChat extends JFrame implements ActionListener{

	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqChat qqChat=new QqChat();
	}
	
	public QqChat(String ownerId, String friend){
		this.ownerId=ownerId;
		this.friendId=friend;
		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("发送");
		jb.addActionListener(this);
		jp=new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp,"South");
		this.setTitle(ownerId+"正在和"+friend+"聊天");
		this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
		this.setSize(300,200);
		this.setVisible(true);
	}
	
	//写一个方法，让她显示消息
	public void showMessage(Message m){
		String info=m.getSender()+" 对 "+m.getGetter()+" 说：  "+m.getCon()+"\r\n";
		this.jta.append(info);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb){
			//如果用户点击了“发送”按钮
			Message m=new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			//发送给服务器
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			
			
		}
	}

	/*	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				//读取(如果读不到就等待)
				ObjectInputStream ois=new ObjectInputStream(QqClientCon.s.getInputStream());
				Message m=(Message)ois.readObject();
				//显示
				String info=m.getSender()+" 对 "+m.getGetter()+" 说：  "+m.getCon()+"\r\n";
				this.jta.append(info);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}*/

}
