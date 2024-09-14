package com.iot.CloudAnalytics;

public class Energydata {

	private double   reactivepower;
	private double   rmscurrent;
	private String meterid;
	private String timestamp;
	public double getReactivepower() {
		return reactivepower;
	}
	public void setReactivepower(double reactivepower) {
		this.reactivepower = reactivepower;
	}
	public double getRmscurrent() {
		return rmscurrent;
	}
	public void setRmscurrent(double rmscurrent) {
		this.rmscurrent = rmscurrent;
	}
	
	
	public void setEnergydata(double rpower,double current)
	{
		setReactivepower(rpower);
		setRmscurrent(current);
		
	}
	public String getMeterid() {
		return meterid;
	}
	public void setMeterid(String meterid) {
		this.meterid = meterid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
}
