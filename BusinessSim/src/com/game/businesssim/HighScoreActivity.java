package com.game.businesssim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

public class HighScoreActivity extends Activity{
  
	protected void onCreate(Bundle SavedInstance){
		super.onCreate(SavedInstance);
    
		setContentView(R.layout.highscore);
    
		//These are text views that correspond to the table layout.
		TextView name = (TextView) findViewById(R.id.name);
		TextView score = (TextView) findViewById(R.id.score);
    
		//This will handle on opening the file. (Assuming that the file has it in sorted order)
		FileInputStream fis = null;
		
		File file = new File(getFilesDir(),"highscore.txt");
		
		//Will see if the file already exists.
		if(!file.exists()){
			makeFile();
		}
    
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
	
	//This will make a make a highscore.txt
	public void makeFile(){
		
		//Array of default names.
		String names[] = {"Ivette", "Josh", "Raymond", "Jessica", "Cindy"};
		
		//Array of default scores.
		String score[] = {"100", "75", "50", "25", "0"};
		
		//Name of the file.
		String filename = "highscore.txt";
		
		String line ="\n";
		
		FileOutputStream outputStream;
		
		//This will make the default high score.
		try{
			
			outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
			
			for(int i=0;i<5;i++){
				
				outputStream.write(names[i].getBytes());
				outputStream.write(line.getBytes());
				outputStream.write(score[i].getBytes());
				outputStream.write(line.getBytes());
				
			}
			
			outputStream.close();
			
		} catch(Exception e){
			
			e.printStackTrace();
			
		}
	}
}
