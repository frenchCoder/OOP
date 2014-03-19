package com.game.businesssim;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class RandomEventGenerator {
	
	private ArrayList<RandomEvent> events = new ArrayList<RandomEvent>();
	
	public RandomEventGenerator(){
		events.add(new RandomEvent("One", "lemon", 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		events.add(new RandomEvent("Two", "ice", 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		
//		int size = 2;
//		RandomEvent event;
//		for(int i=0;i<size;i++){
//			
//		}
	}	
	
	public RandomEvent chooseEvent(){
		int rand = (int) Math.random()*events.size();
		return (checkEvent(events.get(rand)))?events.get(rand):null;
	}
	
	private boolean checkEvent(RandomEvent event){
		return true;
	}
	
	

}
