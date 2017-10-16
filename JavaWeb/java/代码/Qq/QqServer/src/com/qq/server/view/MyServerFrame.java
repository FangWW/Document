/*
 * 这是服务器的控制界面，可以完成启动服务器，关闭服务器
 * 可以管理和监控用户
 */
package com.qq.server.view;

import javax.swing.*;

import com.qq.server.model.MyQqServer;

import java.awt.*;
import java.awt.event.*;
public class MyServerFrame extends JFrame implements ActionListener{

	JPanel jp1;
	JButton jb1,jb2;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame mysf=new MyServerFrame();
	}
	
	public MyServerFrame(){
		jp1=new JPanel();
		jb1=new JButton("启动服务器");
		jb1.addActionListener(this);
		jb2=new JButton("关闭服务器");
		jb2.addActionListener(this);
		jp1.add(jb1);
		jp1.add(jb2);
		
		this.add(jp1);
		this.setSize(500,500);
		this.setTitle("山寨QQ服务器2010");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			new MyQqServer();
		}else if(e.getSource()==jb2){
			
		}
	}

}
