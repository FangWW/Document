package com.wangjialin.internet.webservice;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wangjialin.internet.service.WebServiceRequestFromAndroid;

public class AndroidInteractWithWebServiceActivity extends Activity {
		private EditText mobileText;
	    private TextView textView;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        mobileText = (EditText) this.findViewById(R.id.mobile);
	        textView = (TextView) this.findViewById(R.id.textView);
	    }
	    
	    public void query(View v){
	    	String mobile = mobileText.getText().toString();
	    	try {
				String address = WebServiceRequestFromAndroid.getAddress(mobile);
				textView.setText(address);
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), R.string.error, 1).show();
			}
	    }
}