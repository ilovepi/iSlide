package edu.csun.group2.islide;

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
        
        initialize(new iSlide(ui.puzzleSize, ui.path), cfg ); 
    }
    @Override
    public void onStop()
    {
    	super.onStop();
    	//start new Activity
    }
}