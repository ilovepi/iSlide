package edu.csun.group2.islide;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SelectImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_image);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.select_image, menu);
		return true;
	}

}
