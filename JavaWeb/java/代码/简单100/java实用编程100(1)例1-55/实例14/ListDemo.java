/**
 * <p>Title: �б��</p>
 * <p>Description: ͨ����������Ԫ�غ͵����ɾ������ťɾ���б�Ԫ��</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ListDemo.java</p>
 * @author �Ž�
 * @version 1.0
 */
public class ListDemo extends JPanel
                      implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String hireString = "���";
    private static final String fireString = "ɾ��";
    private JButton fireButton;
    private JTextField employeeName;

    public ListDemo() {
        super(new BorderLayout());
        //����List���б�Ԫ��
        listModel = new DefaultListModel();
        listModel.addElement("Alan Sommerer");
        listModel.addElement("Alison Huml");
        listModel.addElement("Kathy Walrath");
        listModel.addElement("Lisa Friendly");
        listModel.addElement("Mary Campione");
        listModel.addElement("Sharon Zakhour");

        //����һ��List����,�����б�Ԫ����ӵ��б���
        list = new JList(listModel);
        //����ѡ��ģʽΪ��ѡ
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //��ʼ��ѡ��������0��λ�ã�����һ��Ԫ��
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        //�����б����ͬʱ��5��Ԫ��
        list.setVisibleRowCount(5);
        //���б����һ��������
        JScrollPane listScrollPane = new JScrollPane(list);

        JButton hireButton = new JButton(hireString);
        HireListener hireListener = new HireListener(hireButton);
        hireButton.setActionCommand(hireString);
        hireButton.addActionListener(hireListener);
        hireButton.setEnabled(false);

        fireButton = new JButton(fireString);
        fireButton.setActionCommand(fireString);
        fireButton.addActionListener(new FireListener());

        employeeName = new JTextField(10);
        employeeName.addActionListener(hireListener);
        employeeName.getDocument().addDocumentListener(hireListener);
        String name = listModel.getElementAt(
                              list.getSelectedIndex()).toString();

        //����һ�����
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                                           BoxLayout.LINE_AXIS));
        buttonPane.add(fireButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(employeeName);
        buttonPane.add(hireButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);
    }
/**
 *<br>��˵��������ӡ���ť����
 *<br>�����������������ӡ���ť��ʵ�ֽ�Ԫ����ӵ��б����
 */
    class FireListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           
            int index = list.getSelectedIndex();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //���û����ѡ������ǡ�ɾ������ťʵЧ
                fireButton.setEnabled(false);

            } else { //ѡ����һ��
                if (index == listModel.getSize()) {
                    //�Ƴ�ѡ��
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

/**
 *<br>��˵������ɾ������ť�����¼�
 *<br>��������ʵ��ɾ���б�Ԫ��
 */
    class HireListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public HireListener(JButton button) {
            this.button = button;
        }

        //����ʵ�� ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = employeeName.getText();

            //�������ջ���ͬ��
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                employeeName.requestFocusInWindow();
                employeeName.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //��ȡѡ����
            if (index == -1) { //���û��ѡ�񣬾Ͳ��뵽��һ��
                index = 0;
            } else {           //�����ѡ����ô���뵽ѡ����ĺ���
                index++;
            }

            listModel.insertElementAt(employeeName.getText(), index);
 
            //���������ı�
            employeeName.requestFocusInWindow();
            employeeName.setText("");

            //ѡ���µ�Ԫ�أ�����ʾ����
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
/**
 *<br>����˵��������Ƿ���LIST��������Ԫ��
 *<br>���������String name ��������
 *<br>�������ͣ�boolean ����ֵ��������ڷ���true
 */

        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

/**
 *<br>����˵����ʵ��DocumentListener�ӿڣ�����ʵ�ֵķ���
 *<br>���������
 *<br>�������ͣ�
 */
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

/**
 *<br>����˵����ʵ��DocumentListener�ӿڣ�����ʵ�ֵķ���
 *<br>���������
 *<br>�������ͣ�
 */
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

/**
 *<br>����˵����ʵ��DocumentListener�ӿڣ�����ʵ�ֵķ���
 *<br>���������
 *<br>�������ͣ�
 */
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }
/**
 *<br>����˵������ťʹ��
 *<br>���������
 *<br>�������ͣ�
 */
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }
/**
 *<br>����˵����ʵ��DocumentListener�ӿڣ�����ʵ�ֵķ������޸İ�ť��״̬
 *<br>���������
 *<br>�������ͣ�
 */
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }
/**
 *<br>����˵����ʵ��ListSelectionListener�ӿڣ�����ʵ�ֵķ���
 *<br>���������
 *<br>�������ͣ�
 */
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                fireButton.setEnabled(false);

            } else {
                fireButton.setEnabled(true);
            }
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
        JFrame frame = new JFrame("ListDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //����һ�����
        JComponent newContentPane = new ListDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //��ʾ����
        frame.pack();
        frame.setVisible(true);
    }
}
