package com.game.businesssim;
import java.util.Random;


//returns an integer array of size 60
//each index is 1 second in real time and 10 minutes in game time
public class CustomerFrequencyGenerator {

	int [] ratios = {1, 2, 2, 5, 6, 9, 8, 4, 3, 2};//hourly ratios
	double dailyIncr = 0.1;
	double adIncr = 0.3;
	
	public int [] gen(int day, boolean hasAd){
		int [] freq = new int[60];
		Random r = new Random();
		
		//calc percent change
		double perc = (hasAd) ? adIncr : dailyIncr;
		perc *= day;
		
		for (int i=0; i<10; i++){
			//create number of customers for next hour segment
			int hour = (int)(r.nextDouble() * ratios[i] + ratios[i]*(0.3+perc));
			//fill in next hour of array randomly
			for (int j = 0; j<6; j++){
				freq[i+j] = r.nextInt(hour*2+1);	
			}
		}
		
		
		return freq;
	}

}