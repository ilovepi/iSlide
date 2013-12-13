package edu.csun.group2.islide;

import javax.xml.datatype.Duration;

import com.badlogic.gdx.backends.android.AndroidApplication;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


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
	            	if(android.os.Build.VERSION.SDK_INT >= 9){
	            		Intent select = new Intent(PlayMenu.this, SelectImageActivity.class);
	            		startActivity(select);
	            	}
	            	else{
	            		Toast.makeText(PlayMenu.this, "Sorry API Level not high enough to use Camera function.", Toast.LENGTH_LONG).show();
	            	}
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