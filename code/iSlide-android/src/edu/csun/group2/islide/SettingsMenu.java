package edu.csun.group2.islide;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_menu);
		setButtonHandlers();
		RadioButton r;
		switch(Utility.size)
		{
		case 3:
			r = (RadioButton) findViewById(R.id.radio_easy);
			break;
		case 4:
			r = (RadioButton) findViewById(R.id.radio_medium);
			break;
		case 5:
			r = (RadioButton) findViewById(R.id.radio_hard);
			break;
		default:
			r = (RadioButton) findViewById(R.id.wtfRadio);
			break;
		}
		r.setChecked(true);
		if(Utility.sound)
		{
			CheckBox c = (CheckBox) findViewById(R.id.checkBox_music);
			c.setChecked(true);
		}
	}

	private void setButtonHandlers() {
		((Button) findViewById(R.id.btn_about)).setOnClickListener(btnClick);
	}

	public void RadioGroup_Click(View view) {
		switch (view.getId()) {
		case R.id.radio_easy: {
			Utility.size = 3;
			break;
		}
		case R.id.radio_medium: {
			Utility.size = 4;
			break;
		}
		case R.id.radio_hard: {
			Utility.size = 5;
			break;
		}
		case R.id.wtfRadio:{
			Utility.size = 8;
		}
		}
	}

	public void SoundEnabled_onClick(View v) {
		CheckBox c = (CheckBox) findViewById(R.id.checkBox_music);
		if (c.isChecked()) {
			Utility.sound = true;
		} else {
			Utility.sound = false;
		}
	}

	private View.OnClickListener btnClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_about: {
				Toast.makeText(SettingsMenu.this, "Created by:\n\tTyler Thomas\n\tPaul Kirth\n\tDavid Mkrtchyan\n\tDoug Masini\n\tCopyright (c) 2013 All Rights Reserved.",
						Toast.LENGTH_LONG).show();
				break;
			}
			}
		}
	};
}