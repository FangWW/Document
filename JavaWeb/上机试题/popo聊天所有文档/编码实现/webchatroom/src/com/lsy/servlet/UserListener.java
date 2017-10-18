package com.lsy.servlet;
import java.util.HashMap;

import com.lsy.model.UserInfo;
import com.lsy.vo.User;

import javax.servlet.http.HttpSessionBindingEvent;

public class UserListener implements
		javax.servlet.http.HttpSessionBindingListener {
	private User user;
	private UserInfo container = UserInfo.getInstance();	//获得UserInfo类的对象

	public UserListener() {
		user = null;
	}

	// 设置在线监听人员
	public void setUser(User user) {
		this.user = user;
	}

	// 获取在线监听
	public User getUser() {
		return this.user;
	}

	// 当Session有对象加入时执行的方法
	public void valueBound(HttpSessionBindingEvent arg0) {
		

		System.out.println("上线用户：" + this.user.getNickname()+"("+this.user.getQq()+")");

	}

	// 当Session有对象移除时执行的方法
	public void valueUnbound(HttpSessionBindingEvent arg0) {
		System.out.println("下线用户：" +this.user.getNickname()+"("+this.user.getQq()+")");
		if (user != null) {
			container.removeUser(user);
		}
	}
}
