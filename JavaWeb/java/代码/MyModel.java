package com.demos;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MyModel extends AbstractTableModel {
	private ResultSet rs;
	private List<String[]> datas = new ArrayList<String[]>();
	ResultSetMetaData rsmd;

	public MyModel(ResultSet rs) {
		this.rs = rs;
		try {
			rsmd = rs.getMetaData();
			while (rs.next()) {
				String[] data = new String[rsmd.getColumnCount()];
				for (int i = 0; i < data.length; i++) {
					data[i] = rs.getString(i + 1);
				}
				datas.add(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		try {
			return rsmd.getColumnCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public String getColumnName(int column) {

		try {
			return rsmd.getColumnName(column + 1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.getColumnName(column);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return datas.get(rowIndex)[columnIndex];
	}

}
