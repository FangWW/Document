import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
/**
 * <p>Title: �û��Զ���Ի���</p>
 * <p>Description: �Լ�����Ի���ķ����ʹ�öԻ������ʽ���Ӷ�����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: CustomDialog.java</p>
 * @author �Ž�
 * @version 1.0
 */
class CustomDialog extends JDialog
                   implements ActionListener,
                              PropertyChangeListener {
    private String typedText = null;
    private JTextField textField;
    private DialogDemo dd;

    private String magicWord;
    private JOptionPane optionPane;

    private String btnString1 = "ȷ��";
    private String btnString2 = "ȡ��";
/**
 *<br>����˵���������ı������ַ�
 *<br>���������
 *<br>�������ͣ�
 */
    public String getValidatedText() {
        return typedText;
    }
/**
 *<br>����˵��������һ������Ի���
 *<br>���������
 *<br>�������ͣ�
 */
    public CustomDialog(Frame aFrame, String aWord, DialogDemo parent) {
        super(aFrame, true);
        dd = parent;
        
        magicWord = aWord.toUpperCase();
        setTitle("����");

        textField = new JTextField(10);

        //������ʾ��Ϣ
        String msgString1 = "�������� jeck�����Ӣ��������";
        String msgString2 = "(������ǣ� \"" + magicWord
                              + "\"��)";
        Object[] array = {msgString1, msgString2, textField};


        Object[] options = {btnString1, btnString2};

        //�����Ի���
        optionPane = new JOptionPane(array,
                                    JOptionPane.QUESTION_MESSAGE,
                                    JOptionPane.YES_NO_OPTION,
                                    null,
                                    options,
                                    options[0]);

        //��ʾ�Ի���
        setContentPane(optionPane);

        //���õ��رմ��嶯��ģʽ
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent we) {
                
                    optionPane.setValue(new Integer(
                                        JOptionPane.CLOSED_OPTION));
            }
        });

        //ʹ���ı�������õ�����
        addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent ce) {
                textField.requestFocusInWindow();
            }
        });

        //���ı�����Ӽ����¼�
        textField.addActionListener(this);

        //��������ı�
        optionPane.addPropertyChangeListener(this);
    }

    /** �ı���������� */
    public void actionPerformed(ActionEvent e) {
        optionPane.setValue(btnString1);
    }

    /** ��������ĸı� */
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();

        if (isVisible()
         && (e.getSource() == optionPane)
         && (JOptionPane.VALUE_PROPERTY.equals(prop) ||
             JOptionPane.INPUT_VALUE_PROPERTY.equals(prop))) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                 return;
            }

            optionPane.setValue(
                    JOptionPane.UNINITIALIZED_VALUE);

            if (btnString1.equals(value)) {
                    typedText = textField.getText();
                String ucText = typedText.toUpperCase();
                if (magicWord.equals(ucText)) {
                    //���������Ч��������ı������ضԻ���
                    clearAndHide();
                } else {
                    //�ı�������Ч
                    textField.selectAll();
                    JOptionPane.showMessageDialog(
                                    CustomDialog.this,
                                    "�Բ���, \"" + typedText + "\" "
                                    + "����Ч�����롣\n"
                                    + "����������"
                                    + magicWord + ".",
                                    "����һ��",
                                    JOptionPane.ERROR_MESSAGE);
                    typedText = null;
                    textField.requestFocusInWindow();
                }
            } else { //�û��ر��˶Ի�������ˡ�cancel��
                dd.setLabel("�ðɣ� "
                         + "���ǲ���Ӱ����ľ�������"
                         + magicWord + "��");
                typedText = null;
                clearAndHide();
            }
        }
    }
/**
 *<br>����˵��������ı������ض�����
 *<br>���������
 *<br>�������ͣ�
 */
    public void clearAndHide() {
        textField.setText(null);
        setVisible(false);
    }
}
