package com.game.businesssim;

import java.util.ArrayList;

public class RandomEventGenerator {
	
	private ArrayList<RandomEvent> events = new ArrayList<RandomEvent>();
	
	public RandomEventGenerator(){
		RandomEvent event1 = new RandomEvent("One", "lemon", 0, 5, 0, 0, 0, 0, 0.0, 0, 0, 0, 0);
		RandomEvent event2 = new RandomEvent("Two", "ice", 0, 0, 1, 0, 0, 0, 0.0, 0, 0, 0, 0);
		
		this.events.add(event1);
		this.events.add(event2);		
//		int size = 2;
//		RandomEvent event;
//		for(int i=0;i<size;i++){
//			
//		}
	}	
	
	public RandomEvent chooseEvent(){
		int rand = (int) Math.random()*this.events.size();
		return (checkEvent(this.events.get(rand)))?this.events.get(rand):null;
	}
	
	private boolean checkEvent(RandomEvent event){
		return true;
	}

}
