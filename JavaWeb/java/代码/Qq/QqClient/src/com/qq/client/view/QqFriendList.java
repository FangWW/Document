/*
 * �ҵĺ����б�����İ�������������
 */
package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.common.Message;
public class QqFriendList extends JFrame implements ActionListener,MouseListener{

	//�����һ�ſ�Ƭ
	
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jspl;
	String owner;
	JLabel []jbls;
	
	//����ڶ��ſ�Ƭ��İ���ˣ�
	
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	
	//������JFrame���ó�CardLayout
	CardLayout cl;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	QqFriendList qqFriendList=new QqFriendList();
	}
	
	//�������ߺ����б�
	public void upDateFriend(Message m){
		String onLineFriend[]=m.getCon().split(" ");
		for(int i=0;i<onLineFriend.length;i++){
			jbls[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		}
	}
	
	public QqFriendList(String ownerId){
		
		this.owner=ownerId;
		//�����һ�ſ�Ƭ����ʾ�����б�
		jphy_jb1=new JButton("�ҵĺ���");
		jphy_jb2=new JButton("İ����");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("������");
		jphy1=new JPanel(new BorderLayout());
		//������50����
		jphy2=new JPanel(new GridLayout(50,1,4,4));//�м���м��
		
		//��jphy2��ʼ��50����
		jbls=new JLabel[50];
		
		for(int i=0;i<jbls.length;i++){
			jbls[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);//��֧�ָ�.gif��.bmp
			jbls[i].setEnabled(false);
			if(jbls[i].getText().equals(ownerId)){
				jbls[i].setEnabled(true);
			}
			jbls[i].addMouseListener(this);
			jphy2.add(jbls[i]);
		}
		
		jphy3=new JPanel(new GridLayout(2,1));
		//��������ť���뵽jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		
		jspl=new JScrollPane(jphy2);
		
		//��jphy1��ʼ��
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jspl,"Center");
		jphy1.add(jphy3,"South");
		
		//����ڶ��ſ�Ƭ
		
		jpmsr_jb1=new JButton("�ҵĺ���");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("İ����");
		jpmsr_jb3=new JButton("������");
		jpmsr1=new JPanel(new BorderLayout());
		//������20İ����
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));//�м���м��
		
		//��jphy2��ʼ��20İ����
		JLabel []jbls2=new JLabel[20];
		
		for(int i=0;i<jbls2.length;i++){
			jbls2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);//��֧�ָ�.gif��.bmp
			jpmsr2.add(jbls2[i]);
		}
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		//��������ť���뵽jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		jsp2=new JScrollPane(jpmsr2);
		
		//��jpmsr1��ʼ��
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"1");
		this.add(jpmsr1,"2");
		//��ʾ�Լ��ı��
		this.setTitle(ownerId);
		this.setTitle("ɽկQQ2010");
		this.setIconImage((new ImageIcon("image/jie.jpg").getImage()));
		this.setSize(220,600);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jphy_jb2){
			cl.show(this.getContentPane(), "2");
		}else if(e.getSource()==jpmsr_jb1){
			cl.show(this.getContentPane(), "1");
		}
	}

	@Override
	public void mouseClicked(MouseEvent er) {
		// TODO Auto-generated method stub
		//��Ӧ�û�˫���¼������õ����ѵı��
		if(er.getClickCount()==2){
			//�õ����ѵı��
			String friendNo=((JLabel)er.getSource()).getText();
			//System.out.println("��ϣ����"+friendNo+"����");
			QqChat qqChat=new QqChat(owner,friendNo);
			
			//�����������뵽������
			ManageQqChat.addQqChat(this.owner+" "+friendNo, qqChat);
			//Thread t=new Thread(qqChat);
			//t.start();
		}
	}

	@Override
	public void mouseEntered(MouseEvent er) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)er.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent er) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)er.getSource();
		jl.setForeground(Color.black);
	}

	@Override
	public void mousePressed(MouseEvent er) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent er) {
		// TODO Auto-generated method stub
		
	}

}
