/*
 * ������ѡ���������������
 */
package com.qq.client.tools;

import com.qq.client.view.*;
public class ManageQqFriendList {
	
	private static HashMap hm=new HashMap<String,QqFriendList>();
	
	public static void addQqFriendList(String qqid,QqFriendList qqFriendList){
		
		hm.put(qqid, qqFriendList);
	}
	
	public static QqFriendList getQqFriendList(String qqid){
		return (QqFriendList)hm.get(qqid);
	}

}
