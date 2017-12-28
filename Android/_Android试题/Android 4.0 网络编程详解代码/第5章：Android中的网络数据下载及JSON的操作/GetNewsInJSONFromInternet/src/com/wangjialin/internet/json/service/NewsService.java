package com.wangjialin.internet.json.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.wangjialin.internet.json.domain.News;
import com.wangjialin.internet.json.utils.StreamTool;



public class NewsService {
	
	/**
	 * 获取最新视频资讯
	 * @return
	 * @throws Exception
	 */
	public static List<News> getJSONLastNews() throws Exception{
		String path = "http://192.168.1.100:8080/ServerForJSON/NewsListServlet";
		HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if(conn.getResponseCode() == 200){
			InputStream json = conn.getInputStream();
			return parseJSON(json);
		}
		return null;
	}
	
	private static List<News> parseJSON(InputStream jsonStream) throws Exception{
		List<News> list = new ArrayList<News>();
		byte[] data = StreamTool.read(jsonStream);
		String json = new String(data);
		JSONArray jsonArray = new JSONArray(json);
		for(int i = 0; i < jsonArray.length() ; i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			int id = jsonObject.getInt("id");
			String title = jsonObject.getString("title");
			int timelength = jsonObject.getInt("timelength");
			list.add(new News(id, title, timelength));
		}
		return list;
	}
}
