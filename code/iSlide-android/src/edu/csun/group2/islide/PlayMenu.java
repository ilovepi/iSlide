package edu.csun.group2.islide;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;


public class PlayMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_menu);
		setButtonHandlers();
	}
	private void setButtonHandlers() {
	    ((Button) findViewById(R.id.btn_select)).setOnClickListener(btnClick);  
	    //((Button) findViewById(R.id.btn_gallery)).setOnClickListener(btnClick);
	    ((Button) findViewById(R.id.btn_test)).setOnClickListener(btnClick);
	}
	
	private View.OnClickListener btnClick = new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
	        switch (v.getId()) {
	            case R.id.btn_test: {
	            	Utility.fileLocation="";
	            	Intent test = new Intent(PlayMenu.this, MainActivity.class);
	            	startActivity(test);
	                break;
	            }
	            //add camera interaction here
	            case R.id.btn_select: {
	            	Intent select = new Intent(PlayMenu.this, TakePictureActivity.class);
	            	startActivity(select);
	                break;
	            }
	            /*
	            case R.id.btn_gallery: {
	            	Intent gallery = new Intent(PlayMenu.this, .class);
	            	startActivity(gallery);
	                break;
	            }*/
	        }
	    }
	};
}