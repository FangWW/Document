/*
 * 我的好友列表（包括陌生人与黑名单）
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.common.Message;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class QqFriendList extends JFrame implements ActionListener,MouseListener{

	//处理第一张卡片
	
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jspl;
	String owner;
	JLabel []jbls;
	
	//处理第二张卡片（陌生人）
	
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	
	//把整个JFrame设置成CardLayout
	CardLayout cl;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	QqFriendList qqFriendList=new QqFriendList();
	}
	
	//更新在线好友列表
	public void upDateFriend(Message m){
		String onLineFriend[]=m.getCon().split(" ");
		for(int i=0;i<onLineFriend.length;i++){
			jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		}
	}
	
	public QqFriendList(String ownerId){
		
		this.owner=ownerId;
		//处理第一张卡片（显示好友列表）
		jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("黑名单");
		jphy1=new JPanel(new BorderLayout());
		//假设有50好友
		jphy2=new JPanel(new GridLayout(50,1,4,4));//行间距列间距
		
		//给jphy2初始化50好友
		jbls=new JLabel[50];
		
		for(int i=0;i<jbls.length;i++){
			jbls[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);//不支持个.gif、.bmp
			jbls[i].setEnabled(false);
			if(jbls[i].getText().equals(ownerId)){
				jbls[i].setEnabled(true);
			}
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
		}
		
		jphy3=new JPanel(new GridLayout(2,1));
		//把两个按钮加入到jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		jspl=new JScrollPane(jphy2);
		
		//对jphy1初始化
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jspl,"Center");
		jphy1.add(jphy3,"South");
		
		//处理第二张卡片
		
		jpmsr_jb1=new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("陌生人");
		jpmsr_jb3=new JButton("黑名单");
		jpmsr1=new JPanel(new BorderLayout());
		//假设有20陌生人
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));//行间距列间距
		
		//给jphy2初始化20陌生人
		JLabel []jbls2=new JLabel[20];
		
		for(int i=0;i<jbls2.length;i++){
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);//不支持个.gif、.bmp
			jpmsr2.add(jbls2[i]);
		}
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		//把两个按钮加入到jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		jsp2=new JScrollPane(jpmsr2);
		
		//对jpmsr1初始化
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"1");
		this.add(jpmsr1,"2");
		//显示自己的编号
		this.setTitle(ownerId);
		this.setTitle("山寨QQ2010");
		this.setIconImage((new ImageIcon("image/jie.jpg").getImage()));
		this.setSize(220,600);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jphy_jb2){
			cl.show(this.getContentPane(), "2");
		}else if(e.getSource()==jpmsr_jb1){
			cl.show(this.getContentPane(), "1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent er) {
		// TODO Auto-generated method stub
		//响应用户双击事件，并得到好友的编号
		if(er.getClickCount()==2){
			//得到好友的编号
			String friendNo=((JLabel)er.getSource()).getText();
			//System.out.println("你希望和"+friendNo+"聊天");
			QqChat qqChat=new QqChat(owner,friendNo);
			
			//把聊天界面加入到管理类
			ManageQqChat.addQqChat(this.owner+" "+friendNo, qqChat);
			//Thread t=new Thread(qqChat);
			//t.start();
		}
	}

	@Override
	public void mouseEntered(MouseEvent er) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)er.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent er) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)er.getSource();
		jl.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent er) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent er) {
		// TODO Auto-generated method stub
		
	}

}
