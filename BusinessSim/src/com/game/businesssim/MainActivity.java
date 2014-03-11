package com.game.businesssim;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getActionBar().hide();
        
        
        final Button button1 = (Button)findViewById(R.id.button1);
        // Have all the buttons objects initialized
        
        button1.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// create the intent and start the GameControllerActivity
        		Intent intent = new Intent(MainActivity.this, GameControllerActivity.class);
        		startActivity(intent);	
        	}
        	
        });
        
        
    }

// This is not needed. The action bar is displayed but not the action overflow icon
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
}
