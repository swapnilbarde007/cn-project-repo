package com.cn.oop3;

public class Car extends Vehicle {
	int noOfAirBags;
	int engineCapacity;
	
	public Car(int maxSpeed, String color, int noOfAirBags, int engineCapacity) {
		super(maxSpeed, color);
		this.noOfAirBags = noOfAirBags;
		this.engineCapacity = engineCapacity;
		System.out.println("Car Constructor");
	}
	
	public void print() {
		System.out.println("Car: MaxSpeed: "+this.maxSpeed+" No.Of AirBags: "+this.noOfAirBags+" Color: "+this.color+" Engine: "+this.engineCapacity+"cc");
	}
	
	
}
