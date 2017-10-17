import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <p>Title: ��ť��ʾ</p>
 * <p>Description: �ṩһ����ť����ʾ�����ʵ�ְ�ť����һ����ťʧЧ</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author �Ž�
 * @version 1.0
 */
public class ButtonDemo extends JPanel
                        implements ActionListener {
    protected JButton b1, b2, b3;
/**
 *<br>����˵��������������ʼͼ�ν��湹��
 *<br>���������
 *<br>�������ͣ�
 */
    public ButtonDemo() {
        ImageIcon leftButtonIcon = createImageIcon("images/right.gif");
        ImageIcon middleButtonIcon = createImageIcon("images/middle.gif");
        ImageIcon rightButtonIcon = createImageIcon("images/left.gif");

        b1 = new JButton("ʧЧ�м䰴ť(D)", leftButtonIcon);
        b1.setVerticalTextPosition(AbstractButton.CENTER);//ˮƽ�м����
        b1.setHorizontalTextPosition(AbstractButton.LEADING);//�൱��LEFT
        b1.setMnemonic(KeyEvent.VK_D);//��b1�alt+D��
        b1.setActionCommand("disable");

        b2 = new JButton("M�м䰴ť", middleButtonIcon);
        b2.setVerticalTextPosition(AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition(AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_M);//��b2�alt+M��

        b3 = new JButton("E�����м䰴ť", rightButtonIcon);
        b3.setMnemonic(KeyEvent.VK_E);//��b3�alt+E��
        b3.setActionCommand("enable");
        b3.setEnabled(false);

        //��1��3����¼�����
        b1.addActionListener(this);
        b3.addActionListener(this);
        //���ð�ť��ʾ�ı�
        b1.setToolTipText("��������ť����ʹ�м�İ�ťʧЧ��");
        b2.setToolTipText("��������ť��û���κε��¼�������");
        b3.setToolTipText("��������ť����ʹ�м�İ�ť��Ч");

        //����ť��ӵ�JPanel��
        add(b1);
        add(b2);
        add(b3);
    }
/**
 *<br>����˵�����¼�����
 *<br>���������
 *<br>�������ͣ�
 */
    public void actionPerformed(ActionEvent e) {
        if ("disable".equals(e.getActionCommand())) {
            b2.setEnabled(false);
            b1.setEnabled(false);
            b3.setEnabled(true);
        } else {
            b2.setEnabled(true);
            b1.setEnabled(true);
            b3.setEnabled(false);
        }
    }
/**
 *<br>����˵��������ͼ�꣬
 *<br>���������String path ͼ�����ڵ�·��
 *<br>�������ͣ�ImageIcon ͼ�����
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = ButtonDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
    public static void main(String[] args) {
        //����ʹ���µ�swing����
        JFrame.setDefaultLookAndFeelDecorated(true);

        //����һ������
        JFrame frame = new JFrame("ButtonDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //����һ�����
        ButtonDemo newContentPane = new ButtonDemo();
        newContentPane.setOpaque(true); 
        frame.setContentPane(newContentPane);

        //��ʾ����
        frame.pack();
        frame.setVisible(true);
    }
}
