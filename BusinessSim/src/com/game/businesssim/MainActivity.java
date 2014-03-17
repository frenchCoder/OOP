package com.game.businesssim;

import android.os.Bundle;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements PlayDialogFragment.PlayOptionListener{

	public final static String EXTRA_MESSAGE = "com.game.businesssim.MESSAGE";
	
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
        		showPlayDialog();
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
    
    public void showPlayDialog(){
    	DialogFragment dialog = new PlayDialogFragment();
    	dialog.show(this.getFragmentManager(), "PlayOptionFragment");
    }
    

	// this implements the PlayOptionListener interface
    public void onOptionClick(int which){
    	// Start the GameController Activity and pass in the option from the user
    	Intent intent = new Intent(MainActivity.this, GameControllerActivity.class);
    	String value = Integer.toString(which);
    	intent.putExtra(EXTRA_MESSAGE, value);
    	
    	startActivity(intent);
    }

// This is not needed. The action bar is displayed but not the action overflow icon
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
    
}
