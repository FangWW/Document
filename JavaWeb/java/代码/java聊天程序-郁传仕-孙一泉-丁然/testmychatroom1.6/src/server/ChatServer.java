package server;

public class ChatServer
{
	static final int DEFAULT_PORT = 6000;
	static ServerSocket serverSocket;
	static Vector<Socket> connections;// ����
	static Vector<ClientProc> clients;

	/**
	 * 
	 * ������Ϣ�����е���
	 */
	public static void sendAll(String s)
	{
		if (connections != null)
		{
			for (Enumeration e = connections.elements(); // Enumeration��ʱ�Ľӿڣ�
															// ������for eachѭ��
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
	 * ������Ϣ������һ����
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
						return true; // ����ֵΪ�棬�ҵ�������˿��Խ�������
					} catch (IOException ioe)
					{
						ioe.printStackTrace();
					}
				}
			}
		}
		return false;// û���ҵ�����ˣ�Ӧ���Ǵ����Ѿ��˳���������
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
	 * ���������ڴ�����
	 */
	public static void main(String[] arg)
	{
		int port = DEFAULT_PORT;
		try
		{
			serverSocket = new ServerSocket(port);
			System.out.println("�������Ѿ����������ڼ���...");
		} catch (IOException e)
		{
			System.out.println("�쳣");
			System.err.println(e);
			System.exit(1);

		}

		while (true)
		{ // ��ѭ��
			try
			{
				Socket cs = serverSocket.accept();
				ClientProc cp = new ClientProc(cs); // ����һ���û��߳�
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