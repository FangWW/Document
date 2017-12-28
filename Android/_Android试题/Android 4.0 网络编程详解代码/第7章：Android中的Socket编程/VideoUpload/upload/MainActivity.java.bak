package cn.itcast.upload;

import java.io.File;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.io.RandomAccessFile;
import java.net.Socket;

import cn.itcast.service.UploadLogService;
import cn.itcast.utils.StreamTool;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText filenameText;
    private TextView resultView;
    private ProgressBar uploadbar;
    private UploadLogService service;
    private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			uploadbar.setProgress(msg.getData().getInt("length"));
			float num = (float)uploadbar.getProgress() / (float)uploadbar.getMax();
			int result = (int)(num * 100);
			resultView.setText(result + "%");
			if(uploadbar.getProgress() == uploadbar.getMax()){
				Toast.makeText(MainActivity.this, R.string.success, 1).show();
			}
		}
    };
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        service =  new UploadLogService(this);
        filenameText = (EditText)findViewById(R.id.filename);
        resultView = (TextView)findViewById(R.id.result);
        uploadbar = (ProgressBar)findViewById(R.id.uploadbar);
        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {	
			public void onClick(View v) {
				String filename = filenameText.getText().toString();
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
					File file = new File(Environment.getExternalStorageDirectory(), filename);
					if(file.exists()){
						uploadbar.setMax((int)file.length());
						uploadFile(file);
					}else{
						Toast.makeText(MainActivity.this, R.string.notexsit, 1).show();
					}
				}else{
					Toast.makeText(MainActivity.this, R.string.sdcarderror, 1).show();
				}
			}
		});
    }

	private void uploadFile(final File file) {
		new Thread(new Runnable() {	
			public void run() {
				try {
					String sourceid = service.getBindId(file);
					Socket socket = new Socket("192.168.1.100", 7878);
		            OutputStream outStream = socket.getOutputStream(); 
		            String head = "Content-Length="+ file.length() + ";filename="+ file.getName() 
		            	+ ";sourceid="+(sourceid!=null ? sourceid : "")+"\r\n";
		            outStream.write(head.getBytes());
		            
		            PushbackInputStream inStream = new PushbackInputStream(socket.getInputStream());	
					String response = StreamTool.readLine(inStream);
		            String[] items = response.split(";");
					String responseSourceid = items[0].substring(items[0].indexOf("=")+1);
					String position = items[1].substring(items[1].indexOf("=")+1);
					if(sourceid==null){//如果是第一次上传文件，在数据库中不存在该文件所绑定的资源id
						service.save(responseSourceid, file);
					}
					RandomAccessFile fileOutStream = new RandomAccessFile(file, "r");
					fileOutStream.seek(Integer.valueOf(position));
					byte[] buffer = new byte[1024];
					int len = -1;
					int length = Integer.valueOf(position);
					while( (len = fileOutStream.read(buffer)) != -1){
						outStream.write(buffer, 0, len);
						length += len;//累加已经上传的数据长度
						Message msg = new Message();
						msg.getData().putInt("length", length);
						handler.sendMessage(msg);
					}
					if(length == file.length()) service.delete(file);
					fileOutStream.close();
					outStream.close();
		            inStream.close();
		            socket.close();
		        } catch (Exception e) {                    
		        	Toast.makeText(MainActivity.this, R.string.error, 1).show();
		        }
			}
		}).start();
	}
}