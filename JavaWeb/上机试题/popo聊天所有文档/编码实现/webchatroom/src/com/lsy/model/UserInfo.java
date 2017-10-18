package com.lsy.model;

import java.util.Vector;

import com.lsy.vo.User;

public class UserInfo {
	private static UserInfo user = new UserInfo();
	private Vector vector = null;

	// 利用private调用构造函数，防止被外界产生新的instance对象
	private UserInfo() {
		this.vector = new Vector();
	}

	// 外界使用的instance对象
	public static UserInfo getInstance() {
		return user;
	}

	// 增加用户
	public boolean addUser(User user) {
		if (user != null) {
			this.vector.add(user);
			return true;
		} else {
			return false;
		}
	}

	// 获取用户列表
	public Vector getList() {
		 
		
		return vector;
	}

	// 移除用户
	public void removeUser(User user) {
		if (user != null) {
			vector.removeElement(user);
		}
	}
}

