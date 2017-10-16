/*
 * 这是客户端连接服务器的后台
 */
package com.qq.client.model;

import java.util.*;
import java.net.*;
import java.io.*;

import com.qq.client.tools.*;
import com.qq.common.*;
public class QqClientCon {
	
	public Socket s;
	
	//发送第一次请求
	public boolean sendLoginInfoToServer(Object o){
		boolean b=false;
		try{
			s=new Socket("192.168.1.22",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();
			//这里就是验证用户登录的地方
			if(ms.getMesType().equals("1")){
				//就创建一个该qq号与服务器保持通信的线程
				ClientConServerThread ccst=new ClientConServerThread(s);
				//启动该通信线程
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), ccst);
				b=true;
			}
		}catch(Exception e){
			
		}finally{
			
		}
		return b;
	}
}
