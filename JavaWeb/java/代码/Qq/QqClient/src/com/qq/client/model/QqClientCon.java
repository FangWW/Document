/*
 * ���ǿͻ������ӷ������ĺ�̨
 */
package com.qq.client.model;

import com.qq.client.tools.*;
import com.qq.common.*;
public class QqClientCon {
	
	public Socket s;
	
	//���͵�һ������
	public boolean sendLoginInfoToServer(Object o){
		boolean b=false;
		try{
			s=new Socket("192.168.1.22",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			Message ms=(Message)ois.readObject();
			//���������֤�û���¼�ĵط�
			if(ms.getMesType().equals("1")){
				//�ʹ���һ����qq�������������ͨ�ŵ��߳�
				ClientConServerThread ccst=new ClientConServerThread(s);
				//������ͨ���߳�
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
