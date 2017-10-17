import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.border.Border;

/**
 * <p>Title: �Ի�����ʾ</p>
 * <p>Description: ȫ�����ʾ�������͵ĶԻ����ʹ��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: DialogDemo.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class DialogDemo extends JPanel {
    JLabel label;
    ImageIcon icon = createImageIcon("images/middle.gif");
    JFrame frame;
    String simpleDialogDesc = "�򵥵���Ϣ��ʾ�Ի���";
    String iconDesc = "����ͼ��ĶԻ���";
    String moreDialogDesc = "������Ϣ�Ի���";
    CustomDialog customDialog;
/**
 *<br>����˵����������������һ�������ӵ�JFrame��
 *<br>���������
 *<br>�������ͣ�
 */
    public DialogDemo(JFrame frame) {
        super(new BorderLayout());
        this.frame = frame;
        customDialog = new CustomDialog(frame, "tom", this);
        customDialog.pack();

        //������Ա
        JPanel frequentPanel = createSimpleDialogBox();
        JPanel featurePanel = createFeatureDialogBox();
        JPanel iconPanel = createIconDialogBox();
        label = new JLabel("���\"��ʾ\" ��ť"
                           + " ��ʾһ��ѡ��ĶԻ���",
                           JLabel.CENTER);

        //���ö���
        Border padding = BorderFactory.createEmptyBorder(20,20,5,20);
        frequentPanel.setBorder(padding);
        featurePanel.setBorder(padding);
        iconPanel.setBorder(padding);
        //����ѡ�
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("�򵥶Ի���", null,
                          frequentPanel,
                          simpleDialogDesc); 
        tabbedPane.addTab("���ӶԻ���", null,
                          featurePanel,
                          moreDialogDesc);
        tabbedPane.addTab("ͼ��Ի���", null,
                          iconPanel,
                          iconDesc);

        add(tabbedPane, BorderLayout.CENTER);
        add(label, BorderLayout.PAGE_END);
        label.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }
/**
 *<br>����˵�������ð�ť�ϵ�����
 *<br>���������String newText ��ӵ�����
 *<br>�������ͣ�
 */
    void setLabel(String newText) {
        label.setText(newText);
    }
/**
 *<br>����˵������ȡͼƬ
 *<br>���������String path ͼƬ����·��������
 *<br>�������ͣ�ImageIcon ͼƬ����
 */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = DialogDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
/**
 *<br>����˵��������һ��JPanel������һ��ѡ�
 *<br>���������
 *<br>�������ͣ�
 */
    private JPanel createSimpleDialogBox() {
        final int numButtons = 4;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        JButton showItButton = null;

        final String defaultMessageCommand = "default";
        final String yesNoCommand = "yesno";
        final String yeahNahCommand = "yeahnah";
        final String yncCommand = "ync";
        //��ӵ�ѡ������
        radioButtons[0] = new JRadioButton("ֻ�С�OK����ť");
        radioButtons[0].setActionCommand(defaultMessageCommand);

        radioButtons[1] = new JRadioButton("�С�Yes/No��������ť");
        radioButtons[1].setActionCommand(yesNoCommand);

        radioButtons[2] = new JRadioButton("�С�Yes/No��������ť "
                      + "(�����������)");
        radioButtons[2].setActionCommand(yeahNahCommand);

        radioButtons[3] = new JRadioButton("�С�Yes/No/Cancel��������ť "
                           + "(�����������)");
        radioButtons[3].setActionCommand(yncCommand);
        //���ĸ���ѡ���һ��Ⱥ
        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        //���õ�һ��ΪĬ��ѡ��
        radioButtons[0].setSelected(true);
        //���塰��ʾ����ť
        showItButton = new JButton("��ʾ");
        //������ʾ����ť��Ӽ���
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                //ok�Ի���
                if (command == defaultMessageCommand) {
                    JOptionPane.showMessageDialog(frame,
                                "��������������ɫ�ģ�");

                //yes/no �Ի���
                } else if (command == yesNoCommand) {
                    int n = JOptionPane.showConfirmDialog(
                            frame, "��ϲ�����������?",
                            "һ�������ĵ����⣡��",
                            JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {//ѡ��yes
                        setLabel("�ۣ���Ҳ�ǣ�");
                    } else if (n == JOptionPane.NO_OPTION) {//ѡ��no
                        setLabel("������ϲ���ԣ�");
                    } else {
                        setLabel("������Ұɣ�");
                    }

                //yes/no (�Լ�����ѡ��)
                } else if (command == yeahNahCommand) {
                    Object[] options = {"�ǵ�", "��ϲ��"};
                    int n = JOptionPane.showOptionDialog(frame,
                                    "��ϲ���������?",
                                    "��һ�����ĵ����⣡",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0]);
                    if (n == JOptionPane.YES_OPTION) {
                        setLabel("����˵İɣ���Ҳϲ����");
                    } else if (n == JOptionPane.NO_OPTION) {
                        setLabel("��ʵ��Ҳ��ϲ����");
                    } else {
                        setLabel("�ⶼ���ϸ����ң�С����");
                    }

                //yes/no/cancel �Ի���
                } else if (command == yncCommand) {
                    Object[] options = {"�ǵģ�������һ�ݡ�",
                                        "����лл��",
                                        "������Ҫˮ���㣡"};
                    //����Ի���
                    int n = JOptionPane.showOptionDialog(frame,
                                    "��������������������������㣬����Ҫ��",
                                    "�����������⡣",
                                    JOptionPane.YES_NO_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[2]);
                    if (n == JOptionPane.YES_OPTION) {
                        setLabel("��Ҫ����������ˣ�");
                    } else if (n == JOptionPane.NO_OPTION) {
                        setLabel("�õģ�����Ҫ�����ġ�");
                    } else if (n == JOptionPane.CANCEL_OPTION) {
                        setLabel("�õģ����Ǹ�����ˮ���㣡");
                    } else {
                        setLabel("�Բ����㻹û�е���أ�");
                    }
                }
                return;
            }
        });

        return createPane(simpleDialogDesc + ":",
                          radioButtons,
                          showItButton);
    }
