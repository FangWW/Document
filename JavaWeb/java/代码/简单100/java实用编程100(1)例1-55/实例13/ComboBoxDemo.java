import java.text.SimpleDateFormat;
/**
 * <p>Title: ComboBox��������ʾ</p>
 * <p>Description: ͨ��ѡ���������һ�����ڸ�ʽ����ʽ�����������</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ComboBoxDemo.java</p>
 * @author �Ž�
 * @version 1.0
 */

public class ComboBoxDemo extends JPanel
                           implements ActionListener {
    static JFrame frame;
    JLabel result;
    String currentPattern;
/**
 *<br>����˵��������������ʼ�����幹��
 *<br>���������
 *<br>�������ͣ�
 */
    public ComboBoxDemo() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        String[] patternExamples = {
                 "dd MMMMM yyyy",
                 "dd.MM.yy",
                 "MM/dd/yy",
                 "yyyy.MM.dd G 'at' hh:mm:ss z",
                 "EEE, MMM d, ''yy",
                 "h:mm a",
                 "H:mm:ss:SSS",
                 "K:mm a,z",
                 "yyyy.MMMMM.dd GGG hh:mm aaa"
                 };

        currentPattern = patternExamples[0];

        //����һ���淶���û�����
        JLabel patternLabel1 = new JLabel("����һ���ַ���ʽ����");
        JLabel patternLabel2 = new JLabel("�������б���ѡ��һ�֣�");

        JComboBox patternList = new JComboBox(patternExamples);
        patternList.setEditable(true);//��ע����ComboBox�ɽ��б༭
        patternList.addActionListener(this);

        //����һ����ʾ����û�����
        JLabel resultLabel = new JLabel("��ǰ ����/ʱ��",
                                        JLabel.LEADING);//�൱��LEFT
        result = new JLabel(" ");
        result.setForeground(Color.black);
        result.setBorder(BorderFactory.createCompoundBorder(
             BorderFactory.createLineBorder(Color.black),
             BorderFactory.createEmptyBorder(5,5,5,5)
        ));

        //���ù���
        JPanel patternPanel = new JPanel();
        patternPanel.setLayout(new BoxLayout(patternPanel,
                               BoxLayout.PAGE_AXIS));
        patternPanel.add(patternLabel1);
        patternPanel.add(patternLabel2);
        patternList.setAlignmentX(Component.LEFT_ALIGNMENT);
        patternPanel.add(patternList);

        JPanel resultPanel = new JPanel(new GridLayout(0, 1));
        resultPanel.add(resultLabel);
        resultPanel.add(result);

        patternPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        resultPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        add(patternPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(resultPanel);

        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        reformat();
    } 
/**
 *<br>����˵�����¼�����
 *<br>���������
 *<br>�������ͣ�
 */
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String newSelection = (String)cb.getSelectedItem();
        currentPattern = newSelection;
        reformat();
    }
/**
 *<br>����˵������ʽ����ʾ���������
 *<br>���������
 *<br>�������ͣ�
 */
    public void reformat() {
        Date today = new Date();
        SimpleDateFormat formatter =
           new SimpleDateFormat(currentPattern);
        try {
            String dateString = formatter.format(today);
            result.setForeground(Color.black);
            result.setText(dateString);
        } catch (IllegalArgumentException iae) {
            result.setForeground(Color.red);
            result.setText("Error: " + iae.getMessage());
        }
    }
/**
 *<br>����˵����������
 *<br>���������
 *<br>�������ͣ�
 */
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);

        //����һ������
        frame = new JFrame("ComboBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //����һ���������
        JComponent newContentPane = new ComboBoxDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //��ʾ����
        frame.pack();
        frame.setVisible(true);
    }
}
