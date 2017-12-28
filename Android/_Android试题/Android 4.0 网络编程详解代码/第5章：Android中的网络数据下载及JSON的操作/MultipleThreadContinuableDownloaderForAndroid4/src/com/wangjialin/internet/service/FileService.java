package com.wangjialin.internet.service;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * 业务Bean，实现对数据的操作
 * @author Wang Jialin
 *
 */
public class FileService {
	private DBOpenHelper openHelper;	//声明数据库管理器

	public FileService(Context context) {
		openHelper = new DBOpenHelper(context);	//根据上下文对象实例化数据库管理器
	}
	/**
	 * 获取特定URI的每条线程已经下载的文件长度
	 * @param path
	 * @return
	 */
	public Map<Integer, Integer> getData(String path){
		SQLiteDatabase db = openHelper.getReadableDatabase();	//获取可读的数据库句柄，一般情况下在该操作的内部实现中其返回的其实是可写的数据库句柄
		Cursor cursor = db.rawQuery("select threadid, downlength from filedownlog where downpath=?", new String[]{path});	//根据下载路径查询所有线程下载数据，返回的Cursor指向第一条记录之前
		Map<Integer, Integer> data = new HashMap<Integer, Integer>();	//建立一个哈希表用于存放每条线程的已经下载的文件长度
		while(cursor.moveToNext()){	//从第一条记录开始开始遍历Cursor对象
			data.put(cursor.getInt(0), cursor.getInt(1));	//把线程id和该线程已下载的长度设置进data哈希表中
			data.put(cursor.getInt(cursor.getColumnIndexOrThrow("threadid")), cursor.getInt(cursor.getColumnIndexOrThrow("downlength")));
		}
		cursor.close();	//关闭cursor，释放资源
		db.close();	//关闭数据库
		return data;	//返回获得的每条线程和每条线程的下载长度
	}
	/**
	 * 保存每条线程已经下载的文件长度
	 * @param path	下载的路径
	 * @param map 现在的id和已经下载的长度的集合
	 */
	public void save(String path,  Map<Integer, Integer> map){
		SQLiteDatabase db = openHelper.getWritableDatabase();	//获取可写的数据库句柄
		db.beginTransaction();	//开始事务，因为此处要插入多批数据
		try{
			for(Map.Entry<Integer, Integer> entry : map.entrySet()){	//采用For-Each的方式遍历数据集合
				db.execSQL("insert into filedownlog(downpath, threadid, downlength) values(?,?,?)",
						new Object[]{path, entry.getKey(), entry.getValue()});	//插入特定下载路径特定线程ID已经下载的数据
			}
			db.setTransactionSuccessful();	//设置事务执行的标志为成功
		}finally{	//此部分的代码肯定是被执行的，如果不杀死虚拟机的话
			db.endTransaction();	//结束一个事务，如果事务设立了成功标志，则提交事务，否则会滚事务
		}
		db.close();	//关闭数据库，释放相关资源
	}
	/**
	 * 实时更新每条线程已经下载的文件长度
	 * @param path
	 * @param map
	 */
	public void update(String path, int threadId, int pos){
		SQLiteDatabase db = openHelper.getWritableDatabase();	//获取可写的数据库句柄
		db.execSQL("update filedownlog set downlength=? where downpath=? and threadid=?",
				new Object[]{pos, path, threadId});	//更新特定下载路径下特定线程已经下载的文件长度
		db.close();	//关闭数据库，释放相关的资源
	}
	/**
	 * 当文件下载完成后，删除对应的下载记录
	 * @param path
	 */
	public void delete(String path){
		SQLiteDatabase db = openHelper.getWritableDatabase();	//获取可写的数据库句柄
		db.execSQL("delete from filedownlog where downpath=?", new Object[]{path});	//删除特定下载路径的所有线程记录
		db.close();	//关闭数据库，释放资源
	}
	
}
