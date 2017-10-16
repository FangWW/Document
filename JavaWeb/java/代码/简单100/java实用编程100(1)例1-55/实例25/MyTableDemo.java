import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import javax.swing.DefaultCellEditor;

import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;
/**
 * <p>Title: 自己定义的表格</p>
 * <p>Description: 继承AbstractTableModel类，实现自己的表格</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Filename: MyTableDemo.java</p>
 * @author 杜江
 * @version 1.0
 */
public class MyTableDemo extends JFrame {

    public MyTableDemo() {
        super("MyTableDemo");
        //声明自己的表格，并添加到JTable中
        MyTableModel myModel = new MyTableModel();
        JTable table = new JTable(myModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));

        //将表格添加到可滚动的面板
        JScrollPane scrollPane = new JScrollPane(table);

        //将滚动面板添加到窗体
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        //添加监听
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
/**
 * <p>Title: 定义自己的表格模式</p>
 * <p>Description: 通过继承AbstractTableModel类来定义自己的表格模式，
 *    这里使得第三个以后才可以编辑</p>
 */
    class MyTableModel extends AbstractTableModel {
        //定义表头
        final String[] columnNames = {"姓名", 
                                      "性别",
                                      "学历",
                                      "年龄",
                                      "是否已婚"};
        //初始化表数据
        final Object[][] data = {
            {"张三", "男", 
             "大本", new Integer(25), new Boolean(false)},
            {"李四", "男", 
             "大本", new Integer(33), new Boolean(true)},
            {"王五", "男",
             "高中", new Integer(20), new Boolean(false)},
            {"赵倩", "女",
             "大专", new Integer(26), new Boolean(true)},
            {"周大", "男",
             "大本", new Integer(24), new Boolean(false)}
        };
/**
 *<br>方法说明：继承AbstractTableModel必须实现的方法
 *<br>输入参数：
 *<br>返回类型：int 列数
 */
        public int getColumnCount() {
            return columnNames.length;
        }
/**
 *<br>方法说明：继承AbstractTableModel必须实现的方法
 *<br>输入参数：
 *<br>返回类型：int 列数
 */        
        public int getRowCount() {
            return data.length;
        }
/**
 *<br>方法说明：继承AbstractTableModel必须实现的方法
 *<br>输入参数：
 *<br>返回类型：String 列名
 */
        public String getColumnName(int col) {
            return columnNames[col];
        }
/**
 *<br>方法说明：继承AbstractTableModel必须实现的方法，获取表格中的数据
 *<br>输入参数：int row 行数
 *<br>输入参数：int col 列数
 *<br>返回类型：Object 数据对象
 */
        public Object getValueAt(int row, int col) {
            return data[row][col];
        }
/**
 *<br>方法说明：实现这个方法使得最后一列不是显示true和false。而是使用检查盒
 *<br>输入参数：
 *<br>返回类型：
 */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
/**
 *<br>方法说明：这个方法不一定需要实现。这里是为了定义可编辑的列
 *<br>输入参数：
 *<br>返回类型：boolean 是否可编辑
 */
        public boolean isCellEditable(int row, int col) {
            if (col < 2) { 
                return false;
            } else {
                return true;
            }
        }
/**
 *<br>方法说明：实现此方法，获得编辑数据。
 *<br>输入参数：
 *<br>返回类型：
 */
        public void setValueAt(Object value, int row, int col) {
                System.out.println("修改数据位置： " + row + "," + col
                                   + " 新数据为： " + value);

            data[row][col] = value;
            fireTableCellUpdated(row, col);

                System.out.println("表格新数据:");
                printDebugData();

        }
/**
 *<br>方法说明：输出表格中的新数据
 *<br>输入参数：
 *<br>返回类型：
 */
        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    行 " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
/**
 *<br>方法说明：主方法
 *<br>输入参数：
 *<br>返回类型：
 */
    public static void main(String[] args) {
    	JFrame.setDefaultLookAndFeelDecorated(true);
        MyTableDemo frame = new MyTableDemo();
        frame.pack();
        frame.setVisible(true);
    }
}
