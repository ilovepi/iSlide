package edu.csun.group2.islide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);
		setButtonHandlers();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}
	private void setButtonHandlers() {
	    ((Button) findViewById(R.id.btn_play)).setOnClickListener(btnClick);
	    ((Button) findViewById(R.id.btn_settings)).setOnClickListener(btnClick);
	    ((Button) findViewById(R.id.btn_highscores)).setOnClickListener(btnClick);
	}
	
	
	
	
	private View.OnClickListener btnClick = new View.OnClickListener() {
	    @Override
	    public void onClick(View v) {
	        switch (v.getId()) {
	            case R.id.btn_play: {
	            	Intent play = new Intent(MainMenuActivity.this, PlayMenu.class);
	            	startActivity(play);
	                break;
	            }
	            case R.id.btn_settings: {
	            	Intent settings = new Intent(MainMenuActivity.this, SettingsMenu.class);
	            	startActivity(settings);
	                break;
	            }
	            case R.id.btn_highscores: {
	            	Intent highscores = new Intent(MainMenuActivity.this, HighScore.class);
	            	startActivity(highscores);
	                break;
	            }
	        }
	    }
	};

}
