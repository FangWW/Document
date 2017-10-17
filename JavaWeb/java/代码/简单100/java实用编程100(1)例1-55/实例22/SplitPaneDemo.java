/**
 * <p>Title: �ָ����</p>
 * <p>Description: ��ʾ�����ָ������������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename:SplitPaneDemo.java </p>
 * @author �Ž�
 * @version 1.0
 */
public class SplitPaneDemo implements ListSelectionListener {
    private String[] imageNames={"Bird.gif","Cat.gif","Dog.gif","Pig.gif"};
    private JLabel picture;
    private JList list;
    private JSplitPane splitPane;
/**
 *<br>����˵����������������������Ҫʹ�õĹ���
 *<br>���������
 *<br>�������ͣ�
 */
    public SplitPaneDemo() {
        
        //����һ��ͼ�����Ƶ��б�����Ϊ��ѡ��ʽ
        list = new JList(imageNames);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        JScrollPane listScrollPane = new JScrollPane(list);

        //��ȡĬ�ϵ�ͼƬ
        ImageIcon firstImage = createImageIcon("images/" +
                                     (String)imageNames[0]);
        if (firstImage != null) {
            picture = new JLabel(firstImage);
            picture.setPreferredSize(new Dimension(firstImage.getIconWidth(),
                                                   firstImage.getIconHeight()));
        } else {
            picture = new JLabel((String)imageNames[0]);
        }
        JScrollPane pictureScrollPane = new JScrollPane(picture);

        //����һ��ˮƽ�ָ����壬�����������������֡�
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true,
                                   listScrollPane, pictureScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        //����������С�ߴ�
        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        pictureScrollPane.setMinimumSize(minimumSize);

        //�����ʼ�ߴ�
        splitPane.setPreferredSize(new Dimension(400, 200));
    }
/**
 *<br>����˵�����������ָ�����
 *<br>���������
 *<br>�������ͣ�JSplitPane ����
 */
    public JSplitPane getSplitPane() {
        return splitPane;
    }
/**
 *<br>����˵�����б�����¼�����
 *<br>���������ListSelectionEvent e �б�ѡ���¼�
 *<br>�������ͣ�
 */
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting())
            return;

        JList theList = (JList)e.getSource();
        if (theList.isSelectionEmpty()) {
            picture.setIcon(null);
            picture.setText(null);
        } else {
            int index = theList.getSelectedIndex();
            ImageIcon newImage = createImageIcon("images/" +
                                     (String)imageNames[index]);
            picture.setIcon(newImage);
            if (newImage != null) {
                picture.setText(null);
                picture.setPreferredSize(new Dimension(newImage.getIconWidth(),
                                                       newImage.getIconHeight() ));
            } else {
                picture.setText("Image not found: "
                                + (String)imageNames[index]);
            }
            picture.revalidate();
        }
    }
/**
 *<br>����˵��������·����ȡͼ�ζ���
 *<br>���������String path ͼƬ·��
 *<br>�������ͣ�ImageIcon ͼƬ����
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = SplitPaneDemo.class.getResource(path);
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
        JFrame.setDefaultLookAndFeelDecorated(true);

        //���崰��
        JFrame frame = new JFrame("SplitPaneDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SplitPaneDemo splitPaneDemo = new SplitPaneDemo();
        frame.getContentPane().add(splitPaneDemo.getSplitPane());

        //��ʾ����
        frame.pack();
        frame.setVisible(true);
    }
}
