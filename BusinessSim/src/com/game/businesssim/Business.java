package com.game.businesssim;

import java.io.Serializable;

public class Business implements Serializable {
    private int lemonCount;
    private int iceCount;
    private int cupCount;
    private int lemonadeQty;
    private int sugarCount;
    private double profit;
    private int numLoans;
    private int numAds;
    private int day;
    private int cupSold;
    private double pricePerCup;
    private long startTime;

    private static double PRICE_CHANGE = 0.25;
    private static int SUGAR_PER_CUP = 2;
    private static int ICE_PER_CUP = 2;
    private static int LEMON_PER_CUP = 1;
    private static int CUPS_PER_PITCHER = 30;

    private int cupsPerPitcher;  // the number of cups currently in pitcher

    //new game
    Business(){  // TODO: change appropriate values back to zero!
        profit=0;
        numAds=0;
        numLoans=1;
        sugarCount=0;
        iceCount=0;
        lemonadeQty=0;
        cupCount=0;
        lemonCount=0;
        day=1;
        cupSold = 0;
        pricePerCup = .25;
        startTime = 60000;
    }

    Business(Business business){
        profit = business.getProfit();
        numAds = business.getNumAds();
        numLoans = business.getNumLoans();
        sugarCount = business.getSugarCount();
        iceCount = business.getIceCount();
        lemonCount =  business.getLemonCount();
        lemonadeQty =  business.getLemonQuantity();
        cupCount =  business.getCupCount();
        day =  business.getDay();
        cupSold = business.getCupsSold();
        pricePerCup = business.getPricePerCup();
        startTime = business.getStartTime();
    }

    public long getStartTime(){
        return startTime;
    }

    public void setStartTime(long time){
        startTime = time;
    }

    public double getProfit(){
        return profit;
    }

    public void setProfit(double money){
        profit=money;
    }

    public int getNumAds(){
        return numAds;
    }

    public void setNumAds(int ads){
        numAds= ads;
    }

    public int getNumLoans(){
        return numLoans;
    }

    public void setLoans(int loans){
        numLoans=loans;
    }

    public int getSugarCount(){
        return sugarCount;
    }

    public void setSugarCount(int sugarCnt){
        sugarCount=sugarCnt;
    }

    public int getIceCount(){
        return iceCount;
    }

    public void setIceCount(int iceCnt){
        iceCount=iceCnt;
    }

    public int getCupCount(){
        return cupCount;
    }

    public void setCupCount(int cupCnt){
        cupCount=cupCnt;
    }

    public int getLemonQuantity(){
        return lemonadeQty;
    }

    public void setLemonQty(int lemonQty){
        lemonadeQty=lemonQty;
    }

    public int getLemonCount(){
        return lemonCount;
    }

    public void setLemonCount(int lemonCnt){
        lemonCount=lemonCnt;
    }

    public int getDay(){
        return day;
    }

    public void nextDay(){
        day++;
    }

    public void setCupSold(int sold){
        cupSold = sold;
    }

    public int getCupsSold(){
        return cupSold;
    }

    public void increasePricePerCup(){
        pricePerCup += PRICE_CHANGE;
    }

    public void makeLemonade(){

        // check if pitcher is already full
        if (lemonadeQty == 100) return;

        // determine the amount of lemon, sugar, and ice currently in pitcher
        int lemonsInPitcher = cupsPerPitcher*LEMON_PER_CUP;
        int sugarInPitcher = cupsPerPitcher*SUGAR_PER_CUP;
        int iceInPitcher = cupsPerPitcher*ICE_PER_CUP;

        // determine the most amount of lemonade that can be made
        int iceNeeded = CUPS_PER_PITCHER*ICE_PER_CUP - iceInPitcher;
        int cupsNeeded = CUPS_PER_PITCHER - cupsPerPitcher;
        int sugarNeeded = CUPS_PER_PITCHER*SUGAR_PER_CUP - sugarInPitcher;
        int lemonsNeeded = CUPS_PER_PITCHER*LEMON_PER_CUP - lemonsInPitcher;

        // determine minimum amount of cups of lemonade that can be made
        int totalCups = Math.min(iceNeeded/2, sugarNeeded/2);

        // check if there is enough supplies to make some lemonade
        if (iceCount - totalCups*ICE_PER_CUP <= 0) return;
        if (cupCount - totalCups <= 0) return;
        if (sugarCount - totalCups*SUGAR_PER_CUP <= 0) return;
        if (lemonCount - totalCups*LEMON_PER_CUP <= 0) return;

        // increment the amount of supplies that are currently in a batch of lemonade
        cupsPerPitcher += totalCups;

        // increase the lemonade quantity
        lemonadeQty += (cupsPerPitcher * 100) / CUPS_PER_PITCHER;

        // decrement supplies used for this batch of lemonade
        iceCount -= iceNeeded;
        cupCount -= cupsNeeded;
        sugarCount -= sugarNeeded;
        lemonCount -= lemonsNeeded;
    }

    public void sellLemonade(int totalCustomers){

        // check if there is enough lemonade to sell to this many customers
        if (cupsPerPitcher - totalCustomers <= 0){
            totalCustomers = cupsPerPitcher;
        }

        cupsPerPitcher -= totalCustomers;
        profit += pricePerCup*totalCustomers;
        cupSold += totalCustomers;
    }

    public void decreasePricePerCup(){
        if(pricePerCup - PRICE_CHANGE > 0){
            pricePerCup -= PRICE_CHANGE;
        }
    }

    public double getPricePerCup(){
        return pricePerCup;
    }

    public String getDailySummary(){
        String summary = "";
        summary += String.format("Day %d:\n",getDay());
        summary += String.format("\tProfit: $%.2f\n",profit);
        summary += String.format("\tCups sold: %d\n", cupSold);

        return summary;
    }
}
