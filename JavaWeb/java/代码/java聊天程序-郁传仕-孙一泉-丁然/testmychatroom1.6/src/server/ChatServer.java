package server;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatServer
{
	static final int DEFAULT_PORT = 6000;
	static ServerSocket serverSocket;
	static Vector<Socket> connections;// 连接
	static Vector<ClientProc> clients;

	/**
	 * 
	 * 发送信息给所有的人
	 */
	public static void sendAll(String s)
	{
		if (connections != null)
		{
			for (Enumeration e = connections.elements(); // Enumeration过时的接口，
															// 可以用for each循环
			e.hasMoreElements();)
			{
				try
				{
					PrintWriter pw = new PrintWriter(((Socket) e.nextElement())
							.getOutputStream());
					pw.println(s);
					pw.flush();
				} catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		System.out.println(s);
	}

	/**
	 * 
	 * 发送信息给单独一个人
	 */
	public static boolean sendOne(String name, String msg)
	{
		if (clients != null)
		{
			for (Enumeration e = clients.elements(); e.hasMoreElements();)
			{
				ClientProc cp = (ClientProc) e.nextElement();
				if ((cp.getName()).equals(name))
				{
					try
					{
						PrintWriter pw = new PrintWriter((cp.getSocket())
								.getOutputStream());
						pw.println(msg);
						pw.flush();
						return true; // 返回值为真，找到了这个人可以进行聊天
					} catch (IOException ioe)
					{
						ioe.printStackTrace();
					}
				}
			}
		}
		return false;// 没有找到这个人，应该是此人已经退出了聊天室
	}

	public static void addConnection(Socket s, ClientProc cp)
	{
		if (connections == null)
		{
			connections = new Vector<Socket>();
		}
		connections.addElement(s);

		if (clients == null)
		{
			clients = new Vector<ClientProc>();
		}
		clients.addElement(cp);
	}

	public static void deleteConnection(Socket s, ClientProc cp)
			throws IOException
	{
		if (connections != null)
		{
			connections.removeElement(s);
			s.close();
		}
		if (clients != null)
		{
			clients.removeElement(cp);
		}
	}

	public static Vector getClients()
	{
		return clients;
	}

	/**
	 * 
	 * 服务器端在此启动
	 */
	public static void main(String[] arg)
	{
		int port = DEFAULT_PORT;
		try
		{
			serverSocket = new ServerSocket(port);
			System.out.println("服务器已经启动，正在监听...");
		} catch (IOException e)
		{
			System.out.println("异常");
			System.err.println(e);
			System.exit(1);

		}

		while (true)
		{ // 死循环
			try
			{
				Socket cs = serverSocket.accept();
				ClientProc cp = new ClientProc(cs); // 启动一个用户线程
				Thread ct = new Thread(cp);
				//Thread ar = new Thread(ct.)
				ct.start();

				addConnection(cs, cp);
			} catch (IOException e)
			{
				System.err.println(e);
			}
		}
	}
}