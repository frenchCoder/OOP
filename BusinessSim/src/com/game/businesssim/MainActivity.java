package com.game.businesssim;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements PlayOptionFragment.PlayOptionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getActionBar().hide();
        
        
        final Button button_play = (Button)findViewById(R.id.chooseGameBtn);
        final Button button_howto = (Button)findViewById(R.id.howtoBtn);
        final Button button_glossary = (Button)findViewById(R.id.glossaryBtn);
        final Button button_scores = (Button)findViewById(R.id.highscoreBtn);
        
        // Have all the buttons objects initialized
        
        button_play.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        			
        	}
        	
        });
        
        button_howto.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// create the intent and start the GameControllerActivity
        		Intent intent = new Intent(MainActivity.this, HowToActivity.class);
        		startActivity(intent);	
        	}
        	
        });
        
        button_glossary.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// create the intent and start the GameControllerActivity
        		Intent intent = new Intent(MainActivity.this, GlossaryActivity.class);
        		startActivity(intent);	
        	}
        	
        });
        
        button_scores.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// create the intent and start the GameControllerActivity
        		Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
        		startActivity(intent);	
        	}
        	
        });
        
    }
    
    public void onOptionClick(int which){
    	
    }

// This is not needed. The action bar is displayed but not the action overflow icon
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
}
