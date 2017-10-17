package com.qq.server.model;

public class ManageClientThread {

	public static HashMap hm=new HashMap<String,SerConClientThread>();
	
	//��hm�����һ���ͻ���ͨ���߳�
	public static void addClientThread(String uid,SerConClientThread ct){
		
		hm.put(uid, ct);
	}
	
	public static SerConClientThread gerClientThread(String uid){
		return (SerConClientThread)hm.get(uid);
	}
	
	//���ص�ǰ���ߵ��˵����
	public static String getAllOnlineUserId(){
		//ʹ�õ��������
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext()){
			res+=it.next().toString()+" ";
		}
		return res;
	}
}
