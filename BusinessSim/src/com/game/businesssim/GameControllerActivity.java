package com.game.businesssim;

import java.io.ObjectInputStream;
import java.util.Locale;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.io.ObjectOutputStream;
import java.io.IOException;
import android.content.Context;


public class GameControllerActivity extends FragmentActivity implements ActionBar.TabListener {
	
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	protected ViewPager mViewPager;
    protected Timer mTimer;
    protected Business mBusiness;
    protected TextView timerText ;
    protected TextView moneyText;

    public static int MAX_TIME = 60000;  // 1 minute
    public static int INTERVAL = 1000;
    public int MAX_DAYS = 30; // TODO: show final dialog box and call finish()
    public static int SECONDS_IN_MINUTE = 60;
    public static int THIRTY_MINUTES = 3;
    public static int HOUR = 6;
    private String FILENAME = "businessInfo.dat";

    protected long timeResumed;
    protected boolean mInitialPanelOpen = true; // this variable keeps track of first panel opening
    protected boolean menuOpened = false;
    protected int mDay = 1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

        // Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}

        // get intent that created this activity
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        // retrieve boolean value as to whether or not the user wants to load a new or
        // old game
        mBusiness = null;
        try {
			// There was a conflict here I figured I should keep the latest change
           //if (extra != null && extra.containsKey("loadGame")){
           //    mBusiness = loadGame(extra.getBoolean("loadGame"));  // TODO: change to take in info from savedInstanceState
           if (extra != null && extra.containsKey("loadGame")){
               loadGame(extra.getBoolean("loadGame"));  // TODO: change to take in info from savedInstanceState
           }
           else{  // something went wrong (loadGame variable not passed, create a new game by default
               mBusiness = loadGame(false);
           }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mDay = mBusiness.getDay();

        // retrieve and set money amount
        moneyText = (TextView) findViewById(R.id.status_money);
        moneyText.setText(String.format("$%.2f",mBusiness.getProfit()));
        // Create timer and start countdown
        timerText = (TextView) findViewById(R.id.status_time);
		
		// there was a conflic there, the comment is what I though was the old
        //startTimer(mBusiness.getStartTime(),INTERVAL);

        mTimer = new Timer(MAX_TIME,INTERVAL);
        mTimer.start();

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

    public boolean onPrepareOptionsMenu (Menu menu){
       if (!mInitialPanelOpen) {
           timerText.setText(R.string.drawer_open);
           mTimer.cancel();
           menuOpened = true;
       }
       else {
           mInitialPanelOpen = false;
       }

       return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onPause(){
        super.onPause();
        mTimer.cancel();
    }

    /**
     * Called when a panel's menu item has been selected by the user.
     * @param featureId The panel that the menu is in.
     * @param item The menu item that was selected.
     * @return boolean You must return true for the panel to be displayed; if you return false it will not be shown.
     */
    public boolean onMenuItemSelected (int featureId, MenuItem item){

        switch (item.getItemId()){
            case R.id.action_mainmenu:
                onBackPressed();
                break;
            case R.id.action_save:
                try {
                    saveGame();
                    Context context = getApplicationContext();

                    if (context != null){
                        Toast.makeText(getApplicationContext(), R.string.game_saved, Toast.LENGTH_LONG).show();
                    }

                    finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.action_howto:
                // TODO : fill in
            	Intent hIntent = new Intent(GameControllerActivity.this, HowToActivity.class);
        		startActivity(hIntent);	
                //startTimer(timeResumed,INTERVAL);  // TODO: remove when case is filled in
                return super.onMenuItemSelected(featureId,item);
            case R.id.action_glossary:
                // TODO : fill in
            	Intent gIntent = new Intent(GameControllerActivity.this, GlossaryActivity.class);
        		startActivity(gIntent);	
                //  startTimer(timeResumed,INTERVAL); // TODO: remove when case is filled in
                return super.onMenuItemSelected(featureId,item);
            default:
                startTimer(timeResumed,INTERVAL);
                return super.onMenuItemSelected(featureId,item);
        }
        return true;
    }

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.

		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			
			switch (position) {
		        case 0:
		            // Top Rated fragment activity
		            return new StandFragment();
		        case 1:
		            // Games fragment activity
		            return new MarketFragment();
		        case 2:
		            // Movies fragment activity
		            return new BankFragment();
	        }
	 
	        return null;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_stand).toUpperCase(l);
			case 1:
				return getString(R.string.title_market).toUpperCase(l);
			case 2:
				return getString(R.string.title_bank).toUpperCase(l);
			}
			return null;
		}
	}

    public Business getBusiness(){
        return mBusiness;
    }

    /**
     * Saves the current instance of the business object
     */
    public void saveGame()throws IOException{
       try{

           ObjectOutputStream objectOutputStream = new ObjectOutputStream(openFileOutput(FILENAME,Context.MODE_PRIVATE));
           mBusiness.setStartTime(timeResumed);
           objectOutputStream.writeObject(mBusiness);
           objectOutputStream.close();

       }catch (IOException e){
           e.printStackTrace();
       }
    }

    /**
     * Load a game based on whether or not loadFile is true, otherwise create new business object
     * @param loadFile  determines whether or not to load a file from memory
     */
    public Business loadGame(boolean loadFile) throws IOException{

        if (loadFile){
            try{
                // try to create input stream
                ObjectInputStream objectInputStream = new ObjectInputStream(openFileInput(FILENAME));

                try {

                    // get business object
                    Business obj = (Business)objectInputStream.readObject();
                    objectInputStream.close();

                    // let user now that game was successfully loaded
                    Context context = getApplicationContext();

                    if (context != null){
                        Toast.makeText(getApplicationContext(),R.string.game_loaded, Toast.LENGTH_LONG).show();
                    }

                    return new Business(obj);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e){
                e.printStackTrace();
            }

        }

        return new Business();
    }

    /**
     * Show confirmation of leaving game without saving
     */
    public void onBackPressed(){
        timerText.setText(R.string.drawer_open);
        mTimer.cancel();
        confirmExit();
    }

    /**
     * Create and dialog box to confirm that the user will be exiting the game
     */
    public void confirmExit(){
        AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(GameControllerActivity.this);
        confirmationDialog.setMessage(R.string.ok_to_exit);
        confirmationDialog.setTitle(R.string.game_not_saved);

        // user close activity if user presses 'yes'
        confirmationDialog.setNegativeButton(R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        // continue game if user presses 'cancel'
        confirmationDialog.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startTimer(timeResumed, INTERVAL);
            }
        });

        confirmationDialog.create();
        confirmationDialog.show();
    }

    /**
     * Create and show dialog box with the day's summary
     */
    public void showSummary(){

        AlertDialog.Builder summaryDialog = new AlertDialog.Builder(GameControllerActivity.this);
        summaryDialog.setMessage(mBusiness.getDailySummary());
        summaryDialog.setTitle(R.string.summary_title);

        summaryDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mBusiness.nextDay();
                mDay = mBusiness.getDay();
                startTimer(MAX_TIME, INTERVAL);
            }
        });

        summaryDialog.create();
        summaryDialog.show();
    }

    /**
     * Create and show dialog box when the game is over
     */
    public void showGameOverDialog(){

        AlertDialog.Builder gameOverDialog = new AlertDialog.Builder(GameControllerActivity.this);
        gameOverDialog.setMessage(mBusiness.getDailySummary());
        gameOverDialog.setTitle(R.string.game_over);

        gameOverDialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });

        gameOverDialog.create();
        gameOverDialog.show();
    }

    /**
     * Get's the formatted string for the time based on seconds left in game
     * @param seconds seconds left in the game
     * @return formatted string that represent the time in the day
     */
    public String getTime(int seconds){
        String hour = Integer.toString((7 + seconds / HOUR) % 12 + 1);
        String minute= (seconds % HOUR) == 0 ? "00" : "30";
        String midday = seconds < 24 ? "AM" : "PM";
        return hour + ":"+minute+" "+midday;
    }


    /**
     * Start a new instance of the countdown timer
     * @param millisInFuture
     * @param countDownInterval
     */
    public void startTimer(long millisInFuture, long countDownInterval){
        timerText.setText(getTime((int)(SECONDS_IN_MINUTE - millisInFuture/INTERVAL)));
        mTimer = new Timer(millisInFuture,countDownInterval);
        mTimer.start();
    }

    /**
     * Countdown timer
     */
    class Timer extends CountDownTimer{

        Timer(long millisInFuture, long countDownInterval){
            super(millisInFuture,countDownInterval);
        }

        // TODO: add customer array updates here
        public void onTick(long millisUntilFinished) {
            timeResumed = millisUntilFinished;
            long seconds = SECONDS_IN_MINUTE - (millisUntilFinished / INTERVAL);


            if(seconds % THIRTY_MINUTES == 0){
                timerText.setText(getTime((int)seconds));
           }
        }

        /**
         * When countdown timer finishes, show the business's summary of the day
         */
        public void onFinish() {
            timerText.setText(R.string.closed);
            if (mDay == MAX_DAYS){
                showGameOverDialog();
            }
            else{
                showSummary();
            }
        }

    }



}
