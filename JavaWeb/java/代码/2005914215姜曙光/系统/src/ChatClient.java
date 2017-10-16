//package mypack;
//////////���÷�����IP��ַ/////////
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
    
//��¼��Ϣ���л�
class Customer extends Object implements java.io.Serializable
{
     String custName;
     String custPassword;
}

//ע����Ϣ���л�
class Register_Customer extends Object implements java.io.Serializable
{
     String custName;
     String custPassword;
     String age;
     String sex;
     String email;
     String picture;
}

//����������û�����Ϣ  
class Message implements Serializable
{
  	Vector userOnLine;
  	Vector chat;
}
//������Ϣ���л�
class Chat implements Serializable
{
	String  chatUser;
	String  chatMessage;
	String  chatToUser;
    boolean whisper;
}  
//�ļ��ķ���
//class SendFile implements Serializable
//{    String  chatUser;
//     String  chatToUser;
//     File file1;
//     boolean whisper;
//  } 