/**
 *<br>����˵�����ṩ��createSimpleDialogBox��createFeatureDialogBox����
 *<br>����˵������������ʾ��Ϣ��һ�е�ѡ��͡���ʾ����ť
 *<br>���������String description ��ʾ������Ϣ
 *<br>���������JRadioButton[] radioButtons ��ѡ����
 *<br>���������JButton showButton ����ʾ����ť
 *<br>�������ͣ�JPanel ��Ӻõ����
 */
    private JPanel createPane(String description,
                              JRadioButton[] radioButtons,
                              JButton showButton) {

        int numChoices = radioButtons.length;
        JPanel box = new JPanel();
        JLabel label = new JLabel(description);

        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);
        //���radio
        for (int i = 0; i < numChoices; i++) {
            box.add(radioButtons[i]);
        }

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(showButton, BorderLayout.PAGE_END);
        return pane;
    }
/**
 *<br>����˵�����ṩ��createSimpleDialogBox��createFeatureDialogBox����
 *<br>����˵������������ʾ��Ϣ�����е�ѡ��͡���ʾ����ť
 *<br>���������String description ��ʾ������Ϣ
 *<br>���������JRadioButton[] radioButtons ��ѡ����
 *<br>���������JButton showButton ����ʾ����ť
 *<br>�������ͣ�JPanel ��Ӻõ����
 */
     private JPanel create2ColPane(String description,
                                  JRadioButton[] radioButtons,
                                  JButton showButton) {
        JLabel label = new JLabel(description);
        int numPerColumn = radioButtons.length/2;

        JPanel grid = new JPanel(new GridLayout(0, 2));
        for (int i = 0; i < numPerColumn; i++) {
            grid.add(radioButtons[i]);
            grid.add(radioButtons[i + numPerColumn]);
        }

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.PAGE_AXIS));
        box.add(label);
        grid.setAlignmentX(0.0f);
        box.add(grid);

        JPanel pane = new JPanel(new BorderLayout());
        pane.add(box, BorderLayout.PAGE_START);
        pane.add(showButton, BorderLayout.PAGE_END);

        return pane;
    }
