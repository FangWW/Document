import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * <p>Title: ѡ���ʾ</p>
 * <p>Description: ������һ��ѡ���ʾ�������ͬ�Ŀ�Ƭ����ʾ�����ݲ�ͬ</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: TabbedPaneDemo.java</p>
 *
 * @author �Ž�
 * @version 1.0
 */

public class TabbedPaneDemo extends JPanel {
    public TabbedPaneDemo() {
        super(new GridLayout(1, 1));

        ImageIcon icon = createImageIcon("images/middle.gif");
        JTabbedPane tabbedPane = new JTabbedPane();

        Component panel1 = makeTextPanel("#��һ����Ƭ#");
        tabbedPane.addTab("One", icon, panel1,
                          "��һ����Ƭ��ʾ��Ϣ��");
        tabbedPane.setSelectedIndex(0);

        Component panel2 = makeTextPanel("##�ڶ�����Ƭ##");
        tabbedPane.addTab("Two", icon, panel2,
                          "�ڶ�����Ƭ��ʾ��Ϣ��");

        Component panel3 = makeTextPanel("###��������Ƭ###");
        tabbedPane.addTab("Three", icon, panel3,
                          "��������Ƭ��ʾ��Ϣ��");

        Component panel4 = makeTextPanel("####���ĸ���Ƭ####");
        tabbedPane.addTab("Four", icon, panel4,
                          "���ĸ���Ƭ��ʾ��Ϣ��");

        //��ѡ���ӵ�panl��
        add(tabbedPane);
    }
/**
 *<br>����˵���������Ϣ��ѡ���
 *<br>���������String text ��ʾ����Ϣ����
 *<br>�������ͣ�Component ��Ա����
 */
    protected Component makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
/**
 *<br>����˵�������ͼƬ
 *<br>���������String path ͼƬ��·��
 *<br>�������ͣ�ImageIcon ͼƬ����
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = TabbedPaneDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        //ʹ��Swing��������
        JFrame.setDefaultLookAndFeelDecorated(true);

        //��������
        JFrame frame = new JFrame("TabbedPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new TabbedPaneDemo(),
                                 BorderLayout.CENTER);

        //��ʾ����
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
