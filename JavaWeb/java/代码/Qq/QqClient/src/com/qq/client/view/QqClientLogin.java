/*
 * ���ܣ�qq�ͻ��˵�½����
 */
package com.qq.client.view;

import com.qq.client.model.QqClientUser;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqFriendList;
import com.qq.common.*;
import com.qq.common.User;
public class QqClientLogin extends JFrame implements ActionListener{

	//���山����Ҫ�����
	
	JLabel jbl1;
	
	//�����в���Ҫ�����
	//�в�������JPanel����һ����ѡ����ڹ���
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jbl;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	
	//�����ϲ���Ҫ�����
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqClientLogin qqClientLogin=new QqClientLogin();
	}
	public QqClientLogin(){
		//������
		jbl1=new JLabel(new ImageIcon("image/tou.gif"));
		
		//�����в�
		jp2=new JPanel(new GridLayout(3,3));
		
		jp2_jbl1=new JLabel("QQ����",JLabel.CENTER);
		jp2_jbl2=new JLabel("QQ����",JLabel.CENTER);
		jp2_jbl3=new JLabel("��������",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4=new JLabel("�������뱣��",JLabel.CENTER);
		jp2_jbl=new JButton("�������");
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("�����½");
		jp2_jcb2=new JCheckBox("��ס����");
		
		//�ѿؼ���˳�����jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jbl);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		//����ѡ�����
		jtp=new JTabbedPane();
		jtp.add("QQ����",jp2);
		jp3=new JPanel();
		jtp.add("�ֻ�����",jp3);
		jp4=new JPanel();
		jtp.add("�����ʼ�",jp4);
		
		//�����ϲ�
		jp1=new JPanel();
		jp1_jb1=new JButton(new ImageIcon("image/denglu.gif"));
		//��Ӧ�û������¼
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("image/xiangdao.gif"));
		
		jp1_jb3=new JButton("ȡ��");

		//��������ť�ŵ�jpl
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		
		this.add(jbl1,"North");
		this.add(jtp,"Center");
		//��jp1�ŵ��ϲ�
		this.add(jp1,"South");
		this.setTitle("ɽկQQ2010");
		this.setIconImage((new ImageIcon("image/jie.jpg").getImage()));
		this.setSize(350,240);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//����û������¼
		if(e.getSource()==jp1_jb1){
			QqClientUser qqClientUser=new QqClientUser();
			User u=new User();
			u.setUserId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			
			if(qqClientUser.checkUser(u)){
				try {
					
					//���������б�
					QqFriendList qqList=new QqFriendList(u.getUserId());
					ManageQqFriendList.addQqFriendList(u.getUserId(), qqList);
					
					//����һ��Ҫ�󷵻����ߺ��ѵ������
					ObjectOutputStream oos=new  ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
					
					//��һ��Message
					Message m=new Message();
					m.setMesType(MessageType.message_get_onLineFriend);
					//ָ����ø�QQ�ŵĺ��������б�
					m.setSender(u.getUserId());
					oos.writeObject(m);
				} catch (Exception e2) {}
				
			
				//�رյ�¼����
				this.dispose();
			}else{
				JOptionPane.showMessageDialog(this, "�û������������");
			}
		}
	}

}
