package com.wangjialin.internet.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLite管理器，实现创建数据库和表，但版本变化时实现对表的数据库表的操作
 * @author think
 *
 */
public class DBOpenHelper extends SQLiteOpenHelper {
	private static final String DBNAME = "eric.db";	//设置数据库的名称
	private static final int VERSION = 1;	//设置数据库的版本
	
	/**
	 * 通过构造方法
	 * @param context 应用程序的上下文对象
	 */
	public DBOpenHelper(Context context) {
		super(context, DBNAME, null, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {	//建立数据表
		db.execSQL("CREATE TABLE IF NOT EXISTS filedownlog (id integer primary key autoincrement, downpath varchar(100), threadid INTEGER, downlength INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {	//当版本变化时系统会调用该回调方法
		db.execSQL("DROP TABLE IF EXISTS filedownlog");	//此处是删除数据表，在实际的业务中一般是需要数据备份的
		onCreate(db);	//调用onCreate方法重新创建数据表，也可以自己根据业务需要创建新的的数据表
	}

}

