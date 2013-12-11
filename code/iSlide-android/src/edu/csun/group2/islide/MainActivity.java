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
        cfg.useGL20 = true; 									//Using GL 2.0 allows us to have textures that 
        UtilityInfo ui = Utility.CreateUtilityInfo();			//don't need dimensions that are a power of 2
        initialize(new iSlide(ui.puzzleSize, ui.path, ui.sound), cfg ); 
    }
    @Override
    public void onStop()
    {	
    	super.onStop();
    	Intent i = new Intent(this, MainMenuActivity.class);
    	i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); 			//Clears back-stack
    	startActivity(i);
    }
}