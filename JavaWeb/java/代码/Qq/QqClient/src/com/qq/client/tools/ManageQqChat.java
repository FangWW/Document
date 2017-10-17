/*
 * ����һ�������û�����������
 */
package com.qq.client.tools;

import com.qq.client.view.*;
public class ManageQqChat {
	
	private static HashMap hm=new HashMap<String,QqChat>();
	
	//����
	public static void addQqChat(String loginIdAnFriendId,QqChat qqChat){
		hm.put(loginIdAnFriendId, qqChat);
	}
	//ȡ��
	public static QqChat getQqChat(String loginIdAnFriendId){
		return(QqChat)hm.get(loginIdAnFriendId);
	}

}
