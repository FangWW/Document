package com.wangjialin.internet.service;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageService {
	/**
	 * »ñÈ¡Í¼Æ¬
	 * @param path Í¼Æ¬Â·¾¶
	 * @return
	 */
	public static Bitmap getImage(String path) throws Exception{
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if(conn.getResponseCode() == 200){
			InputStream inStream = conn.getInputStream();
			Bitmap bitmap = BitmapFactory.decodeStream(inStream);
			return bitmap;
			/*byte[] data = StreamTool.read(inStream);
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			return bitmap;*/
		}
		return null;
	}

}
