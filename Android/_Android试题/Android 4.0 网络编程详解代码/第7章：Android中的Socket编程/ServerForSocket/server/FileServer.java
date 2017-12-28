package com.wangjialin.net.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.itcast.utils.StreamTool;

public class FileServer {
	
	 private ExecutorService executorService;//线程池
	 private int port;//监听端口
	 private boolean quit = false;//退出
	 private ServerSocket server;
	 private Map<Long, FileLog> datas = new HashMap<Long, FileLog>();//存放断点数据
	 
	 public FileServer(int port){
		 this.port = port;
		 //创建线程池，池中具有(cpu个数*50)条线程
		 executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 50);
	 }
	 /**
	  * 退出
	  */
	 public void quit(){
		this.quit = true;
		try {
			server.close();
		} catch (IOException e) {
		}
	 }
	 /**
	  * 启动服务
	  * @throws Exception
	  */
	 public void start() throws Exception{
		 server = new ServerSocket(port);
		 while(!quit){
	         try {
	           Socket socket = server.accept();
	           //为支持多用户并发访问，采用线程池管理每一个用户的连接请求
	           executorService.execute(new SocketTask(socket));
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	     }
	 }
	 
	 private final class SocketTask implements Runnable{
		private Socket socket = null;
		public SocketTask(Socket socket) {
			this.socket = socket;
		}
		
		public void run() {
			try {
				System.out.println("accepted connection "+ socket.getInetAddress()+ ":"+ socket.getPort());
				PushbackInputStream inStream = new PushbackInputStream(socket.getInputStream());
				//得到客户端发来的第一行协议数据：Content-Length=143253434;filename=xxx.3gp;sourceid=
				//如果用户初次上传文件，sourceid的值为空。
				String head = StreamTool.readLine(inStream);
				System.out.println(head);
				if(head!=null){
					//下面从协议数据中提取各项参数值
					String[] items = head.split(";");
					String filelength = items[0].substring(items[0].indexOf("=")+1);
					String filename = items[1].substring(items[1].indexOf("=")+1);
					String sourceid = items[2].substring(items[2].indexOf("=")+1);		
					long id = System.currentTimeMillis();//生产资源id，如果需要唯一性，可以采用UUID
					FileLog log = null;
					if(sourceid!=null && !"".equals(sourceid)){
						id = Long.valueOf(sourceid);
						log = find(id);//查找上传的文件是否存在上传记录
					}
					File file = null;
					int position = 0;
					if(log==null){//如果不存在上传记录,为文件添加跟踪记录
						String path = new SimpleDateFormat("yyyy/MM/dd/HH/mm").format(new Date());
						File dir = new File("file/"+ path);
						if(!dir.exists()) dir.mkdirs();
						file = new File(dir, filename);
						if(file.exists()){//如果上传的文件发生重名，然后进行改名
							filename = filename.substring(0, filename.indexOf(".")-1)+ dir.listFiles().length+ filename.substring(filename.indexOf("."));
							file = new File(dir, filename);
						}
						save(id, file);
					}else{// 如果存在上传记录,读取已经上传的数据长度
						file = new File(log.getPath());//从上传记录中得到文件的路径
						if(file.exists()){
							File logFile = new File(file.getParentFile(), file.getName()+".log");
							if(logFile.exists()){
								Properties properties = new Properties();
								properties.load(new FileInputStream(logFile));
								position = Integer.valueOf(properties.getProperty("length"));//读取已经上传的数据长度
							}
						}
					}
					
					OutputStream outStream = socket.getOutputStream();
					String response = "sourceid="+ id+ ";position="+ position+ "\r\n";
					//服务器收到客户端的请求信息后，给客户端返回响应信息：sourceid=1274773833264;position=0
					//sourceid由服务器端生成，唯一标识上传的文件，position指示客户端从文件的什么位置开始上传
					outStream.write(response.getBytes());
					
					RandomAccessFile fileOutStream = new RandomAccessFile(file, "rwd");
					if(position==0) fileOutStream.setLength(Integer.valueOf(filelength));//设置文件长度
					fileOutStream.seek(position);//指定从文件的特定位置开始写入数据
					byte[] buffer = new byte[1024];
					int len = -1;
					int length = position;
					while( (len=inStream.read(buffer)) != -1){//从输入流中读取数据写入到文件中
						fileOutStream.write(buffer, 0, len);
						length += len;
						Properties properties = new Properties();
						properties.put("length", String.valueOf(length));
						FileOutputStream logFile = new FileOutputStream(new File(file.getParentFile(), file.getName()+".log"));
						properties.store(logFile, null);//实时记录已经接收的文件长度
						logFile.close();
					}
					if(length==fileOutStream.length()) delete(id);
					fileOutStream.close();					
					inStream.close();
					outStream.close();
					file = null;
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
	            try {
	                if(socket!=null && !socket.isClosed()) socket.close();
	            } catch (IOException e) {}
	        }
		}
	 }
	 
	 public FileLog find(Long sourceid){
		 return datas.get(sourceid);
	 }
	 //保存上传记录
	 public void save(Long id, File saveFile){
		 //日后可以改成通过数据库存放
		 datas.put(id, new FileLog(id, saveFile.getAbsolutePath()));
	 }
	 //当文件上传完毕，删除记录
	 public void delete(long sourceid){
		 if(datas.containsKey(sourceid)) datas.remove(sourceid);
	 }
	 
	 private class FileLog{
		private Long id;
		private String path;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public FileLog(Long id, String path) {
			this.id = id;
			this.path = path;
		}	
	 }

}
