import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.applet.*;
/**
 * <p>Title: Applet中使用SWING</p>
 * <p>Description: 使用SWING的JApplet实现树功能。</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: PageTree.java</p>
 * @author 杜江
 * @version 1.0
 */
public class PageTree extends JApplet implements TreeSelectionListener {
	DefaultMutableTreeNode[] nodeAt = new DefaultMutableTreeNode[256];
	String target = null;
	Hashtable links = new Hashtable();
	AppletContext context;
	public void init() {
		//获取Applet的内容
		context = getAppletContext();
		//定义根节点
		DefaultMutableTreeNode root = null;
		getContentPane().setLayout(new BorderLayout());
		//获取参数。定义的数据配置文件。
		String s = getParameter("file");
		if (s != null) {
			try {
				//使用URL方式打开数据文件，实现在网络上使用。
				URL url = new URL(getDocumentBase(), s);
				BufferedReader br = new BufferedReader(
					new InputStreamReader(url.openStream(), "JISAutoDetect"));
				//读取第一行数据，如果为空则不执行
				String line = br.readLine();
				if (line == null) return;
				root = new DefaultMutableTreeNode(line);
				nodeAt[0] = root;
				int level = 0;
				while ((line = br.readLine()) != null) {
					//使用“|”分开数据
					StringTokenizer st = new StringTokenizer(line, "|");
					String token = st.nextToken();
					int n = countSpaces(token);
					//添加节点名称
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(token.trim());
					//计算节点级数
					for (int i = level + 1; i < n; i++) {
						DefaultMutableTreeNode dummy = new DefaultMutableTreeNode("");
						nodeAt[i].add(dummy);
						nodeAt[i+1] = dummy;
					}
					level = n;
					nodeAt[n].add(node);
					nodeAt[n+1] = node;
					//对“|”符号后的数据进行处理
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
			//添加树的选择监听
			tree.addTreeSelectionListener(this);
			JScrollPane jsp = new JScrollPane(tree);
			getContentPane().add(jsp, BorderLayout.CENTER);
		}
	}
/**
 *<br>方法说明：计算空格数
 *<br>输入参数：
 *<br>返回类型：
 */	
	private int countSpaces(String s) {
		int n = 0;
		while (s.charAt(n) == ' ') {
			n++;
		}
		return n;
	}
/**
 *<br>方法说明：树选择监听实现方法
 *<br>输入参数：TreeSelectionEvent e 选择树节点事件
 *<br>返回类型：
 */	
	public void valueChanged(TreeSelectionEvent e) {
		TreePath path = e.getPath();
		Object o = path.getLastPathComponent();
		URL url = (URL)links.get(o);
		if (url != null) {
			System.out.println(links.get(o));
			//打开连接，通知浏览器加载网页（使用浏览器打开Applet的情况下）
			context.showDocument(url);
		}
	}

}