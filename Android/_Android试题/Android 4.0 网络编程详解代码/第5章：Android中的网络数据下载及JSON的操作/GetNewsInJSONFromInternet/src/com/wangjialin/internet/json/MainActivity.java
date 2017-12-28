package com.wangjialin.internet.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.wangjialin.internet.json.domain.News;
import com.wangjialin.internet.json.service.NewsService;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ListView listView = (ListView) this.findViewById(R.id.listView);
        
        String length = this.getResources().getString(R.string.length);
        try {
			List<News> newes = NewsService.getJSONLastNews();
			List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
			for(News news : newes){
				HashMap<String, Object> item = new HashMap<String, Object>();
				item.put("id", news.getId());
				item.put("title", news.getTitle());
				item.put("timelength", length+ news.getTimelength());
				data.add(item);
			}
			SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item,
					new String[]{"title", "timelength"}, new int[]{R.id.title, R.id.timelength});
			listView.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}