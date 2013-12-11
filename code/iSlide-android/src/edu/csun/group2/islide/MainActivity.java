package edu.csun.group2.islide;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class MainActivity extends AndroidApplication {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
        cfg.useGL20 = true; //test
        
        UtilityInfo ui = Utility.CreateUtilityInfo();
        
        initialize(new iSlide(ui.puzzleSize, ui.path, ui.sound), cfg ); 
    }
    @Override
    public void onStop()
    {
    	super.onStop();
    	Intent i = new Intent(this, MainMenuActivity.class);
    	i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	startActivity(i);
    }
}