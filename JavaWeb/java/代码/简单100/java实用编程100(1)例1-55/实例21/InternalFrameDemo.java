import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
/**
 * <p>Title: �ڲ�������ʾ</p>
 * <p>Description: ������ʾһ���ڲ����塣����ѡ�����ĵ����˵���ͣ�������ڲ����塣</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: </p>
 * @author �Ž�
 * @version 1.0
 */
public class InternalFrameDemo extends JFrame
                               implements ActionListener {
    JDesktopPane desktop;
/**
 *<br>����˵��������������Ӵ����Ա
 *<br>���������
 *<br>�������ͣ�
 */
    public InternalFrameDemo() {
        super("InternalFrameDemo");

        //����������һ���Ƚϴ�Ĵ��壬������ֻ����50px�ı߽�
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                  screenSize.width  - inset*2,
                  screenSize.height - inset*2);
        
        //�������
        desktop = new JDesktopPane(); //�������
        createFrame(); //������һ���ڲ�����
        setContentPane(desktop);//��������ӵ�����
        setJMenuBar(createMenuBar());

        //������קģʽ
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }
/**
 *<br>����˵�����齨�˵�
 *<br>���������
 *<br>�������ͣ�
 */
    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        //����һ���˵�
        JMenu menu = new JMenu("�ļ�");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        //���塰���ĵ����˵�
        JMenuItem menuItem = new JMenuItem("���ĵ�");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("new");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        //���塰�˳����˵�
        menuItem = new JMenuItem("�˳�");
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menuBar;
    }
/**
 *<br>����˵�����¼���������ѡ��Ĳ˵�������ӳ
 *<br>���������ActionEvent e �¼�
 *<br>�������ͣ�
 */
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) { //���ĵ�
            createFrame();
        } else { //�˳�
            quit();
        }
    }
/**
 *<br>����˵��������MyInternalFrame�ഴ���µ��ڲ����壬
 *<br>���������
 *<br>�������ͣ�
 */
    protected void createFrame() {
        MyInternalFrame frame = new MyInternalFrame();
        
        frame.setVisible(true); 
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }
/**
 *<br>����˵�����˳�����
 *<br>���������
 *<br>�������ͣ�
 */
    protected void quit() {
        System.exit(0);
    }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        //����һ���ڲ�����
        InternalFrameDemo frame = new InternalFrameDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //��ʾ����
        frame.setVisible(true);
    }
}
