package com.cargarage.garage;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Car {

	@Id
	private String id;
	private String owner;
	private String make;


	private String name;

	private String color;

	private int price;
	
	public Car(String id, String owner, String make, String name,
		String color, int price) {
	    super();
	    this.id = id;
	    this.owner = owner;
	    this.make = make;
	    this.name = name;
	    this.color = color;
	    this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public String getOwner() {
	    return owner;
	}

	public void setOwner(String owner) {
	    this.owner = owner;
	}

	public String getMake() {
	    return make;
	}

	public void setMake(String make) {
	    this.make = make;
	}
	
	

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return  owner+" drives a "+color+" "+make+" "+name+" which costs $"+price+".";
	}
}
