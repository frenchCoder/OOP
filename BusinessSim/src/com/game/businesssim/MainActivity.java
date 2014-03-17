package com.game.businesssim;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

    boolean loadGame = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        final Button button1 = (Button)findViewById(R.id.button1);
        // Have all the buttons objects initialized
        
        button1.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View v){
                // create the intent and start the GameControllerActivity
                Intent intent = new Intent(MainActivity.this, GameControllerActivity.class);

                // create bundle to store information needed by the game controller
                Bundle extras = new Bundle();

                // TODO: Ivette, make sure you take in whether or not the user wants to load a game from memory. Whatever there's answer is store it in the bundle

                // pass in the user's input as to whether or not they want to load a game from memory
                // assuming they can
                extras.putBoolean("loadGame", loadGame);

                // add bundle to intent
                intent.putExtras(extras);
                startActivity(intent);

            }

        });

        
    }

}
