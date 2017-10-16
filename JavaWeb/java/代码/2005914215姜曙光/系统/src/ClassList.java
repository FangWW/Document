import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.util.*;
import java.io.File;


public class ClassList {
	
	class Customer implements Serializable
	{
		String custName;
		String custPassword;
	}

//	封装注册信息
	class Register_Customer extends Object implements java.io.Serializable
	{
	     String custName;
	     String custPassword;
	     String age;
	     String sex;
	     String email;
	}

//	用于发送聊天和在线用户的信息  
	class Message implements Serializable
	{
	  	Vector userOnLine;
	  	Vector chat;
	}
//	聊天信息序列化
	class Chat implements Serializable
	{
		String  chatUser;
		String  chatMessage;
		String  chatToUser;
		File    file1;
		boolean filee;
		boolean whisper;
	}  
//	退出信息序列化
/*	class Exit1 implements Serializable
	{
	    String exitname;	
	}*/

}

