/*
 * ���ǿͻ��������������ͨ�ŵ��߳�
 */
package com.qq.client.tools;

import com.qq.client.view.QqChat;
import com.qq.client.view.QqFriendList;
import com.qq.common.*;
import com.qq.common.Message;
public class ClientConServerThread extends Thread{

	private Socket s;
	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	//���캯��
	public ClientConServerThread(Socket s){
		this.s=s;
	}
	
	public void run(){
		while(true){
			//��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				//System.out.println("��ȡ���ӷ�������������Ϣ"+m.getSender()+" �� "+m.getGetter()+" ���� "+m.getCon());
				
				if(m.getMesType().equals(MessageType.message_comm_mes)){
					//�Ѵӷ�������õ���Ϣ����ʾ������ʾ���������
					QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					//��ʾ
					qqChat.showMessage(m);
				}else if(m.getMesType().equals(MessageType.message_ret_onLineFriend)){
					String con=m.getCon();
					String friend[]=con.split(" ");
					String getter=m.getGetter();
					//�޸���Ӧ�ĺ����б�
					QqFriendList qqFriendList=ManageQqFriendList.getQqFriendList(getter);
					
					//�������ߺ����б�
					if(qqFriendList!=null){
						qqFriendList.upDateFriend(m);
					}
				}
			} catch (Exception e) {}
		}
	}
}
