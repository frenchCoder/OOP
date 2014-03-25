package com.game.businesssim;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
	Business businessInfo ;
 
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
    	
    	// Get the info from the business
    	businessInfo = ((GameControllerActivity)this.getActivity()).getBusiness();
    	
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
        		if(newAmount < 0){ 
        			newAmount =0;
        		}
        		else{
        			updateTotalCost(0, prices, false);
        		}
        		// update the textView for the totalCost, false for subtract
    			((TextView)getView().findViewById(R.id.lemonAmt)).setText(Integer.toString(newAmount));
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
        		if(newAmount < 0){
        			newAmount =0;
        		}
        		else{
        			updateTotalCost(1, prices, false);
        		}
        		// update the textView for the totalCost, false for subtract
    			((TextView)getView().findViewById(R.id.cupsAmt)).setText(Integer.toString(newAmount));
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
        		if(newAmount < 0){
        			newAmount =0;
        		}
        		else{
        			updateTotalCost(2, prices, false);
        		}
        		// update the textView for the totalCost
    			((TextView)getView().findViewById(R.id.sugarAmt)).setText(Integer.toString(newAmount));
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
        		if(newAmount < 0){
        			newAmount =0;
        		}
        		else{
        			updateTotalCost(3, prices, false);
        		}
        		// update the textView for the totalCost
    			((TextView)getView().findViewById(R.id.iceAmt)).setText(Integer.toString(newAmount));
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
        		if(newAmount < 0){
        			newAmount =0;
        		}
        		else{
        			updateTotalCost(4, prices, false);
        		}
        		// update the textView for the totalCost
    			((TextView)getView().findViewById(R.id.adsAmt)).setText(Integer.toString(newAmount));
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
        		
        		float money = (float)businessInfo.getProfit();
        		if(money < Cost){
        			// Display a dialog
        			purchaseDenied();
        		}
        		else{
        			// we can make the purchase
        			businessInfo.setProfit(money-Cost); // make the purchase
        			// update all the items in the business
        			updateBusinessItems(businessInfo);
        			// update the total purchase back to zero
        			((TextView)getView().findViewById(R.id.totalCost)).setText("0");
	
        		}
        			
        	}
        	
        });
        
        return rootView;
    }
    
    /* This method updates all the item fields of the business.
     * It also, sets the values of the items back to zero
     */
    
    private void updateBusinessItems(Business b){
    	// Update lemons
    	String amount = ((TextView)getView().findViewById(R.id.lemonAmt)).getText().toString();
    	((TextView)getView().findViewById(R.id.lemonAmt)).setText("0");
		b.setLemonCount(b.getLemonCount() + Integer.parseInt(amount));
		
		// Update cups
		amount = ((TextView)getView().findViewById(R.id.cupsAmt)).getText().toString();
    	((TextView)getView().findViewById(R.id.cupsAmt)).setText("0");
		b.setCupCount(b.getCupCount() + Integer.parseInt(amount));
		
		// Update sugar
		amount = ((TextView)getView().findViewById(R.id.sugarAmt)).getText().toString();
    	((TextView)getView().findViewById(R.id.sugarAmt)).setText("0");
		b.setSugarCount(b.getSugarCount() + Integer.parseInt(amount));
		
		// Update ice
		amount = ((TextView)getView().findViewById(R.id.iceAmt)).getText().toString();
    	((TextView)getView().findViewById(R.id.iceAmt)).setText("0");
		b.setIceCount(b.getIceCount() + Integer.parseInt(amount));
		
		// Update advertisement 
		amount = ((TextView)getView().findViewById(R.id.adsAmt)).getText().toString();
    	((TextView)getView().findViewById(R.id.adsAmt)).setText("0");
		b.setNumAds(b.getNumAds() + Integer.parseInt(amount));

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
 
    private void purchaseDenied(){
    	AlertDialog.Builder deniedPurchaseDialog = new AlertDialog.Builder(getActivity());	
    	
    	deniedPurchaseDialog.setMessage(R.string.cant_buy_text);
    	deniedPurchaseDialog.setTitle(R.string.cant_buy_title);

        // continue game if user presses 'cancel'
    	deniedPurchaseDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
               
            }
        });

    	deniedPurchaseDialog.create();
    	deniedPurchaseDialog.show();
    }
}