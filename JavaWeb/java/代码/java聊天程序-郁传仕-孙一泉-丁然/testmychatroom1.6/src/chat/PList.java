package chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PList extends JFrame implements ListSelectionListener,
		ActionListener
{
	private JList peopleList; // 显示进入聊天室的人名单
	private JButton refurbishButton;// 刷新列表按钮
	public DefaultListModel listModel;// 用户列表
	public ChatFrame chf;

	public PList(ChatFrame sup)
	{

		super(sup.myName);

		try
		{ // 使用Windows的界面风格
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		chf = sup;

		JPanel eastPanel = new JPanel(new BorderLayout());
		JLabel title = new JLabel("我的在线好友");
		listModel = new DefaultListModel();// 实现 java.util.Vector API 在发生更改时通知
											// ListDataListener
		peopleList = new JList(listModel);
		peopleList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);// 设置单选还是多选
		JScrollPane ListScrollPane = new JScrollPane(peopleList);
		ListScrollPane.setPreferredSize(new Dimension(150, 400));

		refurbishButton = new JButton("刷新列表");
		refurbishButton.addActionListener(this);// 监听刷新按钮
		peopleList.addListSelectionListener(this);
		
		eastPanel.add(title, BorderLayout.NORTH);
		eastPanel.add(ListScrollPane, BorderLayout.CENTER);
		eastPanel.add(refurbishButton, BorderLayout.SOUTH);
		this.add(eastPanel);
		this.setVisible(true);
		this.setSize(new Dimension(180, 600));
		this.setLocation(270, 50);
	}

	public void valueChanged(ListSelectionEvent e)
	{// 监听下拉列表的显示内容
		if (e.getSource() == peopleList)
		{
			try
			{
				String select = (String) peopleList.getSelectedValue();
				if (select != null)
				{ // 确保选择非空
					String[] userInfo = select.split("〖");
					String name = userInfo[0].trim(); // 提取名字

					if (!name.equals(chf.myName))
					{ // 不能添加自己
						int count = chf.perponsComboBox.getItemCount();
						for (int i = 0; i < count; i++)
						{
							chf.perponsComboBox.setSelectedIndex(i);// 选择第i项
							String strName = (String) chf.perponsComboBox
									.getSelectedItem();
							if (name.equals(strName))
							{ // 如果已经在列表中，就复合框中选中，所以要确保没有重名
								return;
							}
						}
						// 如果没有添加，就会执行下面语句 添加
						chf.perponsComboBox.addItem(name);
					}
				}
			} catch (Exception ee)
			{
				System.out.println("发生错误 在valueChanged " + ee);
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == refurbishButton)
		{ // 如果监听到要刷新列表
			try
			{
				listModel.clear(); // 清空列表
				chf.out.println("refurbish"); // 发送刷新请求到服务器
				chf.out.flush();
			} catch (Exception ee)
			{
			}
		}
	}

}
