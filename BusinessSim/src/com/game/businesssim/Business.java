package com.game.businesssim;

import java.io.Serializable;

/**
 * Created by Cindy on 3/16/14.
 */
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

    //new game
    Business(){
        profit=100;
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
    }

    public double getProfit(){
        return profit;
    }

    public void setProfit(float money){
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

    public void increasePricePerCup(double cent){
        pricePerCup += cent;
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
