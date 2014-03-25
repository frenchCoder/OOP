package com.game.businesssim;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
 
public class MarketFragment extends Fragment {
	
	private float Cost;
	
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	// Initialize the cost to zero
    	Cost = 0;
    	
    	/* Create the prices array, couldn't use enums
    		item 0 = lemons
    		item 1 = cups
    		item 2 = sugar
    		item 3 = ice
    		item 4 = ads
    	*/
    	final float[] prices = {1.00f, 1.00f, 1.00f, 1.00f, 1.00f}; //TODO: change the prices
    	
        View rootView = inflater.inflate(R.layout.fragment_market, container, false);
         
        final Button mLemon_button = (Button) rootView.findViewById(R.id.minusLemon);
        final Button pLemon_button = (Button) rootView.findViewById(R.id.plusLemon);
        
        final Button mCup_button = (Button) rootView.findViewById(R.id.minusCups);
        final Button pCup_button = (Button) rootView.findViewById(R.id.plusCups);
        
        final Button mSugar_button = (Button) rootView.findViewById(R.id.minusSugar);
        final Button pSugar_button = (Button) rootView.findViewById(R.id.plusSugar);
        
        final Button mIce_button = (Button) rootView.findViewById(R.id.minusIce);
        final Button pIce_button = (Button) rootView.findViewById(R.id.plusIce);
        
        final Button mAds_button = (Button) rootView.findViewById(R.id.minusAds);
        final Button pAds_button = (Button) rootView.findViewById(R.id.plusAds);
        
        final Button buy_button = (Button) rootView.findViewById(R.id.buyBtn);
        
        mLemon_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// update the textView for this item
        		String amount = ((TextView)getView().findViewById(R.id.lemonAmt)).getText().toString();
        		int newAmount = Integer.parseInt(amount) - 1;
        		((TextView)getView().findViewById(R.id.lemonAmt)).setText(Integer.toString(newAmount));
        		// update the textView for the totalCost, false for subtract
        		updateTotalCost(0, prices, false);
        	}
        	
        });
        
        pLemon_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// update the textView for this item
        		String amount = ((TextView)getView().findViewById(R.id.lemonAmt)).getText().toString();
        		int newAmount = Integer.parseInt(amount) + 1;
        		((TextView)getView().findViewById(R.id.lemonAmt)).setText(Integer.toString(newAmount));
        		// update the textView for the totalCost, true for add
        		updateTotalCost(0, prices, true);
        	}
        	
        });
        
        mCup_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// update the textView for this item
        		String amount = ((TextView)getView().findViewById(R.id.cupsAmt)).getText().toString();
        		int newAmount = Integer.parseInt(amount) - 1;
        		((TextView)getView().findViewById(R.id.cupsAmt)).setText(Integer.toString(newAmount));
        		// update the textView for the totalCost, false for subtract
        		updateTotalCost(1, prices, false);
        	}
        	
        });
        
        pCup_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// update the textView for this item
        		String amount = ((TextView)getView().findViewById(R.id.cupsAmt)).getText().toString();
        		int newAmount = Integer.parseInt(amount) + 1;
        		((TextView)getView().findViewById(R.id.cupsAmt)).setText(Integer.toString(newAmount));
        		// update the textView for the totalCost
        		updateTotalCost(1, prices, true);
        	}
        	
        });
        
        mSugar_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// update the textView for this item
        		String amount = ((TextView)getView().findViewById(R.id.sugarAmt)).getText().toString();
        		int newAmount = Integer.parseInt(amount) - 1;
        		((TextView)getView().findViewById(R.id.sugarAmt)).setText(Integer.toString(newAmount));
        		// update the textView for the totalCost
        		updateTotalCost(2, prices, false);
        	}
        	
        });
        
        pSugar_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// update the textView for this item
        		String amount = ((TextView)getView().findViewById(R.id.sugarAmt)).getText().toString();
        		int newAmount = Integer.parseInt(amount) + 1;
        		((TextView)getView().findViewById(R.id.sugarAmt)).setText(Integer.toString(newAmount));
        		// update the textView for the totalCost
        		updateTotalCost(2, prices, true);
        	}
        	
        });
        
        mIce_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// update the textView for this item
        		String amount = ((TextView)getView().findViewById(R.id.iceAmt)).getText().toString();
        		int newAmount = Integer.parseInt(amount) - 1;
        		((TextView)getView().findViewById(R.id.iceAmt)).setText(Integer.toString(newAmount));
        		// update the textView for the totalCost
        		updateTotalCost(3, prices, false);
        	}
        	
        });
        
        pIce_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// update the textView for this item
        		String amount = ((TextView)getView().findViewById(R.id.iceAmt)).getText().toString();
        		int newAmount = Integer.parseInt(amount) + 1;
        		((TextView)getView().findViewById(R.id.iceAmt)).setText(Integer.toString(newAmount));
        		// update the textView for the totalCost
        		updateTotalCost(3, prices, true);
        	}
        	
        });
        
        mAds_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// update the textView for this item
        		String amount = ((TextView)getView().findViewById(R.id.adsAmt)).getText().toString();
        		int newAmount = Integer.parseInt(amount) - 1;
        		((TextView)getView().findViewById(R.id.adsAmt)).setText(Integer.toString(newAmount));
        		// update the textView for the totalCost
        		updateTotalCost(4, prices, false);
        	}
        	
        });
        
        pAds_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// update the textView for this item
        		String amount = ((TextView)getView().findViewById(R.id.adsAmt)).getText().toString();
        		int newAmount = Integer.parseInt(amount) + 1;
        		((TextView)getView().findViewById(R.id.adsAmt)).setText(Integer.toString(newAmount));
        		// update the textView for the totalCost
        		updateTotalCost(4, prices, true);
        	}
        	
        });
        
        buy_button.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		// TODO: update the business object
        	}
        	
        });
        
        return rootView;
    }
    
    /* This method updates the total cost of the on going purchase
     * the boolean add determines if we are adding or subtracting
     */
    private void updateTotalCost(int item, float[] prices, boolean add){
    	// TODO: if we want to set a strict value for increasing (not 1), multiply price * that value
    	float p = prices[item];
    	if(!add) p = p * -1;
    	
    	Cost = Cost + p;
    	
    	((TextView) getView().findViewById(R.id.totalCost)).setText(Float.toString(Cost));
    	
    }
 
}