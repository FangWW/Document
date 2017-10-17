package server;

class ClientProc implements Runnable
{
	/**
	 * Ϊĳ���û������һ���û��߳�
	 */
	Socket s;
	BufferedReader in;
	PrintWriter out;
	private String name = null;
	private String sex = null;

	public ClientProc(Socket s) throws IOException
	{
		this.s = s;
		in = new BufferedReader(new InputStreamReader(s.getInputStream()));// �õ�����Ķ˿���
		out = new PrintWriter(s.getOutputStream());// �Ӷ˿ڵõ�һ�������
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
		// �����û��б������͵�ǰ�������ҵ��û����������û��б��У�
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
				ChatServer.sendAll("new&" + inmsg); // ������Ϣ�����û��б� new & zhangsan
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
				// �����˳��¼�(��ȡ��Ϣ)
				if (line.equals("quit"))
				{
					ChatServer.sendAll("��ϵͳ��Ϣ�� " + this.name + " �˳���������");
					ChatServer.deleteConnection(s, this);
					return;
					// ����ˢ���û��б�����
				} else if (line.equals("refurbish"))
				{
					this.updateList();
					// һ����Ϣ,�ֿ��Է�Ϊ����,�Դ��˵, ��ĳ���˽�̸,����˽��
				} else
				{
					String[] inmsg = line.split("&");
					if (inmsg[0].compareTo("cancelsendfile") == 0)
					{
						ChatServer.sendOne(inmsg[1], "cancelsendfile" + "&"
								+ this.name);
					} else if (inmsg[0].compareTo("sendfile") == 0)// �����ļ���Ϣ
					{
						// String[] sendfile = line.split("&");
						ChatServer.sendOne(inmsg[1], "sendfile" + "&"
								+ this.name + "&" + inmsg[2] + "&" + inmsg[3]
								+ "&" + inmsg[4] + "&" + inmsg[5]);
					} else if (inmsg[0].compareTo("acceptfile") == 0)// �����ļ���Ϣ
					{
						// String[] acceptfile = line.split("&");
						ChatServer
								.sendOne(inmsg[1], inmsg[0] + "&" + this.name);
					} else if (inmsg[0].compareTo("refusefile") == 0)// �ܾ������ļ���Ϣ
					{
						// String[] refusefile = line.split("&");
						ChatServer
								.sendOne(inmsg[1], inmsg[0] + "&" + this.name);
					} else if (!line.startsWith("withWho"))
					{ // ��������˵
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
							// �ٷ����Լ�һ��
							out.flush(); // һ��Ҫ��
						} else
						{
							out.println(inmsg[2] + "�Ѿ��뿪������");
							out.flush(); // һ��Ҫ��סҪ
						}
					} else
					{
						ChatServer.sendAll("withWho" + "&" + name + "&"
								+ inmsg[2] + "&" + inmsg[3]);
					} // �������е���

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
