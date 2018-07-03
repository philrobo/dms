package com.example.car;

public class Car {

    private String VIN;
    private String color;
    private Integer miles;
    
    public void setColor(String color){
    	this.color = color;
    }

    public void setVIN(String vin){
    	this.VIN = vin;
    }
    public void setMiles(Integer miles){
    	this.miles = miles;
    }
    public String getColor() {
    	return this.color;
    }

    public String getVIN(){
    	return this.VIN;
    }
    public Integer getMiles(){
    	return this.miles;
    }
    //...
}
