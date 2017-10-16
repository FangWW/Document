/*
 * 这是qq服务器，他在监听，等待某个qq客户端，未连接
 */
package com.qq.server.model;
import java.net.*;
import java.io.*;
import java.util.*;

import com.qq.common.*;
public class MyQqServer {

	public MyQqServer(){
		ServerSocket ss = null;
		try{
			//在端口9999监听
			//System.out.println("我是服务器，在端口9999监听");
			ss=new ServerSocket(9999);
			
			while(true){
				//阻塞 等待连接
				Socket s=ss.accept();
				//接受客户端发来的信息
	
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();
				
				Message m=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("123456")){
					//返回一个成功的登录的信息包
					m.setMesType("1");
					oos.writeObject(m);
					
					//这里就单开一个线程 让该线程与客户端保持通信
					SerConClientThread scct=new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), scct);
					//启动与该客户端通信的线程
					scct.start();
					
					//通知在线好友
					scct.notifyOther(u.getUserId());
				}else{
					m.setMesType("2");
					oos.writeObject(m);
					//关闭连接
					s.close();
				}
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
