package com.qq.client.model;


import com.qq.common.*;
public class QqClientUser {

	public boolean checkUser(User u){
		return new QqClientCon().sendLoginInfoToServer(u);
	}
}
