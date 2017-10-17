/**
 * <p>Title: ������ʾ</p>
 * <p>Description: ѡ��ͬ��ѡ�����ʾ��ͬ��ͼƬ</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: CheckBoxDemo.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class CheckBoxDemo extends JPanel
                          implements ItemListener {
    JCheckBox chinButton;
    JCheckBox glassesButton;
    JCheckBox hairButton;
    JCheckBox teethButton;

    /*
     * ���ĸ����У��ֱ��Ӧ�°͡��۾���ͷ��������
     * ͼƬ����ƴ�����ģ����Ǹ��ݼ���ѡ��ƴдͼƬ�ļ���
     * ͼƬ�ļ����Ķ����ʽΪ"geek-XXXX.gif"
     * ���� XXXX ���ݼ��еĲ�ͬѡ�񣬶���ͬ�����ĸ�ʽ���£�

       ----             //û��ѡ��

       c---             //һ��ѡ��
       -g--
       --h-
       ---t

       cg--             //����ѡ��
       c-h-
       c--t
       -gh-
       -g-t
       --ht

       -ght             //����ѡ��
       c-ht
       cg-t
       cgh-

       cght             //���ж�ѡ
     */

    StringBuffer choices;
    JLabel pictureLabel;

    public CheckBoxDemo() {
        super(new BorderLayout());

        //��������
        chinButton = new JCheckBox("�°�(c)");
        chinButton.setMnemonic(KeyEvent.VK_C);
        chinButton.setSelected(true);

        glassesButton = new JCheckBox("�۾�(g)");
        glassesButton.setMnemonic(KeyEvent.VK_G);
        glassesButton.setSelected(true);

        hairButton = new JCheckBox("ͷ��(h)");
        hairButton.setMnemonic(KeyEvent.VK_H);
        hairButton.setSelected(true);

        teethButton = new JCheckBox("����(t)");
        teethButton.setMnemonic(KeyEvent.VK_T);
        teethButton.setSelected(true);

        //��������Ӽ���
        chinButton.addItemListener(this);
        glassesButton.addItemListener(this);
        hairButton.addItemListener(this);
        teethButton.addItemListener(this);

        choices = new StringBuffer("cght");

        //����һ����ͼƬ�ı�ǩ
        pictureLabel = new JLabel();
        pictureLabel.setFont(pictureLabel.getFont().deriveFont(Font.ITALIC));
        updatePicture();

        //�����з��õ������
        JPanel checkPanel = new JPanel(new GridLayout(0, 1));
        checkPanel.add(chinButton);
        checkPanel.add(glassesButton);
        checkPanel.add(hairButton);
        checkPanel.add(teethButton);

        add(checkPanel, BorderLayout.LINE_START);
        add(pictureLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }
/**
 *<br>����˵�������������¼���ƴ��ͼƬ���ļ���XXXX����
 *<br>���������
 *<br>�������ͣ�
 */
    public void itemStateChanged(ItemEvent e) {
        int index = 0;
        char c = '-';
        Object source = e.getItemSelectable();

        if (source == chinButton) {
            index = 0;
            c = 'c';
        } else if (source == glassesButton) {
            index = 1;
            c = 'g';
        } else if (source == hairButton) {
            index = 2;
            c = 'h';
        } else if (source == teethButton) {
            index = 3;
            c = 't';
        }
        
        //ȡ��ѡ���¼�
        if (e.getStateChange() == ItemEvent.DESELECTED) {
            c = '-';
        }

        //�ı��ļ�����XXXX
        choices.setCharAt(index, c);

        updatePicture();
    }
/**
 *<br>����˵��������ͼƬ
 *<br>���������
 *<br>�������ͣ�
 */
    protected void updatePicture() {
        //���õ���ͼƬ�Ƴ�ͼ��
        ImageIcon icon = createImageIcon(
                                    "images/geek/geek-"
                                    + choices.toString()
                                    + ".gif");
        pictureLabel.setIcon(icon);
        pictureLabel.setToolTipText(choices.toString());
        if (icon == null) {
            pictureLabel.setText("û�з���ͼƬ");
        } else {
            pictureLabel.setText(null);
        }
    }
/**
 *<br>����˵������ȡͼ��
 *<br>���������String path ͼƬ·��
 *<br>�������ͣ�ImageIcon����
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CheckBoxDemo.class.getResource(path);
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
    public static void main(String s[]) {
         JFrame.setDefaultLookAndFeelDecorated(true);

        //����һ�����壬
        JFrame frame = new JFrame("CheckBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //����һ�����
        JComponent newContentPane = new CheckBoxDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //��ʾ����
        frame.pack();
        frame.setVisible(true);
    }
}
