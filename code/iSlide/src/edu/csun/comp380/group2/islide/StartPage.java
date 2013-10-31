package edu.csun.comp380.group2.islide;

import edu.csun.comp380.group2.islide.engine.PlayGameActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class StartPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.start_page, menu);
        return true;
    }
    
    //Strictly for Game Development Testing, Remove before production
    public void btnDebug_OnClick(View v)
    {
    	Intent i = new Intent(this,PlayGameActivity.class);
    	//i.putExtra("", "");//Can put extras here to Test
    	startActivity(i);
    }
}