/**
 *<br>����˵��������������ѡ������
 *<br>����˵�������ﶼ��ʵ��showMessageDialog�࣬����Ҳ����ָ��ͼ��
 *<br>���������
 *<br>�������ͣ�JPanel ����õ����
 */

    private JPanel createIconDialogBox() {
        JButton showItButton = null;

        final int numButtons = 6;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        final String plainCommand = "plain";
        final String infoCommand = "info";
        final String questionCommand = "question";
        final String errorCommand = "error";
        final String warningCommand = "warning";
        final String customCommand = "custom";

        radioButtons[0] = new JRadioButton("��ͨ��û��ͼ�꣩");
        radioButtons[0].setActionCommand(plainCommand);

        radioButtons[1] = new JRadioButton("��Ϣͼ��");
        radioButtons[1].setActionCommand(infoCommand);

        radioButtons[2] = new JRadioButton("����ͼ��");
        radioButtons[2].setActionCommand(questionCommand);

        radioButtons[3] = new JRadioButton("����ͼ��");
        radioButtons[3].setActionCommand(errorCommand);

        radioButtons[4] = new JRadioButton("����ͼ��");
        radioButtons[4].setActionCommand(warningCommand);

        radioButtons[5] = new JRadioButton("�Զ���ͼ��");
        radioButtons[5].setActionCommand(customCommand);

        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        radioButtons[0].setSelected(true);

        showItButton = new JButton("��ʾ");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                //û��ͼ��
                if (command == plainCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "ˮ�����ﲻҪ����ˣ�",
                                    "��ͼ��",
                                    JOptionPane.PLAIN_MESSAGE);
                //��Ϣͼ��
                } else if (command == infoCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "ˮ�����ﲻҪ����ˣ�",
                                    "��Ϣͼ��",
                                    JOptionPane.INFORMATION_MESSAGE);

                //����ͼ��
                } else if (command == questionCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "����Է�ǰϴ�֣�����",
                                    "����",
                                    JOptionPane.QUESTION_MESSAGE);
                //����ͼ��
                } else if (command == errorCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "�Բ���������ÿ�û���ʽ��ˣ�",
                                    "������Ϣ",
                                    JOptionPane.ERROR_MESSAGE);
                //����ͼ��
                } else if (command == warningCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "���棡������͸֧���ÿ����뾡�첹���",
                                    "������Ϣ",
                                    JOptionPane.WARNING_MESSAGE);
                //�Զ���ͼ��
                } else if (command == customCommand) {
                    JOptionPane.showMessageDialog(frame,
                                    "������������ʲôͼ�궼���ԣ�",
                                    "�Զ���Ի���",
                                    JOptionPane.INFORMATION_MESSAGE,
                                    icon);
                }
            }
        });

        return create2ColPane(iconDesc + ":",
                              radioButtons,
                              showItButton);
    }
