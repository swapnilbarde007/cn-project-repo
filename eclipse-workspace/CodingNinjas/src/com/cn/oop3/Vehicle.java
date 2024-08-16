package com.cn.oop3;

public class Vehicle {
	int maxSpeed;
	String color;
	
	
	public Vehicle(int maxSpeed,String color) {
		this.maxSpeed=maxSpeed;
		this.color=color;
		System.out.println("Vehicle Constructor");
	}
	public void print() {
		System.out.println("Vehicle: Max Speed: "+this.maxSpeed+" Color: "+this.color);
	}
}
