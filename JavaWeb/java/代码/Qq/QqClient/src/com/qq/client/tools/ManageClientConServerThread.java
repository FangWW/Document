/*
 * 这是一个管理客户端与服务器保持通信的线程类
 */
package com.qq.client.tools;

import java.util.*;
public class ManageClientConServerThread {

	private static HashMap hm=new HashMap<String,ClientConServerThread>();
	
	//把创建好的ClientConServerThread放入到hm
	public static void addClientConServerThread(String qqId,ClientConServerThread ccst){
		hm.put(qqId, ccst);
	}
	//可以通过qqId取得该线程
	public static ClientConServerThread getClientConServerThread(String qqId){
		return (ClientConServerThread)hm.get(qqId);
	}
}
