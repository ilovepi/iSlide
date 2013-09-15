/*
 * This is the actual Activity that
 * will hold the gameplay. 
 * 
 * From the Extras it can recieve a byteArray 
 * Representing the Image to be used and 
 * an integer to tell it the gridsize.
 * 
 *  If either of these extra values are not present they 
 *  will default to other values.
 *	 
 */
package edu.csun.comp380.group2.islide.engine;

import edu.csun.comp380.group2.islide.R;
import edu.csun.comp380.group2.islide.R.layout;
import edu.csun.comp380.group2.islide.R.menu;
import edu.csun.comp380.group2.islide.util.ExtraKeys;
import edu.csun.comp380.group2.islide.util.GraphicsUtil;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.Menu;

public class PlayGameActivity extends Activity {

	private final int MAX_GRID_SIZE = 6;
	private final int MIN_GRID_SIZE = 3;

	private int gridSize;
	private Bundle extras;
	private Canvas canvas;
	private Bitmap bmp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_game);

		extras = getIntent().getExtras();

		if (extras != null) {
			byte[] imgArray = extras.getByteArray(ExtraKeys.IMAGE_KEY);
			
			gridSize = extras.getInt(ExtraKeys.GRID_SIZE_KEY) > MAX_GRID_SIZE
					|| extras.getInt(ExtraKeys.GRID_SIZE_KEY) < MIN_GRID_SIZE ? 5
					: extras.getInt(ExtraKeys.GRID_SIZE_KEY);

			if (imgArray != null) {
				bmp = BitmapFactory.decodeByteArray(imgArray,0,imgArray.length);
			}
			else{
				bmp = GraphicsUtil.getDefaultBitmap(this);
			}
		}
		canvas = new Canvas();
		canvas.drawBitmap(bmp, 0, 0, null);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play_game, menu);
		return true;
	}

}
