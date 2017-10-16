import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class ResultSetDemo extends JFrame implements ActionListener{
  private final Connection con;
  private Statement stmt;
  private ResultSet resultSet;

  private JLabel rowLabel=new JLabel();

  private JTextField idTxtFid=new JTextField();
  private JTextField nameTxtFid=new JTextField();
  private JTextField ageTxtFid=new JTextField();
  private JTextField addressTxtFid=new JTextField();
  private JLabel idLabel=new JLabel("id");
  private JLabel nameLabel=new JLabel("name");
  private JLabel ageLabel=new JLabel("age");
  private JLabel addressLabel=new JLabel("address");

  private JButton firstBt=new JButton("first");
  private JButton previousBt=new JButton("previous");
  private JButton nextBt=new JButton("next");
  private JButton lastBt=new JButton("last");
  private JButton insertBt=new JButton("insert");
  private JButton deleteBt=new JButton("delete");
  private JButton updateBt=new JButton("update");

  private JPanel headPanel=new JPanel();
  private JPanel centerPanel=new JPanel();
  private JPanel bottomPanel=new JPanel();
   
  public ResultSetDemo(String title)throws SQLException{
   super(title);
   con=new ConnectionProvider().getConnection();
   stmt=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
   ResultSet.CONCUR_UPDATABLE);
   resultSet=stmt.executeQuery("select ID,NAME,AGE,ADDRESS from CUSTOMERS");
   
   if(resultSet.next())refresh();
   buildDisplay();
  }
  
  private void buildDisplay(){  //创建GUI界面      
    firstBt.addActionListener(this);
    previousBt.addActionListener(this);
    nextBt.addActionListener(this);
    lastBt.addActionListener(this);
    insertBt.addActionListener(this);
    updateBt.addActionListener(this);
    deleteBt.addActionListener(this);
    Container contentPane=getContentPane();
    headPanel.add(rowLabel);
    centerPanel.setLayout(new GridLayout(4,2,2,2));
    centerPanel.add(idLabel);
    centerPanel.add(idTxtFid);
    idTxtFid.setEditable(false);
    centerPanel.add(nameLabel);
    centerPanel.add(nameTxtFid); 
    centerPanel.add(ageLabel);
    centerPanel.add(ageTxtFid);
    centerPanel.add(addressLabel);
    centerPanel.add(addressTxtFid); 
   
    bottomPanel.add(firstBt);
    bottomPanel.add(previousBt);
    bottomPanel.add(nextBt);
    bottomPanel.add(lastBt);
    bottomPanel.add(insertBt);
    bottomPanel.add(updateBt);
    bottomPanel.add(deleteBt);

    contentPane.add(headPanel,BorderLayout.NORTH) ;
    contentPane.add(centerPanel,BorderLayout.CENTER);
    contentPane.add(bottomPanel,BorderLayout.SOUTH);
 
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener( new WindowAdapter(){
      public void windowClosing(WindowEvent e){
        try{con.close();}catch(Exception ex){ex.printStackTrace();}
        System.exit(0);
      } 
    });

    pack();
    setVisible(true);
  }
  
  
  public void actionPerformed(ActionEvent e) {
    JButton b=(JButton)e.getSource();
    try{
      if(b.getText().equals("first")){
        resultSet.first(); //把游标移动到第一条记录
      }else if(b.getText().equals("last")){
        resultSet.last(); //把游标移动到第最后一条记录
      }else if(b.getText().equals("next")){
        if(resultSet.isLast())return;
        resultSet.next(); //把游标移动到下一条记录
      }else if(b.getText().equals("previous")){
        if(resultSet.isFirst())return;
        resultSet.previous();  //把游标移动到前一条记录
      }else if(b.getText().equals("update")){
        resultSet.updateString("NAME",nameTxtFid.getText());
        resultSet.updateInt("AGE",Integer.parseInt(ageTxtFid.getText()));
        resultSet.updateString("ADDRESS",addressTxtFid.getText());
        resultSet.updateRow();  //更新记录
      }else if(b.getText().equals("delete")){
        resultSet.deleteRow();  //删除记录
        resultSet.first(); //显示第一条记录
      }else if(b.getText().equals("insert")){
        resultSet.moveToInsertRow();
        resultSet.updateString("NAME",nameTxtFid.getText());
        resultSet.updateInt("AGE",Integer.parseInt(ageTxtFid.getText()));
        resultSet.updateString("ADDRESS",addressTxtFid.getText());
        resultSet.insertRow();  //插入一条记录
        resultSet.moveToCurrentRow();  //把游标移动到插入前的位置
      }
      refresh();  //刷新界面上的数据
    }catch(SQLException ex){ex.printStackTrace();}
  }
  
  private void refresh()throws SQLException{  //刷新界面上的数据
    int row=resultSet.getRow();  //返回游标当前所在的位置
    rowLabel.setText("显示第"+row+"条记录");
    if(row==0){
      idTxtFid.setText(""); 
      nameTxtFid.setText("");
      ageTxtFid.setText("");
      addressTxtFid.setText("");
    }else{
      idTxtFid.setText(new Long(resultSet.getLong(1)).toString()); 
      nameTxtFid.setText(resultSet.getString(2));
      ageTxtFid.setText(new Integer(resultSet.getInt(3)).toString());
      addressTxtFid.setText(resultSet.getString(4));
    }
  }

  public static void main(String[] args)throws SQLException {
    new ResultSetDemo("演示ResultSet的用法");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