/**
 *<br>����˵��������һ��JPanel�����ڵڶ���ѡ���
 *<br>���������
 *<br>�������ͣ�
 */
    private JPanel createFeatureDialogBox() {
        final int numButtons = 5;
        JRadioButton[] radioButtons = new JRadioButton[numButtons];
        final ButtonGroup group = new ButtonGroup();

        JButton showItButton = null;
        //�����������
        final String pickOneCommand = "pickone";
        final String textEnteredCommand = "textfield";
        final String nonAutoCommand = "nonautooption";
        final String customOptionCommand = "customoption";
        final String nonModalCommand = "nonmodal";
        //����radio����
        radioButtons[0] = new JRadioButton("ѡ��һ��");
        radioButtons[0].setActionCommand(pickOneCommand);

        radioButtons[1] = new JRadioButton("������Ϣ");
        radioButtons[1].setActionCommand(textEnteredCommand);

        radioButtons[2] = new JRadioButton("�رհ�ť��Ч");
        radioButtons[2].setActionCommand(nonAutoCommand);

        radioButtons[3] = new JRadioButton("����У��"
                                           + "(�û�������Ϣ)");
        radioButtons[3].setActionCommand(customOptionCommand);

        radioButtons[4] = new JRadioButton("û��ģʽ");
        radioButtons[4].setActionCommand(nonModalCommand);
        //�ϳ�һ����Ⱥ
        for (int i = 0; i < numButtons; i++) {
            group.add(radioButtons[i]);
        }
        //���õ�һ��ΪĬ��ѡ��
        radioButtons[0].setSelected(true);

        showItButton = new JButton("��ʾ");
        showItButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String command = group.getSelection().getActionCommand();

                //ѡ��һ��
                if (command == pickOneCommand) {
                    Object[] possibilities = {"����", "������", "���"};
                    //���öԻ���
                    String s = (String)JOptionPane.showInputDialog(
                                        frame,    //��������
                                        "��ѡ����Ŀ��\n"
                                        + "\"������\"",  //�����Ϣ
                                        "�ͻ�ѡ��",
                                        JOptionPane.PLAIN_MESSAGE,  //�Ի���ģʽ
                                        icon,           //��ʾͼ��
                                        possibilities,   //ѡ������
                                        "����");    //Ĭ��ѡ��

                    //�����ѡ��
                    if ((s != null) && (s.length() > 0)) {
                        setLabel("������" + s + "!");
                        return;
                    }

                    //����ͻ�û��ѡ��
                    setLabel("��㣡");

                //�ı�����
                } else if (command == textEnteredCommand) {
                    String s = (String)JOptionPane.showInputDialog(
                                        frame,
                                        "ѡ��һ������\n"
                                        + "\"������\"",
                                        "�ͻ�����",
                                        JOptionPane.PLAIN_MESSAGE,
                                        icon,
                                        null,
                                        "����");

                    //����û�������
                    if ((s != null) && (s.length() > 0)) {
                        setLabel("��Ҫ���Ǽ�����" + s + "��");
                        return;
                    }

                    //������ص��ǿջ�����null��
                    setLabel("��Щѡ��");

                //�رհ�ť��Ч
                } else if (command == nonAutoCommand) {
                    //����һ���Ի������
                    final JOptionPane optionPane = new JOptionPane(
                                    "�ر�����Ի���\n"
                                    + "��������İ�ť\n"
                                    + "������",
                                    JOptionPane.QUESTION_MESSAGE,
                                    JOptionPane.YES_NO_OPTION);

                    JDialog.setDefaultLookAndFeelDecorated(false);
                    //����һ���Ի���
                    final JDialog dialog = new JDialog(frame,
                                                 "���һ����ť",
                                                 true);
                    //���Ի��������ӵ��Ի�����
                    dialog.setContentPane(optionPane);
                    //���öԻ���ر�ʱ�Ĳ���ģʽ
                    dialog.setDefaultCloseOperation(
                        JDialog.DO_NOTHING_ON_CLOSE);
                    dialog.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent we) { //������رհ�ť
                            setLabel("�谭�û���ͼ�رմ��壡");
                        }
                    });
                    
                    JDialog.setDefaultLookAndFeelDecorated(true);
                    
                    optionPane.addPropertyChangeListener(
                        new PropertyChangeListener() {
                            public void propertyChange(PropertyChangeEvent e) {
                                String prop = e.getPropertyName();

                                if (dialog.isVisible()
                                 && (e.getSource() == optionPane)
                                 && (JOptionPane.VALUE_PROPERTY.equals(prop))) {
                                    //�����Ҫ��ֹ�رհ�ť��������������д���
                                    
                                    dialog.setVisible(false);
                                }
                            }
                        });
                    dialog.pack();
                    dialog.setLocationRelativeTo(frame);
                    dialog.setVisible(true);
                    
                    int value = ((Integer)optionPane.getValue()).intValue();
                    if (value == JOptionPane.YES_OPTION) {
                        setLabel("�õ�");
                    } else if (value == JOptionPane.NO_OPTION) {
                        setLabel("��ͼ����رհ�ť���ر�һ�����ܹرյĶԻ���"
                                 + "�㲻�ܣ�");
                    } else {
                        setLabel("�������ʹ��ESC���رա�");
                    }

                 //�Լ��������
                } else if (command == customOptionCommand) {
                    customDialog.setLocationRelativeTo(frame);
                    customDialog.setVisible(true);

                    String s = customDialog.getValidatedText();
                    if (s != null) {
                        //The text is valid.
                        setLabel("��ӭ�㣡"
                                 + "���Ѿ�������\""
                                 + s
                                 + "\"��");
                    }

                //û��ģʽ
                } else if (command == nonModalCommand) {
                    //����һ���Ի���
                    final JDialog dialog = new JDialog(frame,
                                                       "һ��û��ģʽ�ĶԻ���");
                    //ʹ��html��������ʾ��Ϣ
                    JLabel label = new JLabel("<html><p align=center>"
                        + "����һ��û��ģʽ�ĶԻ���<br>"
                        + "�����ʹ�ø���ĸ�ʽ<br>"
                        + "��������ʹ�������壡");
                    label.setHorizontalAlignment(JLabel.CENTER);
                    Font font = label.getFont();
                    
                    label.setFont(label.getFont().deriveFont(font.PLAIN,
                                                             14.0f));

                    JButton closeButton = new JButton("�ر�");
                    closeButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dialog.setVisible(false);
                            dialog.dispose();
                        }
                    });
                    JPanel closePanel = new JPanel();
                    closePanel.setLayout(new BoxLayout(closePanel,
                                                       BoxLayout.LINE_AXIS));
                    closePanel.add(Box.createHorizontalGlue());
                    closePanel.add(closeButton);
                    closePanel.setBorder(BorderFactory.
                        createEmptyBorder(0,0,5,5));

                    JPanel contentPane = new JPanel(new BorderLayout());
                    contentPane.add(label, BorderLayout.CENTER);
                    contentPane.add(closePanel, BorderLayout.PAGE_END);
                    contentPane.setOpaque(true);
                    dialog.setContentPane(contentPane);

                    //��ʾ����
                    dialog.setSize(new Dimension(300, 150));
                    dialog.setLocationRelativeTo(frame);
                    dialog.setVisible(true);
                }
            }
        });

        return createPane(moreDialogDesc + ":",
                          radioButtons,
                          showItButton);
    }

    public static void main(String[] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        //����������һ������
        JFrame frame = new JFrame("DialogDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //����һ�����
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new GridLayout(1,1));
        contentPane.add(new DialogDemo(frame));

        //��ʾ����
        frame.pack();
        frame.setVisible(true);
    }
}
