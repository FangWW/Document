/**
 * <p>Title: Applet��ʹ��SWING</p>
 * <p>Description: ʹ��SWING��JAppletʵ�������ܡ�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: PageTree.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class PageTree extends JApplet implements TreeSelectionListener {
	DefaultMutableTreeNode[] nodeAt = new DefaultMutableTreeNode[256];
	String target = null;
	Hashtable links = new Hashtable();
	AppletContext context;
	public void init() {
		//��ȡApplet������
		context = getAppletContext();
		//������ڵ�
		DefaultMutableTreeNode root = null;
		getContentPane().setLayout(new BorderLayout());
		//��ȡ��������������������ļ���
		String s = getParameter("file");
		if (s != null) {
			try {
				//ʹ��URL��ʽ�������ļ���ʵ����������ʹ�á�
				URL url = new URL(getDocumentBase(), s);
				BufferedReader br = new BufferedReader(
					new InputStreamReader(url.openStream(), "JISAutoDetect"));
				//��ȡ��һ�����ݣ����Ϊ����ִ��
				String line = br.readLine();
				if (line == null) return;
				root = new DefaultMutableTreeNode(line);
				nodeAt[0] = root;
				int level = 0;
				while ((line = br.readLine()) != null) {
					//ʹ�á�|���ֿ�����
					StringTokenizer st = new StringTokenizer(line, "|");
					String token = st.nextToken();
					int n = countSpaces(token);
					//��ӽڵ�����
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(token.trim());
					//����ڵ㼶��
					for (int i = level + 1; i < n; i++) {
						DefaultMutableTreeNode dummy = new DefaultMutableTreeNode("");
						nodeAt[i].add(dummy);
						nodeAt[i+1] = dummy;
					}
					level = n;
					nodeAt[n].add(node);
					nodeAt[n+1] = node;
					//�ԡ�|�����ź�����ݽ��д���
					if (st.hasMoreTokens()) {
						token = st.nextToken();
						try {
							url = new URL(token.trim());
							links.put(node, url);
						} catch (MalformedURLException ex) {
							ex.printStackTrace();
						}
					}
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			s = getParameter("target");
			if (s != null) target = s.trim();
			JTree tree = new JTree(root);
			//�������ѡ�����
			tree.addTreeSelectionListener(this);
			JScrollPane jsp = new JScrollPane(tree);
			getContentPane().add(jsp, BorderLayout.CENTER);
		}
	}
/**
 *<br>����˵��������ո���
 *<br>���������
 *<br>�������ͣ�
 */	
	private int countSpaces(String s) {
		int n = 0;
		while (s.charAt(n) == ' ') {
			n++;
		}
		return n;
	}
/**
 *<br>����˵������ѡ�����ʵ�ַ���
 *<br>���������TreeSelectionEvent e ѡ�����ڵ��¼�
 *<br>�������ͣ�
 */	
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getPath();
		Object o = path.getLastPathComponent();
		URL url = (URL)links.get(o);
		if (url != null) {
			System.out.println(links.get(o));
			//�����ӣ�֪ͨ�����������ҳ��ʹ���������Applet������£�
			context.showDocument(url);
		}
	}

}