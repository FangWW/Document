/*
 * ���ܣ��Ƿ�������ĳ���ͻ��˵�ͨ���߳�
 */
package com.qq.server.model;

import com.qq.common.*;
public class SerConClientThread extends Thread{
	
	Socket s;
	
	public SerConClientThread(Socket s){
		//�ѷ������͸ÿͻ��˵����Ӹ���s
		this.s=s;
	}
	
	//�ø��߳�֪ͨ�����û�
	public void notifyOther(String iam){
		//�õ����������˵��߳�
		HashMap hm=ManageClientThread.hm;
		Iterator it=hm.keySet().iterator();
		
		while(it.hasNext()){
			Message m=new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onLineFriend);
			//ȡ�������˵�id
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
			//������߳̾Ϳ��Խ��ܿͻ��˵���Ϣ
			try{
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				
				//System.out.println(m.getSender()+"��"+m.getGetter()+"˵��"+m.getCon());
				
				//�Դӿͻ���ȡ�õ���Ϣ���������жϣ�Ȼ��������Ӧ�Ĵ���
				if(m.getMesType().equals(MessageType.message_comm_mes)){
				//һ�����ת�� 
				//ȡ�ý����˵�ͨ���߳�
				SerConClientThread sc=ManageClientThread.gerClientThread(m.getGetter());
				ObjectOutputStream oos=new ObjectOutputStream(sc.s.getOutputStream());
				oos.writeObject(m);
				}else if(m.getMesType().equals(MessageType.message_get_onLineFriend)){
					//�ѷ������ĺ��ѷ��ظ��ͻ���
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
