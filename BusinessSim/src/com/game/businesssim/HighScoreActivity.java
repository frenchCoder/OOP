package com.game.businesssim;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HighScoreActivity {
  
  protected void onCreate(Bundle SavedInstance){
    super.onCreate(SavedInstance);
    
    setContentView(R.layout.highscore);
    
    //These are text views that correspond to the table layout.
    TextView name = (TextView) findViewById(R.id.name);
    TextView score = (TextView) findViewById(R.Id.score);
    
    //This will handle on opening the file. (Assuming that the file has it in sorted order)
    FileInputStream fis = null;
    
    //Try to locate the file
    try{
      fis = openFileInput("highscore.txt");
    } catch(FileNotFoundException e1){
      //TODO Auto-generated catch block
      e1.printStackTrace();
    }
    
    //This will read the file.
    InputStreamReader isr = new InputStreamReader(fis);
    BufferedReader br = new BufferedReader(isr);
    
    
    String sLine = null;
    
    //These strings will get the corresponding information.
    String Name = "";
    String Scores = "";
    
    //This will be used to determine which string it will get.
    int count = 0;
    
    //This will not get the information from the file.
    try{
      
      while((sLine = br.readLine()) != null){
        
        //This is used to get the name.
        if(count ==0){
          Name += sLine + "\n";
          count++;
        }
        
        //This is used to get their score.
        else{
          Scores += sLine + "\n";
          count--;
        }
        
      }
    } catch(IOException e){
      //TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    //Shows the highscore.
    name.setText(Name);
    score.setText(Scores);
    
  }

}
