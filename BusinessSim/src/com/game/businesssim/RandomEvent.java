package com.game.businesssim;

public class RandomEvent {

	private String title, description;
	private int type;
	private int lemonCount;
	private int iceCount;
	private int cupCount;
	private int lemonadeQty;
	private int sugarCount;
	private double profit;
	private int numLoans;
	private int numAds;
	private int cupSold;
	private double pricePerCup;

	public RandomEvent(String title, String description, int type,
			int lemonCount, int iceCount, int cupCount, int lemonadeQty,
			int sugarCount, double profit, int numLoans, int numAds,
			int cupSold, double pricePerCup) {
		this.title=title;
		this.description=description;
		this.type=type;
		this.profit = profit;
		this.numAds = numAds;
		this.numLoans = numLoans;
		this.sugarCount = sugarCount;
		this.iceCount = iceCount;
		this.lemonadeQty = lemonadeQty;
		this.cupCount = cupCount;
		this.lemonCount = lemonCount;
		this.cupSold = cupSold;
		this.pricePerCup = pricePerCup;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDescription() {
		return this.description;
	}

	public int getType() {
		return this.type;
	}

	public int getLemonCount() {
		return this.lemonCount;
	}

	public int getIceCount() {
		return this.iceCount;
	}

	public int getCupCount() {
		return this.cupCount;
	}

	public int getLemonadeQty() {
		return this.lemonadeQty;
	}

	public int getSugarCount() {
		return this.sugarCount;
	}

	public double getProfit() {
		return this.profit;
	}

	public int getNumLoans() {
		return this.numLoans;
	}

	public int getNumAds() {
		return this.numAds;
	}

	public int getCupSold() {
		return this.cupSold;
	}

	public double getPricePerCup() {
		return this.pricePerCup;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setLemonCount(int lemonCount) {
		this.lemonCount = lemonCount;
	}

	public void setIceCount(int iceCount) {
		this.iceCount = iceCount;
	}

	public void setCupCount(int cupCount) {
		this.cupCount = cupCount;
	}

	public void setLemonadeQty(int lemonadeQty) {
		this.lemonadeQty = lemonadeQty;
	}

	public void setSugarCount(int sugarCount) {
		this.sugarCount = sugarCount;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public void setNumLoans(int numLoans) {
		this.numLoans = numLoans;
	}

	public void setNumAds(int numAds) {
		this.numAds = numAds;
	}

	public void setCupSold(int cupSold) {
		this.cupSold = cupSold;
	}

	public void setPricePerCup(double pricePerCup) {
		this.pricePerCup = pricePerCup;
	}
}
