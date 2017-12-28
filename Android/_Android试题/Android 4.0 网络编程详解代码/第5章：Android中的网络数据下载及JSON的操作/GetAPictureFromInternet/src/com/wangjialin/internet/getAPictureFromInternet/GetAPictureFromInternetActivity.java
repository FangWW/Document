package com.wangjialin.internet.getAPictureFromInternet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wangjialin.internet.service.ImageService;

public class GetAPictureFromInternetActivity extends Activity {
	private EditText pathText;
    private ImageView imageView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        pathText = (EditText) this.findViewById(R.id.path);
        imageView = (ImageView) this.findViewById(R.id.imageView);
    }
    
    public void showimage(View v){
    	String path = pathText.getText().toString();
		try {
			Bitmap bitmap = ImageService.getImage(path);
			imageView.setImageBitmap(bitmap);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(getApplicationContext(), R.string.error, 1).show();
		}
    }
}