package edu.csun.group2.islide;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SettingsMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings_menu);
		setButtonHandlers();
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
		}
	}

	private View.OnClickListener btnClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.btn_about: {
				Toast.makeText(SettingsMenu.this, "Put about info here",
						Toast.LENGTH_SHORT).show();
				break;
			}
			}
		}
	};
}