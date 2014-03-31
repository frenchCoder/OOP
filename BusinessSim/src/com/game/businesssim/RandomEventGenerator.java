package com.game.businesssim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.app.Application;
import android.content.Context;

public class RandomEventGenerator extends Application{

	private ArrayList<RandomEvent> events = new ArrayList<RandomEvent>();

	//initialzes the events array
	public RandomEventGenerator(Context context) {
		BufferedReader br = null;
		try {
			String sCurrentLine;
			String[] eventStrings;
			br = new BufferedReader(new InputStreamReader(context.getAssets().open("events.txt")));
			while ((sCurrentLine = br.readLine()) != null) {
				eventStrings = sCurrentLine.trim().split("\\s*,\\s*");
				this.events.add(new RandomEvent(eventStrings[0],
						eventStrings[1], Integer.parseInt(eventStrings[2]),
						Integer.parseInt(eventStrings[3]), Integer
								.parseInt(eventStrings[4]), Integer
								.parseInt(eventStrings[5]), Integer
								.parseInt(eventStrings[6]), Integer
								.parseInt(eventStrings[7]), Double
								.parseDouble(eventStrings[8]), Integer
								.parseInt(eventStrings[9]), Integer
								.parseInt(eventStrings[10]), Integer
								.parseInt(eventStrings[11]), Double
								.parseDouble(eventStrings[12])));
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	//chooses an event at random, runs a check, then returns the event
	public RandomEvent chooseEvent(Business biz) {
		int rand = (int) Math.random() * events.size();
		RandomEvent event = events.get(rand);
		event = checkEvent(events.get(rand), biz);
		return event;
	}
	
	
	//makes sure the player won't go in the negatives for anything
	private RandomEvent checkEvent(RandomEvent event, Business biz) {
		if (biz.getProfit()+event.getProfit()<0)
			event.setProfit(-biz.getProfit());
		if(biz.getLemonCount()+event.getLemonCount()<0)
			event.setLemonCount(-biz.getLemonCount());
		if(biz.getIceCount()+event.getIceCount()<0)
			event.setIceCount(-biz.getIceCount());
		if(biz.getCupCount()+event.getCupCount()<0)
			event.setCupCount(-biz.getCupCount());
		if(biz.getLemonQuantity()+event.getLemonadeQty()<0)
			event.setLemonadeQty(-biz.getLemonQuantity());
		if(biz.getSugarCount()+event.getSugarCount()<0)
			event.setSugarCount(-biz.getSugarCount());
		if(biz.getNumLoans()+event.getNumLoans()<0)
			event.setNumLoans(-biz.getNumLoans());
		if(biz.getNumAds()+event.getNumAds()<0)
			event.setNumAds(-biz.getNumAds());
		if(biz.getCupsSold()+event.getCupSold()<0)
			event.setCupSold(-biz.getCupsSold());
		if(biz.getPricePerCup()+event.getPricePerCup()<0)
			event.setPricePerCup(-biz.getPricePerCup());
		return event;
	}

}
