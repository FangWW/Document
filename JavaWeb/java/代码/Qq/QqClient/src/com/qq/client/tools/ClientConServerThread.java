/*
 * 这是客户端与服务器保持通信的线程
 */
package com.qq.client.tools;

import com.qq.common.*;
import java.io.*;
import java.net.*;

import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.Message;
public class ClientConServerThread extends Thread{

	private Socket s;
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	//构造函数
	public ClientConServerThread(Socket s){
		this.s=s;
	}
	
	public void run(){
		while(true){
			//不停的读取从服务器端发来的消息
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				//System.out.println("读取到从服务器发来的消息"+m.getSender()+" 给 "+m.getGetter()+" 内容 "+m.getCon());
				
				if(m.getMesType().equals(MessageType.message_comm_mes)){
					//把从服务器获得的消息，显示到该显示的聊天界面
					QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					//显示
					qqChat.showMessage(m);
				}else if(m.getMesType().equals(MessageType.message_ret_onLineFriend)){
					String con=m.getCon();
					String friend[]=con.split(" ");
					String getter=m.getGetter();
					//修改相应的好友列表
					QqFriendList qqFriendList=ManageQqFriendList.getQqFriendList(getter);
					
					//更新在线好友列表
					if(qqFriendList!=null){
						qqFriendList.upDateFriend(m);
					}
				}
			} catch (Exception e) {}
		}
	}
}
