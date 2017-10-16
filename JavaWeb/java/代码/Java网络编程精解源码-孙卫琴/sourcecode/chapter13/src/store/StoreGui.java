package store;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class StoreGui {

  //界面的主要窗体组件
  protected JFrame frame;
  protected Container contentPane;
  protected CardLayout card=new CardLayout();
  protected JPanel cardPan=new JPanel();

  //包含各种按钮的选择面板上的组件
  protected JPanel selPan=new JPanel();
  protected JButton custBt=new JButton("客户详细信息");
  protected JButton allCustBt=new JButton("所有客户清单");

  //显示单个客户的面板上的组件
  protected JPanel custPan=new JPanel();
  protected JLabel nameLb=new JLabel("客户姓名");
  protected JLabel idLb=new JLabel("ID");
  protected JLabel addrLb=new JLabel("地址");
  protected JLabel ageLb=new JLabel("年龄");

  protected JTextField nameTf=new JTextField(25);
  protected JTextField idTf=new JTextField(25);
  protected JTextField addrTf=new JTextField(25);
  protected JTextField ageTf=new JTextField(25);
  protected JButton getBt=new JButton("查询客户");
  protected JButton updBt=new JButton("更新客户");
  protected JButton addBt=new JButton("添加客户");
  protected JButton delBt=new JButton("删除客户");

  //列举所有客户的面板上的组件
  protected JPanel allCustPan=new JPanel();
  protected JLabel allCustLb=new JLabel("所有客户清单",SwingConstants.CENTER);
  protected JTextArea allCustTa=new JTextArea();
  protected JScrollPane allCustSp=new JScrollPane(allCustTa);

  String[] tableHeaders={"ID","姓名","地址","年龄"};
  JTable table;
  JScrollPane tablePane;
  DefaultTableModel tableModel;

  //日志面板上的组件
  protected JPanel logPan=new JPanel();
  protected JLabel logLb=new JLabel("操作日志",SwingConstants.CENTER);

  protected JTextArea logTa=new JTextArea(9,50);
  protected JScrollPane logSp=new JScrollPane(logTa);
  
  /** 显示并刷新单个客户面板 custPan */
  public void refreshCustPane(Customer cust){
    showCard("customer");

    if(cust==null || cust.getId()==-1){
      idTf.setText(null);
      nameTf.setText(null);
      addrTf.setText(null);
      ageTf.setText(null);
      return;
    }
    idTf.setText(new Long(cust.getId()).toString());
    nameTf.setText(cust.getName().trim());
    addrTf.setText(cust.getAddr().trim());
    ageTf.setText(new Integer(cust.getAge()).toString());
  }
  
  /** 显示并刷新所有客户面板 allCustPan */
  public void refreshAllCustPan(Set<Customer> custs){
    showCard("allcustomers");
    String newData[][];
    newData=new String[custs.size()][4];
    Iterator<Customer> it=custs.iterator();
    int i=0; 
    while(it.hasNext()){
      Customer cust=it.next();
      newData[i][0]=new Long(cust.getId()).toString();
      newData[i][1]=cust.getName();
      newData[i][2]=cust.getAddr();
      newData[i][3]=new Integer(cust.getAge()).toString();
      i++; 
    }

    tableModel.setDataVector(newData,tableHeaders);
  }
  
  /** 在日志面板logPan中添加日志信息 */
  public void updateLog(String msg){
    logTa.append(msg+"\n");
  }

  /** 获得客户面板custPan上用户输入的ID */
  public long getCustIdOnCustPan(){
     try{
       return Long.parseLong(idTf.getText().trim());
     }catch(Exception e){
       updateLog(e.getMessage());
       return -1;
     }
  }
  
  /** 获得单个客户面板custPan上用户输入的客户信息 */
  public Customer getCustomerOnCustPan(){
    try{
      return new Customer(Long.parseLong(idTf.getText().trim()),
        nameTf.getText().trim(),addrTf.getText().trim(),
        Integer.parseInt(ageTf.getText().trim()));
    }catch(Exception e){
      updateLog(e.getMessage());
      return null; 
    }
  }
  
  /** 显示单个客户面板custPan或者所有客户面板allCustPan */
  private void showCard(String cardStr){
    card.show(cardPan,cardStr);
  }
  
  /** 构造方法 */
  public StoreGui(){
    buildDisplay();
  }
  
  /** 创建图形界面 */
  private void buildDisplay(){
   frame=new JFrame("商店的客户管理系统");
   buildSelectionPanel();
   buildCustPanel();
   buildAllCustPanel();
   buildLogPanel();
   
   /** carPan采用CardLayout布局管理器，包括custPan和allCustPan两张卡片 */
   cardPan.setLayout(card);
   cardPan.add(custPan,"customer");
   cardPan.add(allCustPan,"allcustomers");

   //向主窗体中加入各种面板
   contentPane=frame.getContentPane();
   contentPane.setLayout(new BorderLayout());
   contentPane.add(cardPan,BorderLayout.CENTER);
   contentPane.add(selPan,BorderLayout.NORTH);
   contentPane.add(logPan,BorderLayout.SOUTH);

   frame.pack();
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);

  }
 
  /** 创建选择面板selPan */ 
  private void buildSelectionPanel(){
    selPan.setLayout(new GridLayout(1,2));
    selPan.add(custBt);
    selPan.add(allCustBt);
  }

  /** 为选择面板selPan中的2个按钮注册监听器 */
  public void addSelectionPanelListeners(ActionListener a[]){
   int len=a.length;
   if(len!=2){ return;}

   custBt.addActionListener(a[0]);
   allCustBt.addActionListener(a[1]);
 }
  
  /**　创建单个客户custPan面板 */
  private void buildCustPanel(){
   custPan.setLayout(new GridLayout(6,2));
   custPan.add(idLb);
   custPan.add(idTf);
   custPan.add(nameLb);
   custPan.add(nameTf);
   custPan.add(addrLb);
   custPan.add(addrTf);
   custPan.add(ageLb);
   custPan.add(ageTf);

   custPan.add(getBt);
   custPan.add(updBt);
   custPan.add(addBt);
   custPan.add(delBt);

  }
  
  /** 为单个客户面板custPan中的4个按钮注册监听器 */
  public void addCustPanelListeners(ActionListener a[]){
    int len=a.length;
    if(len!=4){ return;}

   getBt.addActionListener(a[0]);
   addBt.addActionListener(a[1]);
   delBt.addActionListener(a[2]);
   updBt.addActionListener(a[3]);
  }
  
  /** 创建所有客户allCustPan面板 */
  private void buildAllCustPanel(){
    allCustPan.setLayout(new BorderLayout());
    allCustPan.add(allCustLb,BorderLayout.NORTH);
    allCustTa.setText("all customer display");

    tableModel=new DefaultTableModel(tableHeaders,10);
    table=new JTable(tableModel);
    tablePane=new JScrollPane(table);

    allCustPan.add(tablePane,BorderLayout.CENTER);

    Dimension dim=new Dimension(500,150);
    table.setPreferredScrollableViewportSize(dim);
  }
  
  /** 创建日志面板*/
  private void buildLogPanel(){
   logPan.setLayout(new BorderLayout());
   logPan.add(logLb,BorderLayout.NORTH);
   logPan.add(logSp,BorderLayout.CENTER);
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
