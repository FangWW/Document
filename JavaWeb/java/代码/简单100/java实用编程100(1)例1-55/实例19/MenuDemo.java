import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
/**
 * <p>Title: �˵���ʾ</p>
 * <p>Description: ��ʾ�˵��Ľ����Ϳ�ݼ���ʹ�á�</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: MenuDemo.java</p>
 * @author �Ž�
 * @version 1.0
 */

public class MenuDemo implements ActionListener, ItemListener {
    JTextArea output;
    JScrollPane scrollPane;
    String newline = "\n";
/**
 *<br>����˵�����齨�˵���
 *<br>���������
 *<br>�������ͣ�
 */
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

        //����˵���
        menuBar = new JMenuBar();

        //�����һ���˵�
        menu = new JMenu("(A)�˵�");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);

        //���濪ʼ����˵���
        
        //ֻ������
        menuItem = new JMenuItem("(O)ֻ���ı��Ĳ˵�",
                                 KeyEvent.VK_O);
        //���ÿ�ݼ�
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_1, ActionEvent.ALT_MASK));
        //��Ӽ���
        menuItem.addActionListener(this);
        menu.add(menuItem);
        //��ͼ�껹������
        ImageIcon icon = createImageIcon("images/middle.gif");
        menuItem = new JMenuItem("(B)��ͼ������ֵĲ˵�", icon);
        menuItem.setMnemonic(KeyEvent.VK_B);
        menuItem.addActionListener(this);
        menu.add(menuItem);
        //ֻ��ͼ��
        menuItem = new JMenuItem(icon);
        menuItem.setMnemonic(KeyEvent.VK_D);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //����һ��radio button����ѡ��ť���˵�
        menu.addSeparator();
        ButtonGroup group = new ButtonGroup();

        rbMenuItem = new JRadioButtonMenuItem("(R)ʹ��radio�Ĳ˵�");
        rbMenuItem.setSelected(true);
        rbMenuItem.setMnemonic(KeyEvent.VK_R);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        menu.add(rbMenuItem);

        rbMenuItem = new JRadioButtonMenuItem("(d)����һ��radio�˵�");
        rbMenuItem.setMnemonic(KeyEvent.VK_D);
        group.add(rbMenuItem);
        rbMenuItem.addActionListener(this);
        menu.add(rbMenuItem);

        //����һ��check box�����У��˵�
        menu.addSeparator();
        cbMenuItem = new JCheckBoxMenuItem("(C)ʹ�ü��еĲ˵�");
        cbMenuItem.setMnemonic(KeyEvent.VK_C);
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);

        cbMenuItem = new JCheckBoxMenuItem("(H)����һ������");
        cbMenuItem.setMnemonic(KeyEvent.VK_H);
        cbMenuItem.addItemListener(this);
        menu.add(cbMenuItem);

        //����һ�����Ӳ˵�
        menu.addSeparator();
        submenu = new JMenu("(S)�����Ӳ˵�");
        submenu.setMnemonic(KeyEvent.VK_S);
        //�����Ӳ˵�
        menuItem = new JMenuItem("�����Ӳ˵�");
        //�����ݼ�
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_2, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menuItem = new JMenuItem("�Ӳ˵���");
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        menu.add(submenu);

        //����ڶ����˵�
        menu = new JMenu("(N)�ڶ����˵�");
        menu.setMnemonic(KeyEvent.VK_N);
        menuBar.add(menu);

        return menuBar;
    }
/**
 *<br>����˵�����������
 *<br>���������
 *<br>�������ͣ�
 */
    public Container createContentPane() {
        //����һ�����
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //����һ���ı���
        output = new JTextArea(5, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //���ı�����ӵ������
        contentPane.add(scrollPane, BorderLayout.CENTER);

        return contentPane;
    }
/**
 *<br>����˵�������������˵�
 *<br>���������
 *<br>�������ͣ�
 */
    public void createPopupMenu() {
        JMenuItem menuItem;

        //���������˵�
        JPopupMenu popup = new JPopupMenu();
        ImageIcon openicon = createImageIcon("images/Open16.gif");
        menuItem = new JMenuItem("���ļ�",openicon);
        menuItem.addActionListener(this);
        popup.add(menuItem);
        ImageIcon saveicon = createImageIcon("images/Save16.gif");
        menuItem = new JMenuItem("�����ļ�",saveicon);
        menuItem.addActionListener(this);
        popup.add(menuItem);

        //���һ���������ı����Ա����Ҽ�ʱ��Ӧ
        MouseListener popupListener = new PopupListener(popup);
        output.addMouseListener(popupListener);
    }
/**
 *<br>����˵����������ͨ�Ĳ˵�ѡ��
 *<br>���������ActionEvent e �¼�
 *<br>�������ͣ�
 */
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "����¼���"
                   + newline
                   + "    �¼�Դ: " + source.getText()
                   + " (ѡ�����" + getClassName(source) + ")";
        output.append(s + newline);
    }
/**
 *<br>����˵�����������в˵�ѡ����
 *<br>���������ItemEvent e ���д������¼�
 *<br>�������ͣ�
 */
    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "�˵������"
                   + newline
                   + "    �¼�Դ: " + source.getText()
                   + " (ѡ����� " + getClassName(source) + ")"
                   + newline
                   + "    �µ�״̬: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "ѡ��":"��ѡ��");
        output.append(s + newline);
    }
/**
 *<br>����˵��������������
 *<br>���������
 *<br>�������ͣ�
 */
    protected String getClassName(Object o) {
        String classString = o.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }
/**
 *<br>����˵��������·������ͼƬ
 *<br>���������String path ͼƬ��·��
 *<br>�������ͣ�ImageIcon ͼƬ����
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MenuDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        //����һ������
        JFrame frame = new JFrame("MenuDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //�����˵�������ӵ������
        MenuDemo demo = new MenuDemo();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //���ɵ����˵�
        demo.createPopupMenu();

        //��ʾ����
        frame.setSize(450, 260);
        frame.setVisible(true);
    }
//�����˵�������
    class PopupListener extends MouseAdapter {
        JPopupMenu popup;

        PopupListener(JPopupMenu popupMenu) {
            popup = popupMenu;
        }
        
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }

        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }

        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(),
                           e.getX(), e.getY());
            }
        }
    }
}
