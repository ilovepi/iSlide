package edu.csun.comp380.group2.islide.menus;

import java.io.ByteArrayOutputStream;

import edu.csun.comp380.group2.islide.R;
import edu.csun.comp380.group2.islide.R.layout;
import edu.csun.comp380.group2.islide.R.menu;
import edu.csun.comp380.group2.islide.engine.PlayGameActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import edu.csun.comp380.group2.islide.util.ExtraKeys;
import edu.csun.comp380.group2.islide.util.GraphicsUtil;

@SuppressWarnings("unused")
public class GameDebugMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_debug_menu);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_debug_menu, menu);
		return true;
	}

	public void debug_onClick(View v) {
		Intent i = new Intent(this, PlayGameActivity.class);
		
		i.putExtra(ExtraKeys.IMAGE_KEY, GraphicsUtil.getByteArrayFromImage(this,
				R.drawable.ic_launcher, Bitmap.CompressFormat.PNG));
		i.putExtra(ExtraKeys.GRID_SIZE_KEY, 3);
		
		startActivity(i);

	}
}
