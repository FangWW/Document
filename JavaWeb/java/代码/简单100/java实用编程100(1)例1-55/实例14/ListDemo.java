import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
/**
 * <p>Title: 列表框</p>
 * <p>Description: 通过输入框添加元素和点击“删除”按钮删除列表元素</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: ListDemo.java</p>
 * @author 杜江
 * @version 1.0
 */
public class ListDemo extends JPanel
                      implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private static final String hireString = "添加";
    private static final String fireString = "删除";
    private JButton fireButton;
    private JTextField employeeName;

    public ListDemo() {
        super(new BorderLayout());
        //构建List的列表元素
        listModel = new DefaultListModel();
        listModel.addElement("Alan Sommerer");
        listModel.addElement("Alison Huml");
        listModel.addElement("Kathy Walrath");
        listModel.addElement("Lisa Friendly");
        listModel.addElement("Mary Campione");
        listModel.addElement("Sharon Zakhour");

        //创建一个List构件,并将列表元素添加到列表中
        list = new JList(listModel);
        //设置选择模式为单选
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //初始化选择索引在0的位置，即第一个元素
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        //设置列表可以同时看5个元素
        list.setVisibleRowCount(5);
        //给列表添加一个滑动块
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

        //创建一个面板
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
 *<br>类说明：“添加”按钮监听
 *<br>类描述：当点击“添加”按钮后，实现将元素添加到列表框中
 */
    class FireListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
           
            int index = list.getSelectedIndex();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //如果没有了选择项，则是“删除”按钮实效
                fireButton.setEnabled(false);

            } else { //选择了一个
                if (index == listModel.getSize()) {
                    //移除选项
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

/**
 *<br>类说明：“删除”按钮监听事件
 *<br>类描述：实现删除列表元素
 */
    class HireListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public HireListener(JButton button) {
            this.button = button;
        }

        //必须实现 ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = employeeName.getText();

            //如果输入空或有同名
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                employeeName.requestFocusInWindow();
                employeeName.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //获取选择项
            if (index == -1) { //如果没有选择，就插入到第一个
                index = 0;
            } else {           //如果有选择，那么插入到选择项的后面
                index++;
            }

            listModel.insertElementAt(employeeName.getText(), index);
 
            //重新设置文本
            employeeName.requestFocusInWindow();
            employeeName.setText("");

            //选择新的元素，并显示出来
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
/**
 *<br>方法说明：检测是否在LIST中有重名元素
 *<br>输入参数：String name 检测的名字
 *<br>返回类型：boolean 布尔值，如果存在返回true
 */

        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

/**
 *<br>方法说明：实现DocumentListener接口，必需实现的方法
 *<br>输入参数：
 *<br>返回类型：
 */
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

/**
 *<br>方法说明：实现DocumentListener接口，必需实现的方法
 *<br>输入参数：
 *<br>返回类型：
 */
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

/**
 *<br>方法说明：实现DocumentListener接口，必需实现的方法
 *<br>输入参数：
 *<br>返回类型：
 */
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }
/**
 *<br>方法说明：按钮使能
 *<br>输入参数：
 *<br>返回类型：
 */
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }
/**
 *<br>方法说明：实现DocumentListener接口，必需实现的方法，修改按钮的状态
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：实现ListSelectionListener接口，必需实现的方法
 *<br>输入参数：
 *<br>返回类型：
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
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
    public static void main(String[] args) {

        JFrame.setDefaultLookAndFeelDecorated(true);

        //创建一个窗体
        JFrame frame = new JFrame("ListDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //创建一个面版
        JComponent newContentPane = new ListDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);

        //显示窗体
        frame.pack();
        frame.setVisible(true);
    }
}
