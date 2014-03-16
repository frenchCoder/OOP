package com.game.businesssim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class GlossaryActivity {
  
  @Override
  protected void onCreate(Bundle savedInstanceState){
    
    super.onCreate(savedInstanceState);
    setContentView(R.layout.glossary);
    
    //This will display the content from the file to the screen.
    TextView ourglossary = (TextView) findViewById(R.id.glossary);
    
    //Will actually handle the screen display.
    ourglossary.setText(readText());
    
  }
  
  private String readText(){
    
    //Get the file in raw format.
    InputStream is = getResources().openRawResource(R.raw.glossary);
    InputStreamReader ir = new InputStreamReader(is);
    
    BufferedReader br = new BufferedReader(ir);
    
    //Use this text variable as our string builder.
    StringBuilder text = new StringBuilder();
    String line;
    
    try{
      while((line = br.readline()) != null){
        text.append(line);
        text.append('\n');
      }
    } catch(IOException e){
      return null;
    }
    
    //Returns the string format to the display of the screen.
    return text.toString();
  }
}
