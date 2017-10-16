/*
 * 功能：是服务器和某个客户端的通信线程
 */
package com.qq.server.model;

import java.net.*;
import java.util.*;
import java.io.*;
import com.qq.common.*;
public class SerConClientThread extends Thread{
	
	Socket s;
	
	public SerConClientThread(Socket s){
		//把服务器和该客户端的连接赋给s
		this.s=s;
	}
	
	//让该线程通知其他用户
	public void notifyOther(String iam){
		//得到所有在线人的线程
		HashMap hm=ManageClientThread.hm;
		Iterator it=hm.keySet().iterator();
		
		while(it.hasNext()){
			Message m=new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			//取出在线人的id
			String onLineUserId=it.next().toString();
			try {
				ObjectOutputStream oos=new ObjectOutputStream(ManageClientThread.gerClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public void run(){
		while(true){
			//这里该线程就可以接受客户端的信息
			try{
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				
				//System.out.println(m.getSender()+"给"+m.getGetter()+"说："+m.getCon());
				
				//对从客户端取得的消息进行类型判断，然后做出相应的处理
				if(m.getMesType().equals(MessageType.message_comm_mes)){
				//一会完成转发 
				//取得接收人的通信线程
				SerConClientThread sc=ManageClientThread.gerClientThread(m.getGetter());
				ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(m);
				}else if(m.getMesType().equals(MessageType.message_get_onLineFriend)){
					//把服务器的好友返回给客户端
					String res=ManageClientThread.getAllOnlineUserId();
					Message m2=new Message();
					m2.setMesType(MessageType.message_ret_onLineFriend);
					m2.setCon(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
			}catch(Exception e){}
		}
	}

}
