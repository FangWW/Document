package com.wangjialin.internet.webCodeViewer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wangjialin.internet.service.HtmlService;

public class WebCodeViewerActivity extends Activity {
	private EditText pathText;
	private TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		pathText = (EditText) this.findViewById(R.id.path);
		textView = (TextView) this.findViewById(R.id.textView);
	}

	public void showhtml(View v) {
		String path = pathText.getText().toString();
		try {
			String html = HtmlService.getHtml(path);
			textView.setText(html);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
		}

	}
}