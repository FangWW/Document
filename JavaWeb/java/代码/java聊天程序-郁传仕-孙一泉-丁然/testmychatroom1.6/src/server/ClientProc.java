package server;

import java.net.*;
import java.util.*;
import java.io.*;

class ClientProc implements Runnable
{
	/**
	 * 为某个用户服务的一个用户线程
	 */
	Socket s;
	BufferedReader in;
	PrintWriter out;
	private String name = null;
	private String sex = null;

	public ClientProc(Socket s) throws IOException
	{
		this.s = s;
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));// 得到输入的端口流
		out = new PrintWriter(s.getOutputStream());// 从端口得到一个输出流
		this.updateList();
	}

	public String getName()
	{
		return name;
	}

	public String getSex()
	{
		return sex;
	}

	public Socket getSocket()
	{
		return s;
	}

	private void updateList()
	{
		// 更新用户列表（即发送当前在聊天室的用户到新来的用户列表中）
		Vector cs = ChatServer.getClients();
		if (cs != null)
		{
			for (Enumeration e = cs.elements(); e.hasMoreElements();)
			{
				ClientProc cp = (ClientProc) e.nextElement();
				String exist_name = cp.getName();
				String exit_sex = cp.getSex();
				/**
				 * for (int i = 0;i<cs.size() ;i++ ){ String exist_name
				 * =((ClientProc)cs.elementAt(i)).getName(); String exit_sex =
				 * ((ClientProc)cs.elementAt(i)).getSex();
				 */
				System.out.println("old" + "&" + exist_name + "&" + exit_sex);
				out.println("old" + "&" + exist_name + "&" + exit_sex);
				out.flush();
			}
		}
	}

	public void run()
	{
		while (name == null)
		{
			try
			{
				String inmsg;
				inmsg = in.readLine();
				ChatServer.sendAll("new&" + inmsg); // 发送信息更新用户列表 new & zhangsan
				// & boy

				String[] userInfo;
				userInfo = inmsg.split("&");
				name = userInfo[0];
				sex = userInfo[1];
			} catch (IOException ee)
			{
				ee.printStackTrace();
			}
		}

		while (true)
		{
			try
			{
				String line = in.readLine();
				System.out.println(line);
				// 处理退出事件(读取信息)
				if (line.equals("quit"))
				{
					ChatServer.sendAll("【系统消息】 " + this.name + " 退出了聊天室");
					ChatServer.deleteConnection(s, this);
					return;
					// 处理刷新用户列表请求
				} else if (line.equals("refurbish"))
				{
					this.updateList();
					// 一般消息,又可以分为几种,对大家说, 与某个人交谈,或者私聊
				} else
				{
					String[] inmsg = line.split("&");
					if (inmsg[0].compareTo("cancelsendfile") == 0)
					{
						ChatServer.sendOne(inmsg[1], "cancelsendfile" + "&"
								+ this.name);
					} else if (inmsg[0].compareTo("sendfile") == 0)// 发送文件消息
					{
						// String[] sendfile = line.split("&");
						ChatServer.sendOne(inmsg[1], "sendfile" + "&"
								+ this.name + "&" + inmsg[2] + "&" + inmsg[3]
								+ "&" + inmsg[4] + "&" + inmsg[5]);
					} else if (inmsg[0].compareTo("acceptfile") == 0)// 接收文件消息
					{
						// String[] acceptfile = line.split("&");
						ChatServer
								.sendOne(inmsg[1], inmsg[0] + "&" + this.name);
					} else if (inmsg[0].compareTo("refusefile") == 0)// 拒绝接收文件消息
					{
						// String[] refusefile = line.split("&");
						ChatServer
								.sendOne(inmsg[1], inmsg[0] + "&" + this.name);
					} else if (!line.startsWith("withWho"))
					{ // 对所有人说
						ChatServer.sendAll(this.name + ": " + line);
					}

					// String[] inmsg = line.split("&");
					else if (inmsg[1].equals("privateTure"))
					{
						if (ChatServer.sendOne(inmsg[2], "privateTalk" + "&"
								+ name + "&" + inmsg[2] + "&" + inmsg[3]))
						{ // success
							out.println("privateTalk" + "&" + name + "&"
									+ inmsg[2] + "&" + inmsg[3]);
							// 再发给自己一份
							out.flush(); // 一定要有
						} else
						{
							out.println(inmsg[2] + "已经离开聊天室");
							out.flush(); // 一定要记住要
						}
					} else
					{
						ChatServer.sendAll("withWho" + "&" + name + "&"
								+ inmsg[2] + "&" + inmsg[3]);
					} // 发给所有的人

				}
			} catch (IOException e)
			{
				System.out.println(e.toString());
				try
				{
					s.close();
				} catch (IOException e2)
				{
				}
				return;
			}
		}
	}
}
