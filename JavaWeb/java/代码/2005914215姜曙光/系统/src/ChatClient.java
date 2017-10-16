//package mypack;
import java.io.*;
import java.util.*;



//////////设置服务器IP地址/////////
public class ChatClient 
{
     
    public ChatClient()
    {
    }
    public static void main(String args[])
	{
		new Login();
	}
}
    
//登录信息序列化
class Customer extends Object implements java.io.Serializable
{
     String custName;
     String custPassword;
}

//注册信息序列化
class Register_Customer extends Object implements java.io.Serializable
{
     String custName;
     String custPassword;
     String age;
     String sex;
     String email;
     String picture;
}

//聊天和在线用户的信息  
class Message implements Serializable
{
  	Vector userOnLine;
  	Vector chat;
}
//聊天信息序列化
class Chat implements Serializable
{
	String  chatUser;
	String  chatMessage;
	String  chatToUser;
    boolean whisper;
}  
//文件的发送
//class SendFile implements Serializable
//{    String  chatUser;
//     String  chatToUser;
//     File file1;
//     boolean whisper;
//  } 
