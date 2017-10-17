/*
 * ����qq�����������ڼ������ȴ�ĳ��qq�ͻ��ˣ�δ����
 */
package com.qq.server.model;
import com.qq.common.*;
public class MyQqServer {

	public MyQqServer(){
		ServerSocket ss = null;
		try{
			//�ڶ˿�9999����
			//System.out.println("���Ƿ��������ڶ˿�9999����");
			ss=new ServerSocket(9999);
			
			while(true){
				//���� �ȴ�����
				Socket s=ss.accept();
				//���ܿͻ��˷�������Ϣ
	
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();
				
				Message m=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(u.getPasswd().equals("123456")){
					//����һ���ɹ��ĵ�¼����Ϣ��
					m.setMesType("1");
					oos.writeObject(m);
					
					//����͵���һ���߳� �ø��߳���ͻ��˱���ͨ��
					SerConClientThread scct=new SerConClientThread(s);
					ManageClientThread.addClientThread(u.getUserId(), scct);
					//������ÿͻ���ͨ�ŵ��߳�
					scct.start();
					
					//֪ͨ���ߺ���
					scct.notifyOther(u.getUserId());
				}else{
					m.setMesType("2");
					oos.writeObject(m);
					//�ر�����
					s.close();
				}
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
