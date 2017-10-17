/**
 * <p>Title: ��ɫѡ����</p>
 * <p>Description: ��ʾһ����ɫѡ���������Դ�������ѡ�񣬿���ʹ��HSBģʽ��RGBģʽ</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ColorChooserDemo.java</p>
 * @author �Ž�
 * @version 1.0
 */

public class ColorChooserDemo extends JPanel
                              implements ChangeListener {

    protected JColorChooser tcc;
    protected JLabel banner;

    public ColorChooserDemo() {
        super(new BorderLayout());

        //����һ����ǩ�������ġ�Ҳ������ʾ�û�ѡ�����ɫ��
        banner = new JLabel("��ӭʹ����ɫѡ������",
                            JLabel.CENTER);
        banner.setForeground(Color.yellow);
        banner.setBackground(Color.blue);
        banner.setOpaque(true);
        banner.setFont(new Font("SansSerif", Font.BOLD, 24));
        banner.setPreferredSize(new Dimension(100, 65));

        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.add(banner, BorderLayout.CENTER);
        bannerPanel.setBorder(BorderFactory.createTitledBorder("���"));

        //����ѡ����ɫѡ����
        tcc = new JColorChooser(banner.getForeground());//���ó�ʼ��ɫ
        tcc.getSelectionModel().addChangeListener(this);//������ģʽ��Ӽ���
        tcc.setBorder(BorderFactory.createTitledBorder("ѡ����ɫ"));

        add(bannerPanel, BorderLayout.CENTER);
        add(tcc, BorderLayout.PAGE_END);
    }
/**
 *<br>����˵�����¼��������û�ѡ����ɫ����
 *<br>���������ChangeEvent e �û�ѡ���¼�
 *<br>�������ͣ�
 */
    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();//��ȡ�û�ѡ�����ɫ
        banner.setForeground(newColor);
    }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        //��������
        JFrame frame = new JFrame("ColorChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //�����������
        JComponent newContentPane = new ColorChooserDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //��ʾ����
        frame.pack();
        frame.setVisible(true);
    }
}
