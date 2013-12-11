package edu.csun.group2.islide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class HighScore extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores_menu);
		File sdCard = Environment.getExternalStorageDirectory();
		File directory = new File (sdCard.getAbsolutePath() + "/iSlide");
		File file = new File(directory, "h_scores.txt"); //or any other format supported
		FileInputStream fs;
		try {
			fs = new FileInputStream(file);
			BufferedReader br = new BufferedReader(new FileReader(file));
			char[] buf = new char[1024];
			int i = br.read(buf);
			 String scores = "";
			 for(int j = 0 ; j < i ; j++)
			 {
				 scores += buf[j];
			 }
			TextView t = (TextView) findViewById(R.id.textViewScores);
			t.setText(scores);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(1, menu.FIRST, menu.FIRST, "Clear High Scores");
		return super.onCreateOptionsMenu(menu);
	}
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {

	    switch (item.getItemId()) {
	    case 1:
			File sdCard = Environment.getExternalStorageDirectory();
			File directory = new File (sdCard.getAbsolutePath() + "/iSlide");
			File file = new File(directory, "h_scores.txt"); //or any other format supported
			file.delete();
			TextView t = (TextView) findViewById(R.id.textViewScores);
			t.setText("");
	default:
	    break;

	       }
	    return super.onOptionsItemSelected(item);
	}
}