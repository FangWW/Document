import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.sql.*;
import javax.sql.rowset.*;
import java.io.*;

public class RowSetDemo extends JFrame implements ActionListener{
  private CachedRowSet rowset;  //用于更新和删除数据
  private CachedRowSet rowsetI;  //用于插入数据
  private String sql="select ID,NAME,AGE,ADDRESS from CUSTOMERS";
  private String sqlI="select NAME,AGE,ADDRESS from CUSTOMERS";
  private ConnectionProvider provider;
  private final int DATA_FOR_DISPLAY_AND_UPDATE=1;
  private final int DATA_FOR_INSERT=2;
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
   
  public RowSetDemo(String title)throws SQLException{
   super(title); 
   provider=new ConnectionProvider();
   load(DATA_FOR_DISPLAY_AND_UPDATE); //向rowset中加载数据，用于更新和删除

   if(rowset.next())refresh();
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
 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);
  }
  
  
  public void actionPerformed(ActionEvent e) {
    JButton b=(JButton)e.getSource();
    try{
      if(b.getText().equals("first")){
        rowset.first(); //把游标移动到第一条记录
      }else if(b.getText().equals("last")){
        rowset.last(); //把游标移动到第最后一条记录
      }else if(b.getText().equals("next")){
        if(rowset.isLast())return;
        rowset.next(); //把游标移动到下一条记录
      }else if(b.getText().equals("previous")){
        if(rowset.isFirst())return;
        rowset.previous();  //把游标移动到前一条记录
      }else if(b.getText().equals("update")){
        int row=rowset.getRow(); 
        rowset.updateString("NAME",nameTxtFid.getText());
        rowset.updateInt("AGE",Integer.parseInt(ageTxtFid.getText()));
        rowset.updateString("ADDRESS",addressTxtFid.getText());
        rowset.updateRow();  //更新记录
        save();
        rowset.absolute(row);  
      }else if(b.getText().equals("delete")){
        rowset.deleteRow();  //删除记录
        save();
        rowset.first();  //把游标移动到第一条记录
      }else if(b.getText().equals("insert")){
        insert();
      }
      refresh();  //刷新界面上的数据
    }catch(SQLException ex){ex.printStackTrace();}
  }
  
  private void save()throws SQLException{
    save(rowset);
  }
  private void save(CachedRowSet rowset)throws SQLException{
    Connection con=provider.getConnection();
    rowset.acceptChanges(con);
    con.close();
  }
  
  private void load(int type)throws SQLException{
    Connection con=provider.getConnection();
    Statement stmt=con.createStatement();
    if(type==DATA_FOR_DISPLAY_AND_UPDATE){
      rowset=new com.sun.rowset.CachedRowSetImpl();
      rowset.populate(stmt.executeQuery(sql));
    }else{
      rowsetI=new com.sun.rowset.CachedRowSetImpl();
      rowsetI.populate(stmt.executeQuery(sqlI));
    }
    con.close();
  }

  private void insert()throws SQLException{
     load(DATA_FOR_INSERT);
     rowsetI.moveToInsertRow();
     rowsetI.updateString("NAME",nameTxtFid.getText());
     rowsetI.updateInt("AGE",Integer.parseInt(ageTxtFid.getText()));
     rowsetI.updateString("ADDRESS",addressTxtFid.getText());
     rowsetI.insertRow();
     rowsetI.moveToCurrentRow();
     save(rowsetI);     
     
     load(DATA_FOR_DISPLAY_AND_UPDATE);
     rowset.next();
  }
  private void refresh()throws SQLException{  //刷新界面上的数据
    int row=rowset.getRow();  //返回游标当前所在的位置
    rowLabel.setText("显示第"+row+"条记录");
    if(row==0){
      rowset.beforeFirst();
      idTxtFid.setText("");
      nameTxtFid.setText("");
      ageTxtFid.setText("");
      addressTxtFid.setText("");
    }else{
      idTxtFid.setText(new Long(rowset.getLong(1)).toString());
      nameTxtFid.setText(rowset.getString(2));
      ageTxtFid.setText(new Integer(rowset.getInt(3)).toString());
      addressTxtFid.setText(rowset.getString(4));
    }
  }

  public static void main(String[] args)throws SQLException {
    new RowSetDemo("演示RowSet的用法");
  }
}


/****************************************************
 * 作者：孙卫琴                                     *
 * 来源：<<Java网络编程精解>>                       *
 * 技术支持网址：www.javathinker.org                *
 ***************************************************/
