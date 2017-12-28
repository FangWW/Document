package com.wangjialin.net.client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

import cn.itcast.utils.StreamTool;

public class SocketClient {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", 7878);
            OutputStream outStream = socket.getOutputStream();            
            String filename = "QQWubiSetup.exe";
            File file = new File(filename);
            String head = "Content-Length="+ file.length() + ";filename="+ filename + ";sourceid=\r\n";
            outStream.write(head.getBytes());
            
            PushbackInputStream inStream = new PushbackInputStream(socket.getInputStream());	
			String response = StreamTool.readLine(inStream);
            System.out.println(response);
            String[] items = response.split(";");
			String position = items[1].substring(items[1].indexOf("=")+1);
			
			RandomAccessFile fileOutStream = new RandomAccessFile(file, "r");
			fileOutStream.seek(Integer.valueOf(position));
			byte[] buffer = new byte[1024];
			int len = -1;
			while( (len = fileOutStream.read(buffer)) != -1){
				outStream.write(buffer, 0, len);
			}
			fileOutStream.close();
			outStream.close();
            inStream.close();
            socket.close();
        } catch (Exception e) {                    
            e.printStackTrace();
        }

	}
	/**
	* 读取流
	* @param inStream
	* @return 字节数组
	* @throws Exception
	*/
	public static byte[] readStream(InputStream inStream) throws Exception{
			ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = -1;
			while( (len=inStream.read(buffer)) != -1){
				outSteam.write(buffer, 0, len);
			}
			outSteam.close();
			inStream.close();
			return outSteam.toByteArray();
	}
}
